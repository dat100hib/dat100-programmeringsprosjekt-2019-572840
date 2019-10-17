package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;


	public GPSData(int n) {

		// TODO - START
		antall=0;
		gpspoints = new GPSPoint [n];
		
		// TODO - SLUTT
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		
		if(antall<gpspoints.length) {
			gpspoints[antall]= gpspoint;
			antall++;
			inserted = true;
			
		}
		
	      return inserted;

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START
		String hrStr = time.substring(11,13);
		int hr = Integer.parseInt(hrStr);
		
		String minStr = time.substring(14,16);
		int min = Integer.parseInt(minStr);
		
		String secStr = time.substring(17,19);
		int sec = Integer.parseInt(secStr);
		
		int secs = hr*60*60 + min*60 + sec;
		
		int timeInt = secs;
		double latDouble = Double.parseDouble(latitude);
		double lonDouble = Double.parseDouble(longitude);
		double elevDouble = Double.parseDouble(elevation);
		
		gpspoint = new GPSPoint(timeInt, latDouble, lonDouble, elevDouble);
		
		boolean inserted = insertGPS(gpspoint);
		return inserted;
		
		// TODO - SLUTT
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		
		for(GPSPoint gpspoint : gpspoints){
		System.out.println(gpspoint.toString());
		}
		// TODO - SLUTT
		
		 System.out.println("====== Konvertert GPS Data - SLUTT ======");
	
	}
	
}
