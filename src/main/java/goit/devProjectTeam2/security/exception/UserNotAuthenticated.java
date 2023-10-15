package goit.devProjectTeam2.security.exception;

public class UserNotAuthenticated extends Exception{
	public UserNotAuthenticated(String message) {
		super(message);
	}
}
