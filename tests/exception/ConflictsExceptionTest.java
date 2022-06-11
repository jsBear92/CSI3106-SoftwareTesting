package tests.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Person;
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
class ConflictsExceptionTest {
	
	/**
	 * Test case number 6
	 * Utility function for test case no. 7
	 * @param month
	 * @param org
	 * @param room
	 */
    public void addMeeting(Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 26;
		int start = 5;
		int end = 6;
		String msg = null;
		String where = "JO18.330";
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Person a1 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
		}
		
		System.out.println("=====================");
    }
    
	/**
	 * Overload method
	 * Test case number 6
	 * Utility function for test case no. 7
	 * @param month
	 * @param org
	 * @param room
	 */
    public void addMeeting(Organization org, Room room, String roomId) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 26;
		int start = 5;
		int end = 6;
		String msg = null;
		String where = "";
		
		if (roomId.isEmpty()) {
			where = "JO18.330";
		} else {
			where = roomId;
		}
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Person a1 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
		}
		
		System.out.println("=====================");
    }

	@Order(1)
    @Test
    @DisplayName("1: Month exception")
    public void testInvalidMonth(Room room) {
		int month = 14;
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(month, 4, 5, 6));
	    String expectedMessage = "Month does not exist.";
	    String actualMessage   = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(2)
    @Test
    @DisplayName("2: Day exception")
    public void testInvalidDay(Room room) {
		int day = 0;
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, day, 5, 6));
	    String expectedMessage = "Day does not exist.";
	    String actualMessage   = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(actualMessage.contains(expectedMessage));
    }
    
	@Order(3)
	@Test
    @DisplayName("3: Start hour exception")
    public void testInvalidStartHrs(Room room) {
		int startHrs = -1;
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 4, startHrs, 6));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(actualMessage.contains(expectedMessage));
    } 
    
	@Order(4)
	@Test
    @DisplayName("4: Meeting starts before it ends exception")
    public void testInvalidMeetingStartEndTimes(Room room) {
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 25, 2, 1));
	    String expectedMessage = "Meeting starts before it ends.";
	    String actualMessage   = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(5)
	@Test
    @DisplayName("5: End hour exception")
    public void testInvalidEndHrs(Room room) {
		int endHrs = 99;
	    Exception exception = assertThrows(ConflictsException.class, ()-> room.isBusy(5, 4, 5, endHrs));
	    String expectedMessage = "Illegal hour.";
	    String actualMessage   = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(actualMessage.contains(expectedMessage));
    }
	
	@Order(7)
	@Test
    @DisplayName("7: Overlap room exception")
    public void testOverlapRoomMeeting(Room room) {
		Organization org = new Organization();
		this.addMeeting(org, room);
		// Add the same meeting which triggers the room overlap conflict exception
		// This will not return since the addMeeting return type is void
		this.addMeeting(org, room);
    }
	
	@Order(8)
	@Test
    @DisplayName("8: Attendee meeting conflict exception")
    public void testAttendeeConflict(Room room) {
		Organization org = new Organization();
		this.addMeeting(org, room);
		// Add meeting with the same details except the room id which will trigger the attendee conflict overlap exception
		// This will not return since the addMeeting return type is void
		this.addMeeting(org, room, "JO15.236");
    }
}
