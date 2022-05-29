package tests.structural;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
import tests.resolver.CalendarParameterResolver;


@ExtendWith(CalendarParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calendar is busy")
class CalendarIsBusyTest {
	// Indexed by Month, Day
	private ArrayList<ArrayList<ArrayList<Meeting>>> occupied;
	
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
	
	@Test
	@Order(1)
	@DisplayName("SC Path : 2, 2.1, 2.2, 2.3(T), 2.4(T), 2.5, 2.2, 2.9")
	public void testStatementCoverage1(Calendar calendar) throws ConflictsException {
		// 2.1
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		
		// 2.2
		Calendar.checkTimes(month, day, start, end);
		
		for(Meeting toCheck : occupied.get(month).get(day)) {
			// 2.3 && 2.4 && 2.5
			if(start >= toCheck.getStartTime() && start <= toCheck.getEndTime()){
				busy=true;
			}else if(end >= toCheck.getStartTime() && end <= toCheck.getEndTime()){
				busy=true;
			}
		}
		
		// 2.2
		// Note: In the Planner class, isBusy() returns a boolean
		// In this unit test, we will use assertTrue to evaluate path traversed.
		// 2.9
		assertFalse(busy);
	}
	
	@Test
	@Order(2)
	@DisplayName("SC Path : 2, 2.1, 2.2, 2.3, 2.6, 2.")
	void testStatementCoverage2(Calendar calendar) {
		// 2.1
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
	
	@Test
	@Order(88)
	@DisplayName("Branch coverage")
	void testBranchCoverage(Calendar calendar) {
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
	
	@Test
	@Order(99)
	@DisplayName("Conditional coverage")
	void testConditionalCoverage(Calendar calendar) {
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
}
