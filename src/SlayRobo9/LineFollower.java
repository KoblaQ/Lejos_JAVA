package SlayRobo9;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineFollower extends Thread{
	DataExchange DEObj;
	
	//Connect Light sensor to the port 3
	private static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
	
	//Connect UltraSonic sensor to the port 1
//	private EV3UltrasonicSensor obstacleChecker = new EV3UltrasonicSensor(SensorPort.S1);
	
	// Wheels connected to the ports 
	private RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.B);
	private  RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
	
	
	public LineFollower(DataExchange DE) {
		DEObj = DE;
		
		colorSensor.setFloodlight(true);
	}
	
	//VALUES TO BE USED IN THIS THREAD
	SensorMode colorValue = colorSensor.getRedMode();
	int colorDetected;
	private final int lineColor = 15; //Measured blackness of the black line as against the white background of Zero.

	
	public void run(){
		//Infinite Task
		while(true) {
			if(DEObj.getCMD() == 1) {
				
				//Line Checker SampleProvider
	            float [] sample = new float[colorValue.sampleSize()];
	            colorValue.fetchSample(sample, 0);
	            colorDetected = (int)(sample[0]*100);
				
	            //Set speed first to make it go faster. 
//	            leftWheel.setSpeed(360);
//	            rightWheel.setSpeed(360);
	            
				if(colorDetected < lineColor) {
					leftWheel.setSpeed(360);
					rightWheel.setSpeed(180);
					
//					leftWheel.forward();
//					rightWheel.flt();
//					
					leftWheel.forward();
					rightWheel.forward();
				}else {
					leftWheel.setSpeed(180);
					rightWheel.setSpeed(360);
					
//					leftWheel.flt();
//					rightWheel.forward();
					
					leftWheel.forward();
					rightWheel.forward();
					
				}
			}else {
				//Stop
				Sound.twoBeeps();
//				Sound.twoBeeps();
				
//				rightWheel.stop();
//				leftWheel.stop();
				
				//Turns right and moves forward a bit
		    	leftWheel.rotate(45);
		    	
		    	leftWheel.setSpeed(360);
		    	rightWheel.setSpeed(360);
		    	
		    	rightWheel.forward();
		    	leftWheel.forward();
		    	
		    	Delay.msDelay(2000);
		    	
		    	rightWheel.stop();
		    	leftWheel.stop();
		    	
		    	//Turns Right and moves a bit
		    	
		    	leftWheel.rotate(-90);
		    	
		    	leftWheel.setSpeed(180);
		    	rightWheel.setSpeed(240);
		    	
		    	rightWheel.forward();
		    	leftWheel.forward();
			}
			if(Button.getButtons()!=0) {
	            break;
	        }
		}
	}

}
