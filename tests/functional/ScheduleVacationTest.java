package tests.functional;

import static org.junit.jupiter.api.Assertions.*;

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
@DisplayName("Schedule a vacation test")
class ScheduleVacationTest {
	@Order(1)
    @ParameterizedTest
	@ValueSource(ints = { 0, 1, 5, 12, 13 })
    @DisplayName("BVA Invalid start month")
	void testInvalidStartMonth(int sMonth, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		Person who = new Person();
		int eDay = 10;
		String name = "Ashley Martin";
		if(!name.equals("cancel")){
			try{
				who = org.getEmployee(name);
				attendees.add(who);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		for (int day=1;day<=eDay;day++){
			Meeting meeting = new Meeting(sMonth,day,0,23,attendees, room,"vacation");
			try {
				who.addMeeting(meeting);
				assertTrue(true);
			} catch(ConflictsException e){
				System.out.println(e.getMessage());
				assertTrue(false);
			}
		}
	}
	
	@Order(2)
    @ParameterizedTest
	@ValueSource(ints = { 0, 1, 29, 30, 31, 32 })
    @DisplayName("BVA Invalid start day")
	void testInvalidStartDay(int sDay, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		Person who = new Person();
		int sMonth = 5;
		String name = "Ashley Martin";
		if(!name.equals("cancel")){
			try{
				who = org.getEmployee(name);
				attendees.add(who);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		Meeting meeting = new Meeting(sMonth, sDay, 0, 23, attendees, room, "vacation");
		try {
			who.addMeeting(meeting);
			assertTrue(true);
		} catch (ConflictsException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	@Order(3)
    @ParameterizedTest
	@ValueSource(ints = { 0, 1, 5, 12, 13 })
    @DisplayName("BVA Invalid end month")
	void testInvalidEndMonth(int eMonth, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		Person who = new Person();
		int sDay = 1;
		String name = "Ashley Martin";
		if(!name.equals("cancel")){
			try{
				who = org.getEmployee(name);
				attendees.add(who);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		Meeting meeting = new Meeting(eMonth, sDay, 0, 23, attendees, room, "vacation");
		try {
			who.addMeeting(meeting);
			assertTrue(true);
		} catch (ConflictsException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	@Order(4)
    @ParameterizedTest
	@ValueSource(ints = { 0, 1, 29, 30, 31, 32 })
    @DisplayName("BVA Invalid end day")
	void testInvalidEndDay(int eDay, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		Person who = new Person();
		int sMonth = 5;
		String name = "Ashley Martin";
		if(!name.equals("cancel")){
			try{
				who = org.getEmployee(name);
				attendees.add(who);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		Meeting meeting = new Meeting(sMonth, eDay, 0, 23, attendees, room, "vacation");
		try {
			who.addMeeting(meeting);
			assertTrue(true);
		} catch (ConflictsException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	@Order(5)
    @ParameterizedTest
	@ValueSource(strings = { "Ashley Martin", "Elon Musk", "ashley martin" })
    @DisplayName("BVA Invalid attendee")
	void testInvalidAttendee(String name, Organization org, Room room) {
		ArrayList<Person> attendees = new ArrayList<Person>();
		Person who = new Person();
		int sMonth = 5;
		int eDay = 10;
		if(!name.equals("cancel")){
			try{
				who = org.getEmployee(name);
				attendees.add(who);
			}catch(Exception e){
				System.out.println(e.getMessage());
				assertTrue(false);
			}
		}

		for (int day=1;day<=eDay;day++){
			Meeting meeting = new Meeting(sMonth,day,0,23,attendees, room,"vacation");
			try {
				who.addMeeting(meeting);
				assertTrue(true);
			} catch(ConflictsException e){
				System.out.println(e.getMessage());
				assertTrue(false);
			}
		}
	}
}
