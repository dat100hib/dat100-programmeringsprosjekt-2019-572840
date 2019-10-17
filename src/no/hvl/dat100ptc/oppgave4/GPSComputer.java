package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		double[] disTab = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length - 1; i++) {

			disTab[i] = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			System.out.println(disTab[i]);
		}

		for (int i = 0; i < disTab.length - 1; i++) {

			distance += disTab[i];
		}

		return distance;

		// TODO - SLUTT

	}

	// beregn totale hÃ¸ydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		double[] elevTab = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length - 1; i++) {

			if ((gpspoints[i + 1].getElevation()) / (gpspoints[i].getElevation()) > 1) {

				elevTab[i] = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();

				// Må være stigning...
			}

		}

		for (int i = 0; i < elevTab.length - 1; i++) {

			if (elevTab[i] > 0) {
				elevation += elevTab[i];
			}
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)

	public int totalTime() {

		int Time = 0;
		int[] timeTab = new int[gpspoints.length];

		for (int i = 0; i < gpspoints.length - 1; i++) {
			timeTab[i] = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
		}

		for (int i = 0; i < timeTab.length - 1; i++) {

			Time += timeTab[i];

		}
		return Time;
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START

		double[] gjSpeed = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {

			gjSpeed[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);

		}

		return gjSpeed;

		// TODO - SLUTT

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START
		maxspeed = GPSUtils.findMax(speeds());

		return maxspeed;

		// TODO - SLUTT

	}

	public double averageSpeed() {

		double average = 0;

		// TODO - START

		double averageMax = 0.0;

		double[] maxs = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {

			maxs[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
			averageMax = averageMax + maxs[i];
		}

		average = (averageMax / (gpspoints.length - 1.5));

		return average;

		// TODO - SLUTT

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START

		if (speedmph < 10) {
			met = 4.0;
		} else {
			if (speedmph >= 10 && speedmph <= 12) {
				met = 6.0;
			} else {
				if (speedmph >= 12 && speedmph <= 14) {
					met = 8.0;
				} else {
					if (speedmph >= 14 && speedmph <= 16) {
						met = 10.0;
					} else {
						if (speedmph >= 16 && speedmph <= 20) {
							met = 12.0;
						} else {
							if (speedmph > 20) {
								met = 16.0;
							}
						}
					}
				}
			}
		}

		kcal = met / weight * ((secs / 60) / 60);

		return kcal;
		// TODO - SLUTT

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		double met = 0;
		double speedmph = averageSpeed() * MS;
		double secs = totalTime();

		if (speedmph < 10) {
			met = 4.0;
		} else {
			if (speedmph >= 10 && speedmph <= 12) {
				met = 6.0;
			} else {
				if (speedmph >= 12 && speedmph <= 14) {
					met = 8.0;
				} else {
					if (speedmph >= 14 && speedmph <= 16) {
						met = 10.0;
					} else {
						if (speedmph >= 16 && speedmph <= 20) {
							met = 12.0;
						} else {
							if (speedmph > 20) {
								met = 16.0;
							}
						}
					}
				}
			}
		}

		totalkcal = met * (weight * (secs / 60) / 60);
		return totalkcal;

		// TODO - SLUTT

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

}
