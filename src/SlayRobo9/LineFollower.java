package SlayRobo9;

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

			if (DEObj.getCMD() == 1) {

				// Set speed first to make it go faster.
//	            leftWheel.setSpeed(240);
//	            rightWheel.setSpeed(240);
	            
//	            leftWheel.forward();
//				rightWheel.forward();

				if (colorDetected < lineColor) {
					// Color sensor detects black
					leftWheel.setSpeed(380);//360
					rightWheel.setSpeed(190);//180
					
					leftWheel.forward();
					rightWheel.forward();
				} else {
					// Color sensor detects white
					leftWheel.setSpeed(180);//180
					rightWheel.setSpeed(360);//360


					leftWheel.forward();
					rightWheel.forward();

				}
			} else {
				//Take a sharp right turn
				leftWheel.setSpeed(320); //200
				rightWheel.setSpeed(180); //120

				rightWheel.forward();
				leftWheel.forward();

				Delay.msDelay(1000);
				Sound.buzz();
				
				// Take a sharp left turn
				leftWheel.setSpeed(140);
				rightWheel.setSpeed(360); //240

				rightWheel.forward();
				leftWheel.forward();
				
				Delay.msDelay(1500);
				Sound.buzz();
				
				// Take a sharp right turn to straighten the robot
				leftWheel.setSpeed(300);
				rightWheel.setSpeed(90);
				
				rightWheel.forward();
				leftWheel.forward();
				
				Delay.msDelay(500);//750
				Sound.buzz();
				
			}
			if (Button.getButtons() != 0) {
				break;
			}
		}
	}

}
