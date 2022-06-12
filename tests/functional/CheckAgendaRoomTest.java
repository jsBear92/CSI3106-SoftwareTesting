package tests.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.Room;
import tests.resolver.OrganizationParameterResolver;

@ExtendWith(OrganizationParameterResolver.class)
public class CheckAgendaRoomTest  {
	
	@Order(1)
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 1, 12, 13, 14})
	@DisplayName("[BVA] Valid month in Check Agenda Room")
	void t1ValidMonth(int month, Organization org) {
		for(Room room: org.getRooms()) {
			try {
				Assertions.assertEquals(room.printAgenda(month), "Agenda for "+month+":\n");
			} catch (IndexOutOfBoundsException e) {
				Assertions.assertTrue(false);
			}
		}
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {"first", "1"})
	@DisplayName("[EQ] Valid month in Check Agenda Room")
	void t2ValidMonth(String month, Organization org) {
		for(Room room: org.getRooms()) {
			try {
				Assertions.assertEquals(room.printAgenda(Integer.parseInt(month)), "Agenda for "+month+":\n");
			} catch (NumberFormatException e) {
				Assertions.assertTrue(false);
			}
		}
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 1, 31, 32, 33})
	@DisplayName("[BVA] Valid day in Check Agenda Room")
	void t3ValidDay(int day, Organization org) {
		for(Room room: org.getRooms()) {
			try {
				Assertions.assertEquals(room.printAgenda(1, day), "No Meetings booked on this date.\n\n");
			} catch (IndexOutOfBoundsException e) {
				Assertions.assertTrue(false);
			}
		}
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(strings = {"first", "1"})
	@DisplayName("[EQ] Valid day in Check Agenda Room")
	void t4ValidDay(String day, Organization org) {
		for(Room room: org.getRooms()) {
			try {
				Assertions.assertEquals(room.printAgenda(1, Integer.parseInt(day)), "No Meetings booked on this date.\n\n");
			} catch (NumberFormatException e) {
				Assertions.assertTrue(false);
			}
		}
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(strings = {"nothing", "JO7.221"})
	@DisplayName("[EQ] Valid Room name in Check Agenda Room")
	void t5ValidName(String name, Organization org) {
		try {
			for(int i=0; i<12; i++) {
				if(org.getRoom(name) == org.getRooms().get(i)) {
					Assertions.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			Assertions.assertTrue(false);
		}
	}
}
