package SlayRobo9;

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class ObstacleDetector extends Thread {

	private DataExchange DEObj;
	//private Obstacle
	//Initialize the us class as EV3UltrasonicSensor with port s1
	private static EV3UltrasonicSensor obstacleChecker = new EV3UltrasonicSensor(SensorPort.S1);
	
	// SampleProvider class that gets the values from the sensor(In this case distance from the ultrasonic sensor)
	public static SampleProvider distance = obstacleChecker.getDistanceMode();
	
	//Distance value that it keeps reading to detect the object.
	int distanceValue;
	private final int securityDistance = 25; // Minimum distance to the obstacle
	
	//In case the obstacle detector needs to exchange data.
	public ObstacleDetector(DataExchange DE) {
		DEObj = DE;
		//obstacleChecker = new EV3UltrasonicSensor(SensorPort.S1);
	}
	
	
	public void run() {
		
		while(true) {
			float[] sample = new float[distance.sampleSize()];
			distance.fetchSample(sample, 0);
			float distanceValue = (int)(sample[0]*100);
			
			if(distanceValue > securityDistance){
				//Relay message to dataExchange to execute command 1 (meaning no obstacle detected)
				DEObj.setCMD(1);
			}else {
				DEObj.setCMD(0);
				Sound.twoBeeps();
//				Sound.twoBeeps();
				//Throw to the ObstacleAvoider thread
				
			}
			
		}
		
	}

}
