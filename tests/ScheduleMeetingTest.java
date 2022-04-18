package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Room;

@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {
	  @Test
	  @DisplayName("Day is greater than 30")
	  public void testIncorrectDay() {
		  Room where = new Room();
		  Exception exception = assertThrows(ConflictsException.class, ()-> where.isBusy(5, 503, 3, 4));
		  
		  String expectedMessage = "Day does not exist.";
		  String actualMessage   = exception.getMessage();
		  
		  assertTrue(actualMessage.contains(expectedMessage));
	  }

}
