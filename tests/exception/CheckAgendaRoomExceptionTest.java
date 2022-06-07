package tests.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Room;
import tests.resolver.CalendarParameterResolver;
import tests.resolver.MeetingParameterResolver;
import tests.resolver.OrganizationParameterResolver;
import tests.resolver.PersonParameterResolver;
import tests.resolver.RoomParameterResolver;

@ExtendWith(PersonParameterResolver.class)
@ExtendWith(MeetingParameterResolver.class)
@ExtendWith(CalendarParameterResolver.class)
@ExtendWith(RoomParameterResolver.class)
@ExtendWith(OrganizationParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckAgendaRoomExceptionTest {

	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 2, 12, 13, 14 })
    @DisplayName("Month exception (Index Error)")
    public void testInvalidMonthIndex(int month, Room room) {
	    Exception exception = assertThrows(IndexOutOfBoundsException.class, ()-> room.printAgenda(month));
	    String expectedMessage = "Index "+ month + " out of bounds for length 14";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 2, 12, 13, 14 })
    @DisplayName("Month exception")
    public void testInvalidMonth(int month, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.printAgenda(month));
	    String expectedMessage = "Month does not exist";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 30, 31, 32, 33 })
    @DisplayName("Day exception (Index Error)")
    public void testInvalidDayIndex(int day, Room room) {
	    Exception exception = assertThrows(IndexOutOfBoundsException.class, ()-> room.printAgenda(5, day));
	    String expectedMessage = "Index "+ day + " out of bounds for length 33";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(4)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 30, 31, 32, 33 })
    @DisplayName("Day exception")
    public void testInvalidDay(int day, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.printAgenda(5, day));
	    String expectedMessage = "Day does not exist";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
}
