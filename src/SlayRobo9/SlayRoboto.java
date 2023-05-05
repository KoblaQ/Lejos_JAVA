package SlayRobo9;

import lejos.hardware.Button;

public class SlayRoboto {
	
		private static DataExchange DE = new DataExchange();
		private static LineFollower LFObj = new LineFollower(DE);
		private static ObstacleDetector ODObj = new ObstacleDetector(DE);
		private static ColorSensor CSObj = new ColorSensor(DE);
//		START THE HTTPTEST OBJECT HERE
		private static slayrobotohttp SRHttp = new slayrobotohttp(DE);

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Button.waitForAnyPress();
			ODObj.start();
			LFObj.start();
			CSObj.start();
			SRHttp.start();
//			START IT HERE TOO JUST LIKE ABOVE
//			REMEMBER TO CHANGE ALL THE HARDCODED VALUES IN THE OTHER OBJECTS TO VARIABLES DERRIVED FROM THE DATAEXCHAGE eg. DE.basespeed 
//			BONUS POINTS: ADD A BUTTON TO START AND STOP THE ROBOT (must be connected to the database) 
			
			while(!(Button.getButtons()!=0)) {
				//Break if button is pressed
				break;
//				if(Button.ESCAPE.isDown()) {
//					break;
//				}
			}

	}

}
