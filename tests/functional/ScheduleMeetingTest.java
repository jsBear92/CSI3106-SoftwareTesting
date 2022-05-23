package tests.functional;


import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Person;
import au.edu.sccs.csp3105.NBookingPlanner.Room;

import tests.resolver.OrganizationParameterResolver;
import tests.resolver.RoomParameterResolver;

@ExtendWith(RoomParameterResolver.class)
@ExtendWith(OrganizationParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Schedule a meeting test")
public class ScheduleMeetingTest {

	@Order(1)
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 12, 13 })
    @DisplayName("BVA Invalid months")
    public void testInvalidMonth(int month, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int day = 25;
		int start = 10;
		int end = 11;
		String msg = null;
		String where = "JO18.330";
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }
	
	@Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 31, 32 })
    @DisplayName("EQ / BVA + Exception: Day")
    public void testInvalidDay(int day, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int start = 10;
		int end = 11;
		String msg = null;
		String where = "JO18.330";
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }
	
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("BVA Invalid start hour")
    public void testInvalidStartHours(int start, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 25;
		int end = 11;
		String msg = null;
		String where = "JO18.330";
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }
	
	@Order(3)
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 23, 24 })
    @DisplayName("BVA Invalid end hour")
    public void testInvalidEndHours(int end, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 25;
		int start = 10;
		String msg = null;
		String where = "JO18.330";
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }
	
	
	@Order(4)
    @ParameterizedTest
    @ValueSource(strings = { "Curtin 112", "JO18.330", "jO18.330", "ML13.218" })
    @DisplayName("BVA Invalid room")
    public void testInvalidRoom(String where, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 25;
		int start = 10;
		int end = 11;
		String roomErr = null;
		String msg = null;
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			roomErr = e1.getMessage();
			System.out.println(roomErr);
			System.out.println("=====================");
			fail();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee("Ashley Martin");
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }
	
	@Order(5)
    @ParameterizedTest
    @ValueSource(strings = { "Kurt Cobain", "Ashley Martin", "Edith Cowan", "Elon Musk" })
    @DisplayName("BVA Invalid attendee")
    public void testInvalidAttendee(String who, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		int month = 9;
		int day = 25;
		int start = 10;
		int end = 11;
		String where = "JO18.330";
		String attendeeErr = null;
		String msg = null;
		
		try {
			room = org.getRoom(where);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Person a1 = null;
		Person a2 = null;
		
		try {
			a1 = org.getEmployee(who);
			a2 = org.getEmployee("Mark Colin");
		} catch (Exception e) {
			attendeeErr = e.getMessage();
			System.out.println(attendeeErr);
			System.out.println("=====================");
			fail();
		}
		
		attendees.add(a1);
		attendees.add(a2);
		Meeting meeting = new Meeting(month, day, start, end, attendees, room, "Initial meeting");
		
		try {
			room.addMeeting(meeting);
			for(Person employee: attendees) {
				employee.addMeeting(meeting);
			}
			msg = room.printAgenda(month, day);
			System.out.println(msg);
			assertTrue(true);
		} catch(ConflictsException e){
			msg = e.getMessage();
			System.out.println(msg);
			System.out.println("=====================");
			assertTrue(false);
		}
		
		System.out.println("=====================");
    }

}
