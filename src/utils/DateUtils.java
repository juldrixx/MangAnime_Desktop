package utils;

import i18n.Constants;
import i18n.Messages;

public abstract class DateUtils {

	private DateUtils() {
		
	}
	
	public static String getDayName(int numberDay) {
		switch (numberDay) {
		case 0:
			return Messages.RESOURCE_BUNDLE.getString(Constants.MONDAY);
		case 1:
			return Messages.RESOURCE_BUNDLE.getString(Constants.TUESDAY);
		case 2:
			return Messages.RESOURCE_BUNDLE.getString(Constants.WEDNESDAY);
		case 3:
			return Messages.RESOURCE_BUNDLE.getString(Constants.THURSDAY);
		case 4:
			return Messages.RESOURCE_BUNDLE.getString(Constants.FRIDAY);
		case 5:
			return Messages.RESOURCE_BUNDLE.getString(Constants.SATURDAY);
		case 6:
			return Messages.RESOURCE_BUNDLE.getString(Constants.SUNDAY);
		default:
			return "";
		}
	}
	
	public static String getMonthName(int numberMonth) {
		switch (numberMonth) {
		case 0:
			return Messages.RESOURCE_BUNDLE.getString(Constants.JANUARY);
		case 1:
			return Messages.RESOURCE_BUNDLE.getString(Constants.FEBRUARY);
		case 2:
			return Messages.RESOURCE_BUNDLE.getString(Constants.MARCH);
		case 3:
			return Messages.RESOURCE_BUNDLE.getString(Constants.APRIL);
		case 4:
			return Messages.RESOURCE_BUNDLE.getString(Constants.MAY);
		case 5:
			return Messages.RESOURCE_BUNDLE.getString(Constants.JUNE);
		case 6:
			return Messages.RESOURCE_BUNDLE.getString(Constants.JULY);
		case 7:
			return Messages.RESOURCE_BUNDLE.getString(Constants.AUGUST);
		case 8:
			return Messages.RESOURCE_BUNDLE.getString(Constants.SEPTEMBER);
		case 9:
			return Messages.RESOURCE_BUNDLE.getString(Constants.OCTOBER);
		case 10:
			return Messages.RESOURCE_BUNDLE.getString(Constants.NOVEMBER);
		case 11:
			return Messages.RESOURCE_BUNDLE.getString(Constants.DECEMBER);
		default:
			return "";
		}
	}
}
