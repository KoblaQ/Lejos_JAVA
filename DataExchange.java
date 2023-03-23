

public class DataExchange {
	private boolean obstacleDetected = false;
	
	private int CMD = 1;
	
	public DataExchange() {
		
	}
	
	/*Get&Set*/
	
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
}
