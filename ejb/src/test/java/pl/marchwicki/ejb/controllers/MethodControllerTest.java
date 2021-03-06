package pl.marchwicki.ejb.controllers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pl.marchwicki.ejb.controllers.MethodControllerLocal.Operator;

public class MethodControllerTest {

	MethodControllerLocal controller;

	@BeforeClass
	public void setup() throws NamingException {
		InitialContext ctx = new InitialContext();
		controller = (MethodControllerLocal) ctx
				.lookup("MethodControllerLocal");
	}

	@Test
	public void notSupportedOperation() {
		String calculate = controller.calculate(Operator.MULTIPLY,
				new String[] { "1", "2" });
		Assert.assertEquals("501 Not Implemented", calculate);
	}

	@Test
	public void convertionException() {
		String calculate = controller.calculate(Operator.ADD, new String[] {
				"1", "2", "foo", "bar" });
		Assert.assertEquals("400 Bad Request", calculate);
	}

	@Test
	public void successfulRequest() {
		String calculate = controller.calculate(Operator.ADD, new String[] {
				"1", "2", "3", "4" });
		Assert.assertEquals("200 OK", calculate);
	}

}
