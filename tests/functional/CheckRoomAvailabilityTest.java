package tests.functional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import au.edu.sccs.csp3105.NBookingPlanner.Room;
import au.edu.sccs.csp3105.NBookingPlanner.Organization;
import au.edu.sccs.csp3105.NBookingPlanner.ConflictsException;

import tests.resolver.OrganizationParameterResolver;



@ExtendWith(OrganizationParameterResolver.class)
class CheckRoomAvailabilityTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 12, 13 })
    @DisplayName("BVA Invalid months")
	void testInvalidMonth(int month, Organization org) {
		for(Room r: org.getRooms()){
			try{
				if(!(r.isBusy(month, 9, 10, 11))){
					assertTrue(true);
					System.out.println(r.getID());
				}
			}catch(ConflictsException e){
				System.out.println(e.getMessage());
				assertTrue(false);
			}
		}

	}
}
