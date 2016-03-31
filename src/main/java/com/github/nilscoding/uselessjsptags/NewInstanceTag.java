package com.github.nilscoding.uselessjsptags;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * A tag to create a new object instance
 * @author NilsCoding
 */
public class NewInstanceTag extends SimpleTagSupport implements DynamicAttributes {
    
    protected String type;
    protected String scope;
    protected String var;
    protected Map<Integer,Object> paramMap;
    
    public NewInstanceTag() {
        super();
        this.paramMap = new TreeMap<>();
    }
    
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (localName.matches("^param(\\d+)$")) {
            String numPartStr = localName.substring(5);
            int numPart = Utils.parseInt(numPartStr, -1);
            if (numPart > -1) {
                if (value instanceof ValueExpression) {
                    value = ((ValueExpression)value).getValue(getJspContext().getELContext());
                }
                this.paramMap.put(numPart, value);
            }
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setVar(String var) {
        this.var = var;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        if (this.paramMap.isEmpty() == true) {
            // no constructor parameters, so just create the object
            try {
                Class clazz = Class.forName(this.type, true, Thread.currentThread().getContextClassLoader());
                Object obj = clazz.newInstance();
                int scopeInt = ScopeUtils.resolveScopeByName(this.scope, PageContext.PAGE_SCOPE);
                getJspContext().setAttribute(this.var, obj, scopeInt);
            } catch (Exception ex) {
                // just ignore the exception
            }
        } else {
            try {
                List<Object> paramValues = new LinkedList<>(this.paramMap.values());
                Class clazz = Class.forName(this.type, true, Thread.currentThread().getContextClassLoader());
                Object obj = Utils.createNewInstance(clazz, paramValues);
                int scopeInt = ScopeUtils.resolveScopeByName(this.scope, PageContext.PAGE_SCOPE);
                getJspContext().setAttribute(this.var, obj, scopeInt);
            } catch (Exception ex) {
                // just ignore the exception
            }
        }
    }

    
}
