/**
 * Author : Duminda Gunasekara
 * Date : August 19, 2009
 * Purpose : This is the interface for the Log4j logger
 */

package ca.sait.oosd.util;

public class ArgumentAssertionChecker {

	public static void assertNotNull(Object object) throws TENullValueException {
		if(object == null) {
			throw new TENullValueException("The value of the obejct could not be empty");
		}
	}
}
