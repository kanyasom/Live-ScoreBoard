package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class JunitClass {
	
	@Test
	public void setup() {
		String str = "Setting up JUnit";
		assertEquals("Setting up JUnit",str);
	}
}
