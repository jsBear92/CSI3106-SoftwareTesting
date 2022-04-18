package tests;

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
	  assertThrows(ConflictsException.class, ()-> where.isBusy(5, 503, 3, 4));
  }

}
