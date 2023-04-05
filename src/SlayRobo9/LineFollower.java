package SlayRobo9;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
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

				if (colorDetected < lineColor) {
					// Color sensor detects black
					leftWheel.setSpeed(250);//360
					rightWheel.setSpeed(120);//180

					leftWheel.forward();
					rightWheel.forward();
				} else if(colorDetected > lineColor){
					// Color sensor detects white
					
					leftWheel.setSpeed(120);//180
					rightWheel.setSpeed(250);//360
//
//
					leftWheel.forward();
					rightWheel.forward();

				}
			} else {
				// OBSTACLE DETECTED 
				DEObj.setCycle(1);

				
				if(count <= 1) {
					System.out.println("Cycle: " + count);
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
					
					System.out.println("Cycle: " + count);
					
					leftWheel.stop();
					rightWheel.stop();
					
					leftWheel.setSpeed(90);
					rightWheel.setSpeed(90);
					
					//Celebrate Here
					Sound.playSample(new File ("imperial_march2.wav"), Sound.VOL_MAX);
					

					

					leftWheel.forward();
					rightWheel.backward();
					

					Sound.playSample(new File ("imperial_march2.wav"), Sound.VOL_MAX);
					
//					Delay.msDelay(1000);
					
//					leftWheel.backward();
//					rightWheel.forward();
					
//					Delay.msDelay(1000);
					
//					leftWheel.rotate(180);
//					rightWheel.rotate(45);
//					
//					leftWheel.rotate(45);
//					rightWheel.rotate(180);
					System.out.println("DONE DONE DONE");
					
					Delay.msDelay(10000);
					
					
				}
				

			}
			if (Button.getButtons() != 0) {
				break;
			}
		}
	}

}