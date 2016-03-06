package com.github.nilscoding.uselessjsptags;

import java.io.Reader;
import java.io.Writer;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * A tag that evaluates its body using a JSR-223 compliant scripting langauge
 * @author NilsCoding
 */
public class DynamicScriptTag extends BodyTagSupport {

    private static final long serialVersionUID = 6780650494763759655L;
    
    protected String scriptTypeName = "JavaScript";
    protected String allowDirectWrite = "false";
    protected String resultVar;
    protected int resultVarScope;
    
    public DynamicScriptTag() {
        super();
    }

    public void setScriptTypeName(String scriptTypeName) {
        this.scriptTypeName = scriptTypeName;
    }

    public void setAllowDirectWrite(String allowDirectWrite) {
        this.allowDirectWrite = allowDirectWrite;
    }

    public void setResultVar(String resultVar) {
        this.resultVar = resultVar;
    }

    public void setResultVarScope(int resultVarScope) {
        this.resultVarScope = resultVarScope;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        // get body content
        String bodyStr = bodyContent.getString();
        if ((bodyStr != null) && (bodyStr.length() > 0)) {
            // prepare empty writer and reader
            Writer errorWriter = new NullWriter();
            Reader inputReader = new EmptyReader();
            try {
                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName(this.scriptTypeName);
                if (engine != null) {
                    ScriptContext ctx = engine.getContext();
                    ctx.setErrorWriter(errorWriter);
                    ctx.setReader(inputReader);
                    // if requested, allow direct writing
                    if ("true".equalsIgnoreCase(this.allowDirectWrite)) {
                        ctx.setWriter(this.pageContext.getOut());
                    }
                    // register some special objects
                    engine.put("_pageContext", pageContext);
                    engine.put("_request", (HttpServletRequest)pageContext.getRequest());
                    engine.put("_response", (HttpServletResponse)pageContext.getResponse());
                    engine.put("_session", pageContext.getSession());
                    // evaluate content
                    Object resultOject = engine.eval(bodyStr);
                    if (this.resultVar != null) {
                        pageContext.setAttribute(this.resultVar, resultOject, this.resultVarScope);
                    }
                }
            } catch (Exception ex) {
                // just ignore the exception here
            }
        }
        return EVAL_PAGE;
    }
    
}
