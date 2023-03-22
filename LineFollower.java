package app;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.Color;

public class LineFollower {

	public static void main(String[] args) {
		RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3ColorSensor lineChecker = new EV3ColorSensor(SensorPort.S3);
		
		Button.waitForAnyPress();
		int lineColor;
		//Set the speed for both wheels (Uses per second) 
		rightWheel.setSpeed(720);
		leftWheel.setSpeed(720);
		
		
		
		while(true) {
//			float[] sample = new float[lineChecker.sampleSize()];
//			lineChecker.fetchSample(sample, 0);
	
				lineColor = lineChecker.getColorID();
				SensorMode Color = lineChecker.getRedMode();
//				if(lineColor == 7) {
//					leftWheel.rotate(90);
//					rightWheel.rotate(45);
//				}else if(lineColor == 6){
//					rightWheel.rotate(90);
//					leftWheel.rotate(45);
//				}
				
				if(lineColor == 7) {
					leftWheel.setSpeed(360);
					rightWheel.setSpeed(180);
					
					leftWheel.forward();
					rightWheel.forward();
				}else if(lineColor == 6){
					rightWheel.setSpeed(360);
					leftWheel.setSpeed(180);
					
					rightWheel.forward();
					leftWheel.forward();
				}
				
				if(Button.getButtons()!=0) {
	                break;
	            }
						
		}
		
		
		

	}

}
