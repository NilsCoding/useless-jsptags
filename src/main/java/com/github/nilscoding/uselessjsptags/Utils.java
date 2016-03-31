package com.github.nilscoding.uselessjsptags;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

/**
 * Some common utility methods
 * @author NilsCoding
 */
public class Utils {
    
    private Utils() { }
    
    /**
     * Parses a String to an int value
     * @param str   string to parse
     * @param defaultValue  default value
     * @return  parsed int or default value
     */
    public static int parseInt(String str, int defaultValue) {
        if ((str == null) || (str.length() == 0)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return defaultValue;
        }
    }
    
    /**
     * Checks if a String is empty
     * @param str   string to check
     * @return  true if empty, false if not empty
     */
    public static boolean isEmptyString(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return (str.length() == 0);
    }
    
    /**
     * Searches for all constructors that might accept the given parameter values
     * @param clazz class
     * @param values    possible constructor parameters
     * @return  list with p
     */
    public static <T> List<Constructor<?>> findMatchingConstructor(Class<T> clazz, List<Object> values) {
        List<Constructor<?>> resultList = new LinkedList<>();
        if (clazz == null) {
            return resultList;
        }
        if ((values == null) || (values.isEmpty() == true)) {
            // simple case: no parameters
            try {
                resultList.add(clazz.getConstructor());
            } catch (Exception ex) {
            }
            return resultList;
        }
        try {
            // check each constructor
            for (Constructor<?> oneCtr : clazz.getConstructors()) {
                if (oneCtr.getParameterCount() != values.size()) {
                    // skip check if parameter count is different
                    // (maybe trailing null values could be ignored in future versions)
                    continue;
                }
                boolean mightMatch = true;
                // check if parameter types are compatible
                Class<?>[] paramTypes = oneCtr.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    Class<?> oneParamType = paramTypes[i];
                    Object possibleParamValue = values.get(i);
                    if (possibleParamValue == null) {
                        // if value is null, then the param type must not be a primitive
                        if (oneParamType.isPrimitive()) {
                            mightMatch = false;
                            break;
                        }
                    } else {
                        if (oneParamType.isAssignableFrom(possibleParamValue.getClass()) == false) {
                            mightMatch = false;
                            break;
                        }
                    }
                    // other special cases are currently ignored here
                }
                if (mightMatch == true) {
                    resultList.add(oneCtr);
                }
            }
        } catch (Exception ex) {
        }
        return resultList;
    }
    
    /**
     * Creates a new class instance by searching for matching constructors, then creating the object
     * @param clazz class to create instance of
     * @param parameterValues   parameter value objects (list can be null or empty)
     * @return  created object or null
     */
    public static Object createNewInstance(Class<?> clazz, List<Object> parameterValues) {
        if (clazz == null) {
            return null;
        }
        if ((parameterValues == null) || (parameterValues.isEmpty() == true)) {
            try {
                return clazz.newInstance();
            } catch (Exception ex) {
                return null;
            }
        }
        List<Constructor<?>> possibleConstructors = findMatchingConstructor(clazz, parameterValues);
        if ((possibleConstructors == null) || (possibleConstructors.isEmpty() == true)) {
            return null;
        }
        Object[] parameterValuesArray = parameterValues.toArray();
        for (Constructor<?> oneCtr : possibleConstructors) {
            try {
                Object newObj = oneCtr.newInstance(parameterValuesArray);
                return newObj;
            } catch (Exception ex) {
            }
        }
        return null;
    }
    
}
