package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
//import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Room;


public class ScheduleMeetingTest {
	/**
	 * Expecting to throw an exception when given day is greater than 30
	 * Schedule a meeting at the month of 5, day of 504,
	 * start hours of 3, and end hour of 4.
	 * Will not proceed to prompt for attendees
	 */
  @Test
  @DisplayName("Schedule a meeting at month of 5, day of 503, start hour of 3, and end hour for 4")
  public void testIncorrectDay() {
	  Room where = new Room();
	  assertThrows(ConflictsException.class, ()-> where.isBusy(5, 503, 3, 4));
  }


  
}
