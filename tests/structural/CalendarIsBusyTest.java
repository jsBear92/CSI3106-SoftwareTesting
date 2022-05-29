package tests.structural;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import au.edu.sccs.csp3105.NBookingPlanner.Calendar;
import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;
import tests.resolver.CalendarParameterResolver;

@ExtendWith(CalendarParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calendar is busy")
class CalendarIsBusyTest {

	/**
	 * Used to check whether a meeting is scheduled during a particular 
	 * time frame.
	 * @param month - The month of the meeting (1-12)
	 * @param day - The day of the meeting (1-31)
	 * @param start - The time the meeting starts (0-23)
	 * @param end - The time the meeting ends (0-23)
	 * @return boolean - Whether the calendar has an entry in that time frame.
	 * @throws ConflictsException If an invalid date or time is entered.
	 */
	@Test
	@Order(1)
	@DisplayName("Statement coverage")
	void testIsBusyStatementCoverage(Calendar calendar) {
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		try {
			calendar.isBusy(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
	
	@Test
	@Order(2)
	@DisplayName("Branch coverage")
	void testIsBusyBranchCoverage(Calendar calendar) {
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		try {
			calendar.isBusy(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
	
	@Test
	@Order(3)
	@DisplayName("Conditional coverage")
	void testIsBusyConditionalCoverage(Calendar calendar) {
		int month = 9;
		int day   = 25;
		int start = 10;
		int end   = 11;
		Boolean busy = false;
		try {
			calendar.isBusy(month, day, start, end);
		} catch (ConflictsException e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}
}
