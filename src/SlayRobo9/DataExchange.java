package SlayRobo9;

public class DataExchange {

	// ObstacleDetector
	private boolean obstacleDetected = false;
	//ColorSensor
	private int lineChecker;
	
	

	// Robot has the following commands: Follow Line, Stop
	private int CMD = 1;
	public DataExchange() {
		
	}
	
	// Getters and Setters
	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}
	
	public boolean getObstacleDetected() {
		return obstacleDetected;
	}
	
	public void setCMD(int command) {
		CMD = command;
	}
	
	public int getCMD() {
		return CMD;
	}
	
	//Getters and Setters for LineChecker
	public int getLineChecker() {
		return lineChecker;
	}

	public void setLineChecker(int lineChecker) {
		this.lineChecker = lineChecker;
	}

}
