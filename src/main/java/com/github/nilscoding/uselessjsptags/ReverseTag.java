package com.github.nilscoding.uselessjsptags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * A tag that reverses the body string
 * @author NilsCoding
 */
public class ReverseTag extends BodyTagSupport {

    private static final long serialVersionUID = 2660794610652213715L;

    public ReverseTag() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        String bodyStr = this.bodyContent.getString();
        if ((bodyStr != null) && (bodyStr.length() > 0)) {
            StringBuilder buffer = new StringBuilder(bodyStr);
            buffer.reverse();
            try {
                pageContext.getOut().print(buffer.toString());
            } catch (IOException ioex) {
            }
        }
        return EVAL_PAGE;
    }
    
}
