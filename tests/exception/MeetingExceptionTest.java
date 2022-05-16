package tests.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
// to do: use assertions.assertThrow to check what exception is thrown
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
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
public class MeetingExceptionTest {

	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 12, 13 })
    @DisplayName("Month exception")
    public void testInvalidMonth(int month, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(month, 25, 3, 4));
	    String expectedMessage = "Month does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 30, 31, 32 })
    @DisplayName("Day exception")
    public void testInvalidDay(int day, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, day, 3, 4));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("Start hour exception")
    public void testInvalidStartHrs(int startHrs, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 25, startHrs, 4));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    } 
    
	@Order(4)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("End hour exception")
    public void testInvalidEndHrs(int endHrs, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 25, 15, endHrs));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(5)
    @ParameterizedTest
    @ValueSource(strings = { "JO18.330", "ICT30.300", "ML13.218" })
    @DisplayName("Room exception")
    public void testInvalidRoom(String room, Organization org) {
	    Exception exception = assertThrows(Exception.class, ()-> assertTrue(room == org.getRoom(room).getID()));
	    String expectedMessage = "Requested room does not exist";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(6)
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/resources/employees.csv" )
    @DisplayName("Attendee exception")
    public void testInvalidAttendee(String name, Organization org) {
	    Exception exception = assertThrows(Exception.class, ()-> assertTrue(name == org.getEmployee(name).getName()));
	    String expectedMessage = "Requested employee does not exist";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
