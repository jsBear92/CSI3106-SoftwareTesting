package tests.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



/**
 * Test Planner.inputOutput() method functionality
 *
 */
class IOExceptionTest {
	
	/**
	 * //	getClass().getSimpleName()
	 * Passes a prompt to the user and returns the user specified 
	 * string.
	 * @param message - User input.
	 * @return String - Entered string.
	 */
	private String inputOutput(String message) {
		System.out.println(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String returnString = "";
		try {
			returnString = br.readLine();
		}
		catch (IOException e){
			System.out.println("Error reading in value");
		}
		return returnString;
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "", "1" })
	@DisplayName("Test IOException")
	public void testUserInput(String testInput) throws IOException {
		InputStream stream = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		if (br.readLine() == null) {
			System.out.println("Error reading in value");
			assertTrue(false);
		} else {
			System.out.println(br.readLine());
			assertTrue(true);
		}
	}
}