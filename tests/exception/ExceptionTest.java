package tests.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.Organization;
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
class ExceptionTest {
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
