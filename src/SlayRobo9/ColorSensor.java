package SlayRobo9;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ColorSensor extends Thread{
	
	//Data Exchange 
	private  DataExchange DEObj ;
	
	//Connect Light Sensor to Port S3
	private EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
	
	public ColorSensor(DataExchange DE) {
		DEObj = DE;
		
		colorSensor.setFloodlight(true);
	}
	
	//VALUES TO BE USED IN THIS THREAD
	SensorMode colorValue = colorSensor.getRedMode();
	int colorDetected;
//	private final int lineColor = 15; // Measured blackness of the black line as against the white background of zero.
	
	public void run() {
		
		while(true) {
			//LineChecker sample provider
			float[] sample = new float[colorValue.sampleSize()];
			colorValue.fetchSample(sample, 0);
			colorDetected = (int)	(sample[0] * 100);
			
			DEObj.setLineChecker(colorDetected);
		}
	}

}
