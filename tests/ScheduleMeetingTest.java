package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Room;

@ExtendWith(RoomParameterResolver.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {
	@Test
	@DisplayName("Month is less than or equal to 12")
	public void testValidMonth(Room room) {
		Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(12, 25, 4, 5));
	    String actualMessage   = exception.getMessage();
	    assertEquals("", actualMessage);
	}
	
    @Test
    @DisplayName("Day is greater than 30")
    public void testInvalidDay(Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 530, 3, 4));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    

}
