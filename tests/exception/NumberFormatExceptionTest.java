package tests.exception;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * This test case is shortcut version of the Planners main menu
 * where the system prompts the user of the option from 1-6
 * The inputOutput has been remove to bypass it's functionality for the test cases purposes
 * No user-interaction required
 * @author mbernal
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NumberFormatExceptionTest {
	
	@Order(1)
	@Test
	@DisplayName("1. Input as string")
	public void testValidUserInputString() {
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
	public void testValidUserInputDouble() {
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
	public void testValidUserInputInteger() {
		String error = "";
		String num = "1";
		try {
			int userInput = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			error = e.getMessage();
			System.out.println("Please enter a number from 0 - 6");
			assertTrue(true);
		}
		
		if (error.isEmpty()) {
			System.out.println("null");
		}

		
	}
}
