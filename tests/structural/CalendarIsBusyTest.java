package tests.structural;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.Calendar;
import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Person;
import au.edu.sccs.csp3105.NBookingPlanner.Room;
import tests.resolver.CalendarParameterResolver;

// Note
//
// 11, 30 error see Calendar.java at line 43
// occupied.get(9).get(26).add(new Meeting(9, 26, "Apocalypse"));
// System.out.println(occupied.get(month).get(day).get(0).getDescription());


@ExtendWith(CalendarParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calendar is busy")
class CalendarIsBusyTest {
	// Indexed by Month, Day
	private static ArrayList<ArrayList<ArrayList<Meeting>>> occupied;
	
	// This method will run every time a testable method is executed.
	// Think of this as a constructor
	// This will initialize the 2D Calendar array[month[], date[]]
	// and add those special dates below
	@BeforeEach
	public void setup() {
		occupied = new ArrayList<ArrayList<ArrayList<Meeting>>>();
	
		for(int i=0;i<=13;i++){
			// Initialize month
			occupied.add(new ArrayList<ArrayList<Meeting>>());
			for(int j=0;j<=32;j++){
				// Initialize days
				occupied.get(i).add(new ArrayList<Meeting>());
			}
		}
		
		/** Not every month should have 31 days. 
		 * We can deal with this in a hack-ish manner by 
		 * putting in all-day meetings for those dates.
		 */
		occupied.get(2).get(29).add(new Meeting(2,29,"Day does not exist"));
		occupied.get(2).get(30).add(new Meeting(2,30,"Day does not exist"));
		occupied.get(2).get(31).add(new Meeting(2,31,"Day does not exist"));
		occupied.get(4).get(31).add(new Meeting(4,31,"Day does not exist"));
		occupied.get(6).get(31).add(new Meeting(6,31,"Day does not exist"));
		occupied.get(9).get(31).add(new Meeting(9,31,"Day does not exist"));
		occupied.get(11).get(30).add(new Meeting(11,31,"Day does not exist"));
		occupied.get(11).get(31).add(new Meeting(11,31,"Day does not exist"));
	}
	
	
	// Method definition for all unit test.
	// This method is used to all SCPaths
	public Boolean testIsBusy(int month, int day, int start, int end) throws ConflictsException {
		// 2.1
		Boolean busy = false;

		// 2.2
		Calendar.checkTimes(month, day, start, end);
		
		// 2.3
		for(Meeting toCheck : occupied.get(month).get(day)) {
			// 2.4 && 2.5
			if(start >= toCheck.getStartTime() && start <= toCheck.getEndTime()){
				// 2.6
				busy=true;
			 // 2.7 && 2.8
			}else if(end >= toCheck.getStartTime() && end <= toCheck.getEndTime()){
				// 2.9
				busy=true;
			}
		}
		
		// 2.10
		return busy;
	// 2.11 Exit
	}
	
	@Test
	@DisplayName("SC Path 1: 2.1, 2.2, 2.3(F), 2.10, 2.11")
	public void testSCPath1() {
		CalendarIsBusyTest SCPath1 = new CalendarIsBusyTest();
		try {
			System.out.println("SC Path 1: 2.1, 2.2, 2.3(F), 2.10, 2.11");
			System.out.println("Class and method under test: Calendar.isBusy()");
			System.out.println("Input: 9, 26, 10, 11");
			System.out.println("busy: " + SCPath1.testIsBusy(9, 26, 10, 11));
			System.out.println("===============================================================");
		} catch (ConflictsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	@DisplayName("SC Path 2: 2.1, 2.2, 2.3(T), 2.4(T), 2.5(T), 2.6, 2.3(F), 2.10, 2.11")
	public void testSCPath2() {
		ArrayList<Person> attendees = new ArrayList<Person>();
		CalendarIsBusyTest SCPath2 = new CalendarIsBusyTest();
		Room room = new Room();
		Organization org = new Organization();
		
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
		
		try {
			attendees.add(a1);
			attendees.add(a2);
			
			// Add new event at month(9), day(26) with start time of 8 and end time of 8
			occupied.get(9).get(26).add(new Meeting(9, 26, 8, 9, attendees, room, "Apocalypse: The day of reckoning"));
			System.out.println("SC Path 2: 2.1, 2.2, 2.3(T), 2.4(T), 2.5(T), 2.6, 2.3(F), 2.10, 2.11");
			System.out.println("Class and method under test: Calendar.isBusy()");
			System.out.println("Description: Test if start (9) is greater than or equal to new date start & less than or equal to new date end time");
			System.out.println("Input: 9, 26, 9, 7");
			System.out.println("busy: " + SCPath2.testIsBusy(9, 26, 9, 10));
			System.out.println("===============================================================");
		} catch (ConflictsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("SC Path 3: 2.1, 2.2, 2.3(T), 2.4(F), 2.7(T), 2.8(T), 2.9, 2.3, 2.10, 2.11")
	public void testSCPath3() {
		ArrayList<Person> attendees = new ArrayList<Person>();
		CalendarIsBusyTest SCPath3 = new CalendarIsBusyTest();
		Room room = new Room();
		Organization org = new Organization();
		
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
		
		try {
			attendees.add(a1);
			attendees.add(a2);
			
			// Add new event at month(9), day(26) with start time of 8 and end time of 8
			occupied.get(9).get(26).add(new Meeting(9, 26, 8, 9, attendees, room, "Apocalypse: The day of reckoning"));
			System.out.println("SC Path 3: 2.1, 2.2, 2.3(T), 2.4(F), 2.7(T), 2.8(T), 2.9, 2.3, 2.10, 2.11");
			System.out.println("Class and method under test: Calendar.isBusy()");
			System.out.println("Description: Test if end (9) is greater than or equal to new date start & less than or equal to new date end time");
			System.out.println("Input: 9, 26, 8, 10");
			System.out.println("busy: " + SCPath3.testIsBusy(9, 26, 8, 9));
			System.out.println("===============================================================");
		} catch (ConflictsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
