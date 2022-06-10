package tests.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;


class IOExceptionTest {

	@Test
	public void testIOException() throws IOException {
		String testInput = "";
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
