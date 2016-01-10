package com.github.nilscoding.uselessjsptags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * An effectively empty tag
 * @author NilsCoding
 */
public class EmptyTag extends BodyTagSupport {

    private static final long serialVersionUID = 1109394257031667730L;
    
    public EmptyTag() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        // skip the body, which will then not be evaluated in any way
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        // continue page evaluation
        return EVAL_PAGE;
    }
    
    
}
