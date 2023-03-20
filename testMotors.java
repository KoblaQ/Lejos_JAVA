package app;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.Button;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.TachoMotorPort;


public class move {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Brick brick = BrickFinder.getDefault();
        Port motorPortD = brick.getPort("D");
        Port motorPortC = brick.getPort("C");
        String port_name = motorPortD.getName();
        //NXTRegulatedMotor motorD = new NXTRegulatedMotor((TachoMotorPort) motorPortD);
        
        System.out.println(port_name);

        EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(motorPortD);
        EV3LargeRegulatedMotor motorC = new EV3LargeRegulatedMotor(motorPortC);
        

        motorD.setSpeed(360); // Speed in degrees per second
        motorC.setSpeed(360); // Speed in degrees per second
        
        motorD.forward();
        motorC.forward();
       
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        
        //motorD.rotate(180, true); // Rotate the motor 180 degrees, 'true' means non-blocking (returns immediately)
        //motorC.rotate(180, true);
       
        
        //motorD.waitComplete(); // Wait for the motor to complete its rotation

        
        motorD.stop();
        motorC.stop();
        
        motorD.close();// Release resources when you're done
        motorC.close();

        System.out.println("Done");
        motorD.close(); // Release resources when you're done

        
		//EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor("D");
		//EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor("C");

		//leftMotor.forward();
		//rightMotor.forward();

		
		//Button.waitForAnyPress();
	}

}
