package com.wildetechsolutions.reflection;

import java.lang.reflect.Field;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Reflection utility methods
 */
public class ReflectionUtils {

    /**
     * Get the type of a field from a class and field name which can be nested
     * @param beanClass the class to get the field from
     * @param fullNameofField the name of the field which can be nested
     * @return
     * @throws NoSuchFieldException
     */
    public static Class<?> getFieldType(Class<?> beanClass, String fullNameofField) throws NoSuchFieldException{
        String[] nestedPropertyNames = fullNameofField.split("\\.");

        if(nestedPropertyNames.length == 1){
            return beanClass.getDeclaredField(fullNameofField).getType();
        }else{
            Class<?> subClass = beanClass;
            Field subField = null;
            for (String nestedPropertyName : nestedPropertyNames) {
                subField = subClass.getDeclaredField(nestedPropertyName);
                subClass = subField.getType();
            }
            return subClass;
        }
    }

    /**
     * Get the field object from a class and field name which can be nested
     * @param beanClass the class to get the field from
     * @param fullNameofField the name of the field which can be nested
     * @return the field object
     * @throws NoSuchFieldException
     */
    public static Field getField(Class<?> beanClass, String fullNameofField) throws NoSuchFieldException{
        String[] nestedPropertyNames = fullNameofField.split("\\.");

        if(nestedPropertyNames.length == 1){
            return beanClass.getDeclaredField(fullNameofField);
        }else{
            Class<?> subClass = beanClass;
            Field subField = null;
            for (String nestedPropertyName : nestedPropertyNames) {
                subField = subClass.getDeclaredField(nestedPropertyName);
                subClass = subField.getType();
            }
            return subField;
        }
    }

    /**
     * Check if the type is a common type
     * @param type the type to check
     * @return true if the type is a common type
     */
    public static boolean isCommonType(Class<?> type){
        return type.isPrimitive()
                || type == String.class
                || type == Integer.class
                || type == Long.class
                || type == Double.class
                || type == Float.class
                || type == Boolean.class
                || type == LocalDate.class
                || type == LocalDateTime.class
                || type == ZonedDateTime.class
                || type == Timestamp.class;
    }
}
