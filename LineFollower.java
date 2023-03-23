import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.EV3UltrasonicSensor;
//import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class LineFollower extends Thread {
	DataExchange DEObj;
	private EV3ColorSensor ss;
	//private EV3UltrasonicSensor us;
	private final int colorPattern = 45;
	
	private RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.B);
	private RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
	public LineFollower(DataExchange DE) {
		DEObj = DE;
		ss = new EV3ColorSensor(SensorPort.S3);
		//us = new EV3UltrasonicSensor(SensorPort.S1);
		ss.setFloodlight(true);
	}

	public void run() {
		while(true) {
			int lightValue = 0;
			final SampleProvider sp = ss.getRedMode();
			if(DEObj.getCMD() == 1) {
				float[] sample = new float[sp.sampleSize()];
	            sp.fetchSample(sample, 0);
	            lightValue = (int)(sample[0]*100);	
	            //System.out.println(lightValue);
	              
	            if(lightValue > colorPattern) {
	            	//leftWheel.rotate(60);
	            	rightWheel.setSpeed(180);
			    	leftWheel.setSpeed(360);
			    	rightWheel.forward();
			        leftWheel.forward();
	            }
	            else {
	            	//rightWheel.rotate(60);
	            	rightWheel.setSpeed(360);
			    	leftWheel.setSpeed(180);
			    	rightWheel.forward();
			        leftWheel.forward();
	            }
	            LCD.drawInt(lightValue, 1, 1);
	            LCD.refresh();
			}
			else {
				rightWheel.rotate(0);
		    	leftWheel.rotate(0);
			}
            
		}
	}
}
