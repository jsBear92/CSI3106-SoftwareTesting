package tests;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.Calendar;
import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Room;

@ExtendWith(CalendarParameterResolver.class)
@ExtendWith(RoomParameterResolver.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 30, 31, 32 })
    @DisplayName("EQ & BVA Day input testing")
    public void testInvalidDay(int day, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, day, 3, 4));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 12, 13 })
    @DisplayName("EQ & BVA Month input testing")
    public void testInvalidMonth(int month, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(month, 25, 3, 4));
	    String expectedMessage = "Month does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
    
}
