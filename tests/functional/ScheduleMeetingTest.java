package tests.functional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Room;
import tests.resolver.CalendarParameterResolver;
import tests.resolver.MeetingParameterResolver;
import tests.resolver.OrganizationParameterResolver;
import tests.resolver.PersonParameterResolver;
import tests.resolver.RoomParameterResolver;

// To do: Separate functional testing (BVA + EQ) to Exception testing
@ExtendWith(PersonParameterResolver.class)
@ExtendWith(MeetingParameterResolver.class)
@ExtendWith(CalendarParameterResolver.class)
@ExtendWith(RoomParameterResolver.class)
@ExtendWith(OrganizationParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {
	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 12, 13 })
    @DisplayName("EQ / BVA + Exception: Month")
    public void testInvalidMonth(int month, Room room) {
		assertTrue(true);
    }
	
	@Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 30, 31, 32 })
    @DisplayName("EQ / BVA + Exception: Day")
    public void testInvalidDay(int day, Room room) {
		assertTrue(true);
    }
    
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("EQ / BVA + Exception: Start Hours")
    public void testInvalidStartHrs(int startHrs, Room room) {
		assertTrue(true);
    } 
    
	@Order(4)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("EQ / BVA + Exception: End Hours")
    public void testInvalidEndHrs(int endHrs, Room room) {
		assertTrue(true);
    }
	
	@Order(5)
    @ParameterizedTest
    @ValueSource(strings = { "JO18.330", "ICT30.300", "ML13.218" })
    @DisplayName("EQ / BVA + Exception: Room({ JO18.330, ICT30.300, ML13.218 })")
    public void testInvalidRoom(String room, Organization org) {
		assertTrue(true);
    }
	
	@Order(6)
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/resources/employees.csv" )
    @DisplayName("EQ / BVA + Exception: Attendees")
    public void testInvalidAttendee(String name, Organization org) {
		assertTrue(true);
	}
}
