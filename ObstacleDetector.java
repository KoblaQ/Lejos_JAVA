import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
//import lejos.utility.Delay;

public class ObstacleDetector extends Thread {
	private DataExchange DEObj;
	
	private EV3UltrasonicSensor us;
	private final int securityDistance = 15;
	
	public ObstacleDetector(DataExchange DE) {
		DEObj = DE;
		us = new EV3UltrasonicSensor(SensorPort.S1);
	}
	
	public void run() {
        int distanceValue = 0;
        final SampleProvider sp = us.getDistanceMode();  
		while(true) {
			float[] sample = new float[sp.sampleSize()];
	        sp.fetchSample(sample, 0);
	        distanceValue = (int)(sample[0]*100);
	           
	        if(distanceValue > securityDistance) {
	           	DEObj.setCMD(1);	
	        }else {
	           	DEObj.setCMD(0);
	           	LCD.drawString("Object found!", 0, 1);
		        LCD.refresh();
		        Sound.twoBeeps();        
	        }
	            
	        /*display distance
	        System.out.println("Distance: " + distanceValue);
	        Delay.msDelay(500);
	        */
	        if(Button.getButtons()!=0) {
	        	break;
	        }
		}
	}
	
}
