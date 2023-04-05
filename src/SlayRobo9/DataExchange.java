package SlayRobo9;

public class DataExchange {

	// ObstacleDetector
	private boolean obstacleDetected = false;
	private int count = 1;
	//ColorSensor
	private int lineChecker;
	
	

	// Robot has the following commands: Follow Line, Stop
	private int CMD = 1;
	public DataExchange() {
		
	}
	
	// Getters and Setters for ObstacleDetector
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

	public int getCycle() {
		return count;
	}

	public void setCycle(int cycle) {
		this.count = this.count + cycle;
	}
	
	//Getters and Setters for LineChecker
	public int getLineChecker() {
		return lineChecker;
	}

	public void setLineChecker(int lineChecker) {
		this.lineChecker = lineChecker;
	}



}
