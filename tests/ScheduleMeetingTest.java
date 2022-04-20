package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.stream.*;

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
import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray;

@ExtendWith(OrganizationParameterResolver.class)
@ExtendWith(CalendarParameterResolver.class)
@ExtendWith(RoomParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {

	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 12, 13 })
    @DisplayName("EQ / BVA + Exception: Month")
    public void testInvalidMonth(int month, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(month, 25, 3, 4));
	    String expectedMessage = "Month does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 30, 31, 32 })
    @DisplayName("EQ / BVA + Exception: Day")
    public void testInvalidDay(int day, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, day, 3, 4));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("EQ / BVA + Exception: Start Hours")
    public void testInvalidStartHrs(int startHrs, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 25, startHrs, 4));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    } 
    
	@Order(4)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("EQ / BVA + Exception: End Hours")
    public void testInvalidEndHrs(int endHrs, Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 25, 15, endHrs));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(5)
	@Test
//    @ParameterizedTest
//    @CsvFileSource("")
//    @ValueSource(strings = { "JO18.330", "JO7.221" })
    @DisplayName("EQ / BVA + Exception: Room")
    public void testInvalidRoom(Organization org) {
		String[] rooms = { "ICT30.300" };
		
		Stream<String> stream = Arrays.stream(rooms);
		stream.forEach(room -> {
			try {
				assertTrue(room == org.getRoom(room).getID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			}
		});
		
    }
}
