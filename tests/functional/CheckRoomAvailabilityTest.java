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
    	String err = null;
    	
		for(Room r: org.getRooms()){
			try{
				if(!(r.isBusy(month, 9, 10, 11))){
					System.out.println(r.getID());
					err = null;
				}
			}catch(ConflictsException e){
				err = e.getMessage();
				System.out.println(err);
				System.out.println("=====================");
			}
			
//			Line 29 uses inverse operation that is why we use assertFalse here
			if (err!=null) {
				assertFalse(true);
			} else {
				assertFalse(false);
			}
		}
		System.out.println("=====================");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 31, 32 })
    @DisplayName("BVA Invalid days")
	void testInvalidDay(int day, Organization org) {
    	String err = null;
    	
		for(Room r: org.getRooms()){
			try{
				if(!(r.isBusy(9, day, 10, 11))){
					System.out.println(r.getID());
					err = null;
				}
			}catch(ConflictsException e){
				err = e.getMessage();
				System.out.println(err);
				System.out.println("=====================");
			}
			
//			Line 29 uses inverse operation that is why we use assertFalse here
			if (err!=null) {
				assertFalse(true);
			} else {
				assertFalse(false);
			}
		}
		System.out.println("=====================");
    }
    
}
