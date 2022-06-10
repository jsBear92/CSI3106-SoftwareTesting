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


class IOExceptionTest {
	@ParameterizedTest
	@ValueSource(strings = { "", "1", "Ashley Martin" })
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
