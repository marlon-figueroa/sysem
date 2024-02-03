package com.app.sysem.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTest {

	@Test
	public void assertArrayTest() {

		String[] arre1 = { "aa", "bb" };
		String[] arre2 = { "aa", "bb" };

		assertArrayEquals(arre1, arre2);
		assertArrayEquals(arre1, arre2);

	}

}
