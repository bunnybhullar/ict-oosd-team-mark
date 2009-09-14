package ca.sait.oosd.components;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Validator {
	
	//validate for empty string
	public static void isEmptyString(String caption, String value) throws ValidatorException{
		if(value.trim().equals("") || value.trim().equals(null)) {
			throw new ValidatorException("The " + caption + " can not be empty.");
			
		}
	}
	
	//valid number
	public static void isValidNumber(String caption, String price) throws ValidatorException{
		try {
			new BigDecimal(price);
			
		} catch(Exception exp) {
			throw new ValidatorException("The " + caption + " must be a valid number.", exp);
			
		}
	}
	
	//empty dates
	public static void isEmptyDate(String caption, Date date) throws ValidatorException{
		if(date.equals("") || date.equals(null)) {
			throw new ValidatorException("The " + caption + " must be a valid date.");
			
		}
	}
	
	//compare dates
	public static void compareDates(String startDateCaption, String endDateCaption, Date startDate, Date endDate) throws ValidatorException{
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();

		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		
		if(startCalendar.after(endCalendar)) {
			throw new ValidatorException("The " + endDateCaption + " must come after the " + startDateCaption);
		}
	}
	
	//compare price
	public static void comparePrices(String basePriceCaption, String agencyCommCaption, 
			BigDecimal basePrice, BigDecimal agencyComm) throws ValidatorException{
		
		if(basePrice.doubleValue() <= agencyComm.doubleValue()) {
			throw new ValidatorException("The " + agencyCommCaption + " must not be greater than " + basePriceCaption);
			
		}
	}
	
}
