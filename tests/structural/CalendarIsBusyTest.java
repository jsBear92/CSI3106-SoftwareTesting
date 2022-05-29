package tests.structural;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.Calendar;
import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import au.edu.sccs.csp3105.NBookingPlanner.Meeting;
import au.edu.sccs.csp3105.NBookingPlanner.Planner;
import tests.resolver.CalendarParameterResolver;

//Note
//
//11, 30 error see Calendar.java at line 43
//occupied.get(9).get(26).add(new Meeting(9, 26, "Apocalypse"));
//System.out.println(occupied.get(month).get(day).get(0).getDescription());


@ExtendWith(CalendarParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calendar is busy")
class CalendarIsBusyTest {
	// Indexed by Month, Day
	private static ArrayList<ArrayList<ArrayList<Meeting>>> occupied;
	
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
	
	
	// Event date  9, 26, 10, 11
	// This unit test should pass since there is no event booked for  9, 26
	public Boolean dateIsNotBusy() throws ConflictsException {
		// 2.1
		int month = 9;
		int day   = 26;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		
		// 2.2
		Calendar.checkTimes(month, day, start, end);
		
		// 2.3
		for(Meeting toCheck : occupied.get(month).get(day)) {
			if(start >= toCheck.getStartTime() && start <= toCheck.getEndTime()){
				busy=true;
			}else if(end >= toCheck.getStartTime() && end <= toCheck.getEndTime()){
				busy=true;
			}
		}
		
		// 2.10
		return busy;
	// 2.11 Exit
	}
	
	@Test
	@DisplayName("SC Path 1: 2.1, 2.2, 2.3(F), 2.10, 2.11")
	public void dateIsNotBusyDriver() {
		CalendarIsBusyTest SCPath1 = new CalendarIsBusyTest();
		try {
			System.out.println(SCPath1.dateIsNotBusy());
		} catch (ConflictsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
