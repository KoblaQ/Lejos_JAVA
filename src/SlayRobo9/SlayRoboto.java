package SlayRobo9;

import lejos.hardware.Button;

public class SlayRoboto {
	
		private static DataExchange DE = new DataExchange();
		private static LineFollower LFObj = new LineFollower(DE);
		private static ObstacleDetector ODObj = new ObstacleDetector(DE);

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Button.waitForAnyPress();
			ODObj.start();
			LFObj.start();
			
			
			while(!(Button.getButtons()!=0)) {
				//Break if button is pressed
				break;
//				if(Button.ESCAPE.isDown()) {
//					break;
//				}
			}

	}

}
