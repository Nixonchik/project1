package com.korobkin.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneValidationTest {

    @Test
    public void testIsPhone() throws Exception {
        String phone = "+38-(067)-240-37-08";
        boolean actual = PhoneValidation.isPhone(phone);
        assertEquals(true, actual);
    }

    @Test
    public void testIsPhone1() throws Exception {
        String phone = "(067)240 37 08";
        boolean actual = PhoneValidation.isPhone(phone);
        assertEquals(true, actual);
    }

    @Test
    public void testIsPhone2() throws Exception {
        String phone = "0936947175";
        boolean actual = PhoneValidation.isPhone(phone);
        assertEquals(true, actual);
    }
}