package com.kaczmarz.kontawalutowe.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    void testIsNotAnAdultAccordingToPESEL(){
        String underagePESEL = "12220211111";
        assertFalse(userService.isOfAge(underagePESEL));
    }

    @Test
    void testIsAnAdultAccordingToPESEL(){
        String fullAgePESEL = "99101211111";
        assertTrue(userService.isOfAge(fullAgePESEL));
    }
}