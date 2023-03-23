package app;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorChecker {

    private static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
   // private static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
    private static EV3UltrasonicSensor distanceChecker = new EV3UltrasonicSensor(SensorPort.S1);

    public static void main(String[] args){
    	
    	RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);

        SampleProvider colorValue = colorSensor.getRedMode();
        //colorDetected needs to be a float because it is using values between 0 - 1. 
        float colorDetected;
        
        //UltraSonicSensor 
        
        Button.waitForAnyPress();
        
        while(true) {
            float [] sample = new float[colorValue.sampleSize()];
            colorValue.fetchSample(sample, 0);
            colorDetected = (sample[0]);

            //float [] sample = new float[colorValue.sampleSize()];
            //colorValue.fetchSample(sample, 0);
            //colorDetected = (sample[0]);
            
            
            if(colorDetected <= 0.15) {
				leftWheel.setSpeed(360);
				rightWheel.setSpeed(180);
				
				leftWheel.forward();
				rightWheel.forward();
			}else if(colorDetected > 0.15){
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
