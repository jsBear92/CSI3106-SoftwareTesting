package tests;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Room;

@ExtendWith(RoomParameterResolver.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {
	@Test
	@DisplayName("Month does not exists.")
	public void testValidMonth(Room room) {
		Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(12, 25, 4, 5));
		String expectedMessage = "Month does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
    @Test
    @DisplayName("Day does not exists.")
    public void testInvalidDay(Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 530, 3, 4));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
 
    @ParameterizedTest
    @MethodSource("range")
    @DisplayName("Accepts day 1 to 30")
    public void testValidDay(int day) {
      	assertTrue((day >= 1) && (day <= 30));
    }
    
    static IntStream range() {
    	return IntStream.range(0, 101);
    }
    
    

}
