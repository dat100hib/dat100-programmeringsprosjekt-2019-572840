package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;


import java.time.LocalTime;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;


public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START
		 
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latTab= new double[gpspoints.length]; 
		
		for (int i=0; i<latTab.length; i++) {
			
			latTab[i] = gpspoints[i].getLatitude();
		}
			
		
		return latTab;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longTab= new double[gpspoints.length]; 
		
		for (int i=0; i<longTab.length; i++) {
			
			longTab[i] = gpspoints[i].getLongitude();
		}
			
		return longTab;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = toRadians(gpspoint1.getLatitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		
		longitude1 = toRadians(gpspoint1.getLongitude());
		longitude2 = toRadians(gpspoint2.getLongitude());
		
		double longDiff =(longitude2-longitude1);
		double latDiff = (latitude2-latitude1);
		
		
		double a= sin(pow((latDiff/2), 2))+cos(latitude1)*cos(latitude2)*(sin(pow((longDiff/2),2)));
		
		double c = 2*atan2(sqrt(a),sqrt(1-a));
		d=R*c;
		
		
		return d;
		
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		int time1 = gpspoint1.getTime();
		int time2 = gpspoint2.getTime();

		 secs = time2-time1;
		 speed = (distance(gpspoint1, gpspoint2)/secs)*3.6;
		 
		 return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		int minutter = 10;
		double timer = 10;
		
		
		LocalTime timeOfday = LocalTime.ofSecondOfDay(secs);
		timestr = "  " + timeOfday.toString();
		
		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		String kont = String.format("%.2f", d);
		int num = TEXTWIDTH - kont.length();
		String white = "";
		
		for(int i=0; i<num; i++) {
			white = white + " ";
		}
		kont = kont.replaceAll(",",".");
		str = white + kont;
		
		return str;
		// TODO - SLUTT
		
	}
}
