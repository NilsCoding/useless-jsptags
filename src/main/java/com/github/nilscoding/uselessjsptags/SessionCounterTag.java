package com.github.nilscoding.uselessjsptags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * A tag with a counter in the session
 * @author NilsCoding
 */
public class SessionCounterTag extends SimpleTagSupport {
    
    protected static final String SESSION_KEY = "com.github.nilscoding.uselessjsptags:sessionCounter";
    protected String action;
    
    public SessionCounterTag() {
        super();
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        // get counter from session first
        Object counterObj = getJspContext().getAttribute(SESSION_KEY, PageContext.SESSION_SCOPE);
        // if not present (or of wrong type) then set it in session
        Counter counter;
        if (counterObj instanceof Counter) {
            counter = (Counter)counterObj;
        } else {
            counter = new Counter();
            getJspContext().setAttribute(SESSION_KEY, counter, PageContext.SESSION_SCOPE);
        }
        //
        // actions
        //
        if ((action == null) || (action.length() == 0) 
                || (action.equals("increase") 
                || (action.equals("inc")) 
                || (action.equals("++")))) 
        {
            // increase counter
            counter.increase();
        } else if ((action.equals("print") || (action.equals("out")))) {
            // print current value
            String counterValueStr = String.valueOf(counter.getValue());
            getJspContext().getOut().print(counterValueStr);
        }
        // unsupported actions do not have any effect on the counter 
        // (except that a new counter might be created in the session if there was none)
    }
    
}
