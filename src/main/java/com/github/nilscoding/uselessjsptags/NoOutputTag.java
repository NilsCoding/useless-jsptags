package com.github.nilscoding.uselessjsptags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * A tag that will evaluate the body but not write anything to the JSP
 * (setting headers and cookies will not be affected)
 * @author NilsCoding
 */
public class NoOutputTag extends BodyTagSupport {

    private static final long serialVersionUID = -1336372119114794298L;
    
    protected String debugOutput;
    
    public NoOutputTag() {
        super();
    }

    public void setDebugOutput(String debugOutput) {
        this.debugOutput = debugOutput;
    }
    
    @Override
    public int doStartTag() throws JspException {
        // evaluate body, buffered
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        // debug enabled?
        if ("true".equalsIgnoreCase(this.debugOutput)) {
            // get evaluated body
            String bodyStr = bodyContent.getString();
            if (bodyStr != null) {
                bodyStr = bodyStr.replace("<!--", " !--").replace("-->", "-- ");
                try {
                    JspWriter out = pageContext.getOut();
                    out.print("<!--");
                    out.print(bodyStr);
                    out.print("-->");
                } catch (Exception ex) {
                    // ignore exception here
                }
            }
        }
        
        // continue page evaluation
        return EVAL_PAGE;
    }
}
