package tests.exception;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * This test case is shortcut version of the Planners main menu
 * where the system prompts the user of the option from 1-6
 * The inputOutput has been remove to bypass it's functionality for the test cases purposes
 * No user-interaction required
 * @author mbernal
 *
 */
class NumberFormatExceptionTest {
	@Order(1)
	@Test
	@DisplayName("1. Input as string")
	void testValidUserInputString() {
		try {
			int userInput = Integer.parseInt("One");
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number from 0 - 6");
			assertTrue(true);
		}
	}

	@Order(2)
	@Test
	@DisplayName("2. Input as double")
	void testValidUserInputDouble() {
		String num = "1.1";
		try {
			int userInput = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number from 0 - 6");
			assertTrue(true);
		}
	}

	@Order(3)
	@Test
	@DisplayName("3. Input as integer")
	void testValidUserInputInteger() {
		String num = "1";
		try {
			int userInput = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number from 0 - 6");
			assertTrue(true);
		}
	}
}
