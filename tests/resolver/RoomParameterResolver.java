package tests.resolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import au.edu.sccs.csp3105.NBookingPlanner.Room;

public class RoomParameterResolver implements ParameterResolver {
	
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		// TODO Auto-generated method stub
		
		return parameterContext.getParameter().getType() == Room.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		// TODO Auto-generated method stub
		return new Room();
	}

}
