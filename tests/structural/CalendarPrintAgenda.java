package tests.structural;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Person;
import au.edu.sccs.csp3105.NBookingPlanner.Planner;
import au.edu.sccs.csp3105.NBookingPlanner.Room;
import tests.resolver.CalendarParameterResolver;

@ExtendWith(CalendarParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calendar print agenda")
public class CalendarPrintAgenda {
	// Indexed by Month, Day
		private static ArrayList<ArrayList<ArrayList<Meeting>>> occupied;
		
		Planner planner;
		
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
		
		public static void checkTimes(int mMonth,int mDay,int mStart, int mEnd) throws ConflictsException{
			// Check for illegal dates
			if(mDay< 1 || mDay > 30){
				throw new ConflictsException("Day does not exist.");
			}

			if(mMonth < 1 || mMonth >= 12){
				throw new ConflictsException("Month does not exist.");
			}

			// Check for illegal times
			if(mStart< 0 || mStart >= 23){
				throw new ConflictsException("Illegal hour.");
			}

			if(mEnd < 0 || mEnd > 23){
				throw new ConflictsException("Illegal hour.");
			}

			if(mStart > mEnd){
				throw new ConflictsException("Meeting starts before it ends.");
			}
		}
		
		public void addMeeting(Meeting toAdd) throws ConflictsException{
			int mDay = toAdd.getDay();
			int mMonth = toAdd.getMonth();
			int mStart = toAdd.getStartTime();
			int mEnd = toAdd.getEndTime();
			
			checkTimes(mMonth,mDay,mStart,mEnd);

			
			// Check whether a meeting is already scheduled at this time.
			ArrayList<Meeting> thatDay = occupied.get(mMonth).get(mDay);
			boolean booked = false;
			Meeting conflict = new Meeting();
			
			for(Meeting toCheck : thatDay){
				if(!toCheck.getDescription().equals("Day does not exist")){
					// Does the start time fall between this meeting's start and end times?
					if(mStart >= toCheck.getStartTime() && mStart <= toCheck.getEndTime()){
						booked = true;
						conflict = toCheck;
						// Does the end time fall between this meeting's start and end times?
					}else if(mEnd >= toCheck.getStartTime() && mEnd <= toCheck.getEndTime()){
						booked = true;
						conflict = toCheck;
					}
				}
			}
			
			if(booked){
				throw new ConflictsException("Overlap with another item - "+conflict.getDescription()
					+" - scheduled from "+conflict.getStartTime()+" and "+conflict.getEndTime());
			}else{
				occupied.get(mMonth).get(mDay).add(toAdd);
			}
		}
		
		
		// path start
		public String testPrintAgenda(int month) throws ConflictsException{
			// path 6.1
			String agenda;
			// path 6.2
			if (occupied.get(month).isEmpty()){
				// path 6.3
				agenda = "No Meetings booked for this month.\n\n";	
			}
			else {
				// path 6.6
			     agenda = "Agenda for "+month+":\n";
			    for(ArrayList<Meeting> toPrint : occupied.get(month)){
				 for(Meeting meeting: toPrint){
					 // path 6.7
					agenda = agenda+meeting.toString()+"\n";
				  }
			    }
			}
			// path 6.4
			return agenda;
		} // path 6.5 (End)

		
		// path start
		public String testPrintAgenda(int month, int day){
			// path 7.1 
			String agenda;
			// path 7.2
			if (occupied.get(month).get(day).isEmpty()){
				// path 7.3
				agenda = "No Meetings booked on this date.\n\n";	
			}
			else {
				// path 7.6
				agenda = "Agenda for "+month+"/"+day+" are as follows:\n";
			   for(Meeting toPrint : occupied.get(month).get(day)){
				   // path 7.7
				agenda = agenda+toPrint.toString()+"\n";
			   }
			}
			// path 7.4	
			return agenda; 	
		} // path 7.5 (End)
		
		@Test
		@DisplayName("ST Path(6) 1: 6.1, 6.2(T), 6.3, 6.4 (error - this parameter cannot catch empty)")
		void testSTPath1() {
			CalendarPrintAgenda STPath1 = new CalendarPrintAgenda();
			try {
				System.out.println("===============================================================");
				System.out.println("ST Path(6) 1: 6.1, 6.2(T), 6.3, 6.4");
				System.out.println("Class and method under test: Calendar.printAgenda(int month)");
				System.out.println("Input: 1");
				System.out.println("Result: " + STPath1.testPrintAgenda(1));
				System.out.println("===============================================================");
			} catch (ConflictsException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@DisplayName("ST Path(6) 2: 6.1, 6.2(F), 6.6, 6.7, 6.4")
		void testSTPath2() throws ConflictsException {
			ArrayList<Person> attendees = new ArrayList<Person>();
			Room room = new Room();
			Organization org = new Organization();
			
			// Variables
			int month = 1;
			int day = 3;
			int startTime = 6;
			int endTime = 7;
			String description = "Test";
			String where = "JO18.330";
			
			try {
				room = org.getRoom(where);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Person a = null;
			
			try {
				a = org.getEmployee("Ashley Martin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			attendees.add(a);
			
			Meeting meeting = new Meeting(month,day,startTime,endTime,attendees,room,description);
			CalendarPrintAgenda STPath2 = new CalendarPrintAgenda();
			
			STPath2.addMeeting(meeting);
			System.out.println("===============================================================");
			System.out.println("ST Path(6) 2: 6.1, 6.2(F), 6.6, 6.7, 6.4");
			System.out.println("Class and method under test: Calendar.printAgenda(int month)");
			System.out.println("Input: 1");
			System.out.println("Result: " + STPath2.testPrintAgenda(1));
			System.out.println("===============================================================");
		}
		
		@Test
		@DisplayName("ST Path(7) 1: 7.1, 7.2(T), 7.3, 7.4")
		void testSTPath3() throws ConflictsException {
			CalendarPrintAgenda STPath3 = new CalendarPrintAgenda();
			System.out.println("===============================================================");
			System.out.println("ST Path(7) 1: 7.1, 7.2(T), 7.3, 7.4");
			System.out.println("Class and method under test: Calendar.printAgenda(int month, int day)");
			System.out.println("Input: 1, 1");
			System.out.println("Result: " + STPath3.testPrintAgenda(1, 1));
			System.out.println("===============================================================");
		}
		
		@Test
		@DisplayName("ST Path(7) 2: 7.1, 7.2(F), 7.6, 7.7, 7.4")
		void testSTPath4() throws ConflictsException {		
			ArrayList<Person> attendees = new ArrayList<Person>();
			Room room = new Room();
			Organization org = new Organization();
			
			// Variables
			int month = 1;
			int day = 3;
			int startTime = 6;
			int endTime = 7;
			String description = "Test";
			String where = "JO18.330";
			
			try {
				room = org.getRoom(where);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Person a = null;
			
			try {
				a = org.getEmployee("Ashley Martin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			attendees.add(a);
			
			Meeting meeting = new Meeting(month,day,startTime,endTime,attendees,room,description);
			CalendarPrintAgenda STPath4 = new CalendarPrintAgenda();
			
			STPath4.addMeeting(meeting);
			
			System.out.println("===============================================================");
			System.out.println("ST Path(7) 2: 7.1, 7.2(F), 7.6, 7.7, 7.4");
			System.out.println("Class and method under test: Calendar.printAgenda(int month, int day)");
			System.out.println("Input: 1, 1");
			System.out.println("Result: " + STPath4.testPrintAgenda(1, 3));
			System.out.println("===============================================================");
		}
}
