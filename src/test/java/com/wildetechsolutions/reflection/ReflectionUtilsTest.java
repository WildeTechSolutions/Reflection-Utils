package com.wildetechsolutions.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionUtilsTest {
    @Test
    public void test_simple_non_nested_field(){
        class TestClass {
            private int simpleField;
        }
        try {
            Class<?> fieldType = ReflectionUtils.getFieldType(TestClass.class, "simpleField");
            assertEquals(int.class, fieldType);
        } catch (NoSuchFieldException e) {
            fail("Field not found");
        }
    }

    @Test
    public void test_retrieve_nested_field_type() {
        class ExpectedNestedFieldType {
            private int nestedField;
        }
        class NestedObject {
            private ExpectedNestedFieldType field;
        }
        try {
            Class<?> result = ReflectionUtils.getFieldType(NestedObject.class, "field.nestedField");
            assertEquals(int.class, result);
        } catch (NoSuchFieldException e) {
            fail("Field not found");
        }
    }

    @Test
    public void test_no_such_field_exception() {
        class TestClass {
            private int existingField;
        }
        assertThrows(NoSuchFieldException.class, () -> {
            ReflectionUtils.getFieldType(TestClass.class, "nonExistingField");
        });
    }

    @Test
    public void test_retrieve_non_nested_field() {
        class TestClass {
            private int simpleField;
        }
        try {
            Field result = ReflectionUtils.getField(TestClass.class, "simpleField");
            assertEquals("simpleField", result.getName());
            assertEquals(int.class, result.getType());
        } catch (NoSuchFieldException e) {
            fail("Field not found");
        }
    }

    @Test
    public void test_deeply_nested_field_retrieval() {
        class OuterClass {
            class InnerClass {
                public String deepField;
            }
            InnerClass inner = new InnerClass();
        }
        try {
            Field field = ReflectionUtils.getField(OuterClass.class, "inner.deepField");
            assertEquals("deepField", field.getName());
        } catch (NoSuchFieldException e) {
            fail("Field not found");
        }
    }
}
