package com.github.nilscoding.uselessjsptags;

import javax.servlet.jsp.PageContext;

/**
 * Scope utility functions
 * @author NilsCoding
 */
public class ScopeUtils {

    private ScopeUtils() { }
    
    /**
     * Resolves the scope by name, returns default scope if invalid
     * @param scopeName scope name
     * @param defaultScope  default scope to return if invalid
     * @return  scope or default scope
     */
    public static int resolveScopeByName(String scopeName, int defaultScope) {
        if (scopeName == null) {
            return defaultScope;
        }
        scopeName = scopeName.trim().toLowerCase();
        if (scopeName.length() == 0) {
            return defaultScope;
        }
        switch (scopeName) {
            case "application": return PageContext.APPLICATION_SCOPE;
            case "page": return PageContext.PAGE_SCOPE;
            case "request": return PageContext.REQUEST_SCOPE;
            case "session": return PageContext.SESSION_SCOPE;
        }
        return defaultScope;
    }
    
}
