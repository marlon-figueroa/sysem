package com.app.sysem.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalseTest {

	@Test
	public void test1() {

		assertTrue(true);
		assertFalse(false);

	}

	@Test
	public void test2() {

		boolean expresoon1 = "4".equals("4");
		boolean expresion2 = "4".equals("2");

		assertTrue(expresoon1);
		assertFalse(expresion2);

	}

}
