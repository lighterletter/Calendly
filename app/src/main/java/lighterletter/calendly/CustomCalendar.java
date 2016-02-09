package lighterletter.calendly;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by c4q-john on 1/23/16.
 */
public class CustomCalendar {

    //singleton instance
    private static CustomCalendar ourInstance;
    public static CustomCalendar getInstance(Calendar cal, String sdf) {
            ourInstance = new CustomCalendar(cal, sdf );
        return ourInstance;
    }

    //fields
    //general calendar for calculation use
    private Date complete_date;
    private String info;

    private String full_date;
    private String am_pm;
    private String time;

//    private int year = calendar.get(Calendar.YEAR);
//    private int day = calendar.get(Calendar.DAY_OF_WEEK);
    private int day_of_year;
    private int hour_of_day;
    private int hour;
    private int hours_to_year;
    private int timeElapsedYear;
    private String date;


    private CustomCalendar(Calendar cal, String _sdfWatchTime) {
        full_date = _sdfWatchTime;
        day_of_year = cal.get(Calendar.DAY_OF_YEAR);
        hour_of_day = cal.get(Calendar.HOUR_OF_DAY);
        am_pm  = getAM_PM(cal.get(Calendar.AM_PM ));
        hours_to_year = hoursLeftToYear(cal.get(Calendar.HOUR_OF_DAY), day_of_year);
        String minute_split = Arrays.toString(full_date.split(":"));
        String minute_trim = minute_split.substring(minute_split.indexOf(",") + 1);
        String minute = minute_trim.substring(1, 3);
        time = getHour(cal) + ":" + minute + " " + am_pm;
        timeElapsedYear = timeElapsed(hour_of_day, day_of_year);

    }

    //private methods
    private String getAM_PM(int id){
        if(id == 1){
            return "PM";
        } else
            return "AM";
    }
    private int hoursLeftToYear(int time, int day){
        int yearlyHours = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR) * 24;
        int timeElapsed = ((day - 1)*24)+time;
        return yearlyHours - timeElapsed;
    }
    private int timeElapsed(int time, int day){
        return ((day-1)*24)+time;
    }

    //getters
    public int getHour_of_day() {return hour_of_day;}
    public String getInfo(Calendar cal) {
        complete_date = cal.getTime();
        info = complete_date.toString();
        return info;}
    public int getDayOfMonth(Calendar cal){return cal.get(Calendar.DAY_OF_MONTH);}
    public String getDayOfWeek(Calendar cal){return getInfo(cal).substring(0, 3);}
    public String getMonth(Calendar cal){return getInfo(cal).substring(4,7);}
    public int getHour(Calendar cal){
        return cal.get(Calendar.HOUR);
    }
    public String getTime(){return time;}
    public int getHours_to_year() {return hours_to_year;}
    public int getTimeElapsedYear() {return timeElapsedYear;}
    public int getHoursLeftToday(int hour){return 24 - hour;}
    public String getDate(){return full_date.substring(0, 8);}
}
