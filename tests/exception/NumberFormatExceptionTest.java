package tests.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberFormatExceptionTest {

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 6, 7 })
	void testValidUserInput(int userInput) {
		try {
			if (userInput >= 0 && userInput <=6) {
				if (userInput == 1) {
					System.out.println("this.scheduleMeeting();");
				}
				if (userInput == 2){
					System.out.println("this.scheduleVacation();");
				}
				if (userInput == 3){
					System.out.println("this.checkRoomAvailability();");
				}
				if (userInput == 4){
					System.out.println("this.checkEmployeeAvailability();");
				}
				if (userInput == 5){
					System.out.println("this.checkAgendaRoom();");
				}
				if (userInput == 6){
					System.out.println("this.checkAgendaPerson();");
				}
				if (userInput == 0) {
					System.out.println("System.exit(0);");
				} else {
					System.out.println("Please enter a number from 0 - 6");
					System.out.println("this.mainMenu();");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number from 0 - 6");
		}
	}
}
