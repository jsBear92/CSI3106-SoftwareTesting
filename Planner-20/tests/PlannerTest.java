package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import au.edu.sccs.csp3105.NBookingPlanner.Planner;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;

class PlannerTest {
	Planner planner;
	
	@Test
	@DisplayName("Valid meeting room ID")
	void SM1ValidMeeting() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this ha-ah!
	    Mockito.doNothing().when(planner).mainMenu();
	    
		//set inputs as per table
	    //we are going to mimicking entering "1" for the menu on line 55 by directly calling the 
		int month = 6;
		int day = 10;
		int start = 9;
		int end = 10;
		String roomIn = "ML18.330";
		String personIn = "Mark Colin";
		String complete = "done";
		String description = "Desc";
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),Integer.toString(day),Integer.toString(start),Integer.toString(end),
							roomIn, personIn, complete, description).execute(() -> {
			//assert what we expect to be printed to console, is what is actually observed	
			Assertions.assertEquals(
					"\nEnter the month of the meeting (1-12): \r\n" + "\n"
					+ "Enter the day of the meeting (1-31): \r\n" + "\n"
					+ "Enter the starting hour of the meeting (0-23): \r\n" + "\n"
					+ "Enter the ending hour of the meeting (0-23): \r\n" + "The rooms open at that time are:\r\n"
					+ "JO18.330\r\n" + "JO7.221\r\n" + "JO15.236\r\n" + "JO1.230\r\n" + "JO34.536\r\n" + "JO19.230\r\n"
					+ "ML5.123\r\n" + "ML18.330\r\n" + "ML21.520\r\n" + "ML13.213\r\n" + "ML21.310\r\n" + "ML13.218\r\n"
					+ "\nEnter the desired room ID, or cancel to cancel: \r\n"
					+ "The people available to attend at that time are:\r\n" + "Justin Gardener\r\n" + "Ashley Matthews\r\n"
					+ "Mary Jane Cook\r\n" + "Rose Austin\r\n" + "Mike Smith\r\n" + "Helen West\r\n" + "Steven Lewis\r\n"
					+ "Edith Cowan\r\n" + "Mark Colin\r\n" + "Jacquie Martin\r\n" + "Jaci Johnston\r\n" + "Travis Colin\r\n"
					+ "Ashley Martin\r\n" + "\n" + "Enter a person's name, or done if finished: \r\n" + "\n"
					+ "Enter a person's name, or done if finished: \r\n" + "\n"
					+ "Enter a description for the meeting: \r\n", 
					tapSystemOut(() -> {
						//selections options 1 (schedule meeting)
						planner.scheduleMeeting(); //captures the console output and compares to the expected output on lines 39-51
					  }));
        });
		
	}
	
	@Test
	@DisplayName("Invalid meeting room ID")
	void SM2InvalidMeeting() throws Exception {   
		// make spy
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing
	    Mockito.doNothing().when(planner).mainMenu();
	    
		//set inputs
		int month = 6;
		int day = 10;
		int start = 9;
		int end = 10;
		String roomInInvalid = "ML18.33";
		String roomIn = "ML18.330";
		String personIn = "Mark Colin";
		String complete = "done";
		String description = "Desc";
		
		//buffer input values
		withTextFromSystemIn(Integer.toString(month),Integer.toString(day),Integer.toString(start),Integer.toString(end),
							roomInInvalid, roomIn, personIn, complete, description).execute(() -> {
			//assert what we expect to be printed to console, is what is actually observed	
			Assertions.assertEquals(
					"\nEnter the month of the meeting (1-12): \r\n" + "\n"
					+ "Enter the day of the meeting (1-31): \r\n" + "\n"
					+ "Enter the starting hour of the meeting (0-23): \r\n" + "\n"
					+ "Enter the ending hour of the meeting (0-23): \r\n" + "The rooms open at that time are:\r\n"
					+ "JO18.330\r\n" + "JO7.221\r\n" + "JO15.236\r\n" + "JO1.230\r\n" + "JO34.536\r\n" + "JO19.230\r\n"
					+ "ML5.123\r\n" + "ML18.330\r\n" + "ML21.520\r\n" + "ML13.213\r\n" + "ML21.310\r\n" + "ML13.218\r\n"
					+ "\nEnter the desired room ID, or cancel to cancel: \r"
					+ "\nRequested room does not exist\r\n" //due to incorrect room, new prompt
					+ "\nEnter the desired room ID, or cancel to cancel: \r\n" //due to incorrect room, new prompt
					+ "The people available to attend at that time are:\r\n" + "Justin Gardener\r\n" + "Ashley Matthews\r\n"
					+ "Mary Jane Cook\r\n" + "Rose Austin\r\n" + "Mike Smith\r\n" + "Helen West\r\n" + "Steven Lewis\r\n"
					+ "Edith Cowan\r\n" + "Mark Colin\r\n" + "Jacquie Martin\r\n" + "Jaci Johnston\r\n" + "Travis Colin\r\n"
					+ "Ashley Martin\r\n" + "\n" + "Enter a person's name, or done if finished: \r\n" + "\n"
					+ "Enter a person's name, or done if finished: \r\n" + "\n"
					+ "Enter a description for the meeting: \r\n", 
					tapSystemOut(() -> {
						//selections options 1 (schedule meeting)
						planner.scheduleMeeting(); //captures the console output and compares to the expected output on lines 84-98
					  }));
        });
		
	}
	
	@Test
	@DisplayName("New tests")
	void newTests() throws Exception {   
		// copy and paste the above and alter the expected strings where you need too...
	}
}
