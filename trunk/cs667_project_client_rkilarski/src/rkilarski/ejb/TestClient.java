package rkilarski.ejb;

import javax.naming.Context;
import javax.naming.NamingException;

import edu.cs667.rkilarski.request.Request;
import edu.cs667.rkilarski.request.RequestBean;

public class TestClient {

	private static Request request;

	public static void main(String[] args) {
		request = doLookup();
		request.test1();
		request.test2();
	}

	private static Request doLookup() {
		Context context = null;
		Request bean = null;
		try {
			// 1. Obtaining Context
			context = ClientUtility.getInitialContext();
			// 2. Generate JNDI Lookup name
			String lookupName = getLookupName();

			// 3. Lookup and cast
			bean = (Request) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static String getLookupName() {
		/*
		The app name is the EAR name of the deployed EJB without .ear suffix.
		Since we haven't deployed the application as a .ear,
		the app name for us will be an empty string
		*/
		String appName = "";

		/* The module name is the JAR name of the deployed EJB
		without the .jar suffix.
		*/
		String moduleName = "cs667_project_server_rkilarski";

		/*AS7 allows each deployment to have an (optional) distinct name.
		This can be an empty string if distinct name is not specified.
		*/
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = RequestBean.class.getSimpleName();

		// Fully qualified remote interface name
		final String interfaceName = Request.class.getName();

		// Create a look up string name
		String name =
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!"
						+ interfaceName + "?stateful";

		return name;
	}

}
