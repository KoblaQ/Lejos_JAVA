package SlayRobo9;

import lejos.hardware.Button;

public class SlayRoboto {
	private static DataExchange DE = new DataExchange();
//	START THE HTTPTEST OBJECT HERE
	private static slayrobotohttp SRHttp = new slayrobotohttp(DE);

	
	private static LineFollower LFObj = new LineFollower(DE);
	private static ObstacleDetector ODObj = new ObstacleDetector(DE);
	private static ColorSensor CSObj = new ColorSensor(DE);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Button.waitForAnyPress();
		ODObj.start();
		LFObj.start();
		CSObj.start();
		SRHttp.start(); //Slayrobotohttp 

		while (!(Button.getButtons() != 0)) {
			// Break if button is pressed
			break;
//				if(Button.ESCAPE.isDown()) {
//					break;
//				}
		}

	}

}
