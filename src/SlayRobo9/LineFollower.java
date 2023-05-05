package SlayRobo9;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineFollower extends Thread {
	DataExchange DEObj;

	// Wheels connected to the ports
	private RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.B);
	private RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);

	public LineFollower(DataExchange DE) {
		DEObj = DE;
	}
	private final int lineColor = 15; // Measured blackness of the black line as against the white background of Zero.

	public void run() {
		// Infinite Task
		while (true) {
			//Get the line value from the ColorSensor Thread
			int colorDetected = DEObj.getLineChecker();
			//Get the count of the cycle 
			int count = DEObj.getCycle();

			if (DEObj.getCMD() == 1) {
				//LineFollower code if no obstacle is detected
				
				//This code needs to be deleted. We are adopting the new one below.

//				if (colorDetected < lineColor) {
//					// Color sensor detects black
//					leftWheel.setSpeed(250);//360
//					rightWheel.setSpeed(120);//180
//
//					leftWheel.forward();
//					rightWheel.forward();
//				} else if(colorDetected > lineColor){
//					// Color sensor detects white
//					
//					leftWheel.setSpeed(120);//180
//					rightWheel.setSpeed(250);//360
//
//					leftWheel.forward();
//					rightWheel.forward();
//
//				}
				

				//The goal is to proportionally turn the robot, based on how far away from the exact line it is
				
//				int baseSpeed = 250; // 250
				int baseSpeed = DEObj.getBaseSpeed();
				
				//Error is a value base on how far off the line the robot is.
				//If it's positive, the robot is too far on the black
				//If it's negative, it's too far on the white
				int error = (lineColor - colorDetected) * 8; //5
				LCD.drawInt(error, 1, 1);
				LCD.drawInt(colorDetected, 5, 1);
				//Modify wheel spade based on error
				leftWheel.setSpeed(baseSpeed + error);
				rightWheel.setSpeed(baseSpeed - error);
				 
				leftWheel.forward();
				rightWheel.forward();
				
			} else {
				// OBSTACLE DETECTED 
				DEObj.setCycle(1);
				
				//If this is the first cycle. 
				if(count <= 1) {
					System.out.println("Cycle: " + count);  // Debugging tool
					//Take a sharp right turn
					leftWheel.setSpeed(320); //200
					rightWheel.setSpeed(180); //120

					rightWheel.forward();
					leftWheel.forward();
	 
					Delay.msDelay(1000);//1000
					Sound.buzz();
					
					
					//Take a sharp left turn
					leftWheel.setSpeed(120);//180
					rightWheel.setSpeed(250);//360

					leftWheel.forward();
					rightWheel.forward();
					
					Delay.msDelay(3000); //3000
					Sound.buzz();
					
					// Take a sharp right turn to straighten the robot
					leftWheel.setSpeed(300);
					rightWheel.setSpeed(90);

					rightWheel.forward();
					leftWheel.forward();

					Delay.msDelay(750);
					Sound.buzz();

				} else if(count > 1){
					// CELEBRATION after the second cycle.
					System.out.println("Cycle: " + count); // Debugging tool
					
					leftWheel.stop();
					rightWheel.stop();
					
					leftWheel.setSpeed(90);
					rightWheel.setSpeed(90);
					
					//Celebrate Here
					
					leftWheel.forward();
					rightWheel.backward();
					
					// This sound SLAPS!!!
					Sound.playSample(new File ("cbat3.wav"), Sound.VOL_MAX);
					Sound.playSample(new File ("cbat4.wav"), Sound.VOL_MAX);
					
					System.out.println("DONE DONE DONE");

					Sound.twoBeeps();
					Delay.msDelay(20000);

				}
				

			}
			if (Button.getButtons() != 0) {
				break;
			}
		}
	}

}
