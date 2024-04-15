package com.wildetechsolutions.reflection;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReflectionUtils {

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

    public static boolean isPrimitive(Class<?> type){
        return type == int.class || type == long.class || type == double.class || type == float.class
                || type == Integer.class || type == Double.class || type == Long.class || type == Boolean.class
                || type == boolean.class || type ==byte.class || type == char.class || type == short.class
                || type == String.class || type == LocalDate.class || type == LocalDateTime.class;
    }
}
