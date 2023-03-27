package SlayRobo9;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ObstacleAvoider  {
	private DataExchange DEObj;
	//In case the obstacle detector needs to exchange data.
	public ObstacleAvoider(DataExchange DE) {
		DEObj = DE;
		//obstacleChecker = new EV3UltrasonicSensor(SensorPort.S1);
	}
	
	//Connect Light sensor to the port 3
//	private static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
//	private static EV3UltrasonicSensor obstacleChecker = new EV3UltrasonicSensor(SensorPort.S1);
	
	// Wheels connected to the ports 
	private RegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.B);
	private  RegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.A);
	
	public void run() {
		//rightWheel.rotate();
		while(true) {
			
		    if(DEObj.getCMD() == 0) {
		    	
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
			
		
		}
	}
		
	
	
}
