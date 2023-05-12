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

	public void run() {
		// Infinite Task
		while (true) {
			// Get the line value from the ColorSensor Thread
			int colorDetected = DEObj.getLineChecker();
			// Get the count of the cycle
			int count = DEObj.getCycle();

			// Get the linecolor from the database
			int lineColor = DEObj.getLineColor();

			if (DEObj.getCMD() == 1) {
				// LineFollower code if no obstacle is detected

				// The goal is to proportionally turn the robot, based on how far away from the
				// exact line it is

				int baseSpeed = DEObj.getBaseSpeed(); // Basespeed is now set from the database

				// Error is a value base on how far off the line the robot is.
				// If it's positive, the robot is too far on the black
				// If it's negative, it's too far on the white
				int error = (lineColor - colorDetected) * 8; // 5
				// Modify wheel spade based on error
				leftWheel.setSpeed(baseSpeed + error);
				rightWheel.setSpeed(baseSpeed - error);

				leftWheel.forward();
				rightWheel.forward();

			} else {
				// OBSTACLE DETECTED
				DEObj.setCycle(1); // Keep track of the cycles by adding one to the cycle

				// If this is the first cycle.
				if (count <= DEObj.getNewCycle()) {
					DEObj.setStatus(1); // Set the status to 1 to help the robot to know when to send info to the
										// database.

					// Take a sharp right turn
					leftWheel.setSpeed(320);
					rightWheel.setSpeed(180);

					rightWheel.forward();
					leftWheel.forward();

					Delay.msDelay(1000);
					Sound.buzz();

					DEObj.setStatus(0); // Reset the status to 0 to prevent constant sending to the database.

					// Take a sharp left turn
					leftWheel.setSpeed(120);
					rightWheel.setSpeed(250);

					leftWheel.forward();
					rightWheel.forward();

					Delay.msDelay(3000);
					Sound.buzz();

					// Take a sharp right turn to straighten the robot
					leftWheel.setSpeed(300);
					rightWheel.setSpeed(90);

					rightWheel.forward();
					leftWheel.forward();

					Delay.msDelay(750);
					Sound.buzz();

				} else if (count > DEObj.getNewCycle()) {
					// CELEBRATION after the second cycle.

					// Spin around in place.
					leftWheel.stop();
					rightWheel.stop();

					leftWheel.setSpeed(90);
					rightWheel.setSpeed(90);

					// Start spinning here

					leftWheel.forward();
					rightWheel.backward();

					// Play music
					Sound.playSample(new File("cbat3.wav"), Sound.VOL_MAX);
					Sound.playSample(new File("cbat4.wav"), Sound.VOL_MAX);

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
