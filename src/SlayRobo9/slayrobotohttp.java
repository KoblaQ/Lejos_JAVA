package SlayRobo9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation.Builder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;


public class slayrobotohttp extends Thread {

	private static DataExchange DEObj;

	public slayrobotohttp(DataExchange DE) {
		DEObj = DE;
	}

	public static String ipAddress = "192.168.0.101"; // Change the ipaddress to the one running with eclipse

	public void run() {

		// Import values from the Slayroboto database
		URL urlSlayRoboto = null;
		HttpURLConnection connSlayRoboto = null;
		InputStreamReader isrSlayRoboto = null;
		BufferedReader brSlayRoboto = null;

		// Send Obstacle Distance to the database
		URL urlObstacleDistance = null;
		HttpURLConnection connObstacleDistance = null;
		InputStreamReader isrObstacleDistance = null;
		BufferedReader brObstacleDistance = null;

		// Send Color Sensor Values to the database
		URL urlColorSensor = null;
		HttpURLConnection connColorSensor = null;
		InputStreamReader isrColorSensor = null;
		BufferedReader brColorSensor = null;

		try {
			while (true) {

				// Slayroboto values to String
				urlSlayRoboto = new URL("http://" + ipAddress + ":8080/rest/slayrobotoservices/slayroboto");
				connSlayRoboto = (HttpURLConnection) urlSlayRoboto.openConnection();
				InputStream isSlayRoboto = null;
				try {
					isSlayRoboto = connSlayRoboto.getInputStream();
				} catch (Exception e) {
					System.out.println("Exception conn1.getInputSteam()");
					e.printStackTrace();
					System.out.println("Cannot get InputStream from URL 1!");
				}
				isrSlayRoboto = new InputStreamReader(isSlayRoboto);
				brSlayRoboto = new BufferedReader(isrSlayRoboto);
				String sSlayRoboto = null;
				while ((sSlayRoboto = brSlayRoboto.readLine()) != null) {
					String[] values = sSlayRoboto.split(" ");
					// Set BaseSpeed
					int baseSpeed = Integer.parseInt(values[0]);
					DEObj.setBaseSpeed(baseSpeed);

					// Set Cycle
					int cycle = Integer.parseInt(values[1]);
					DEObj.setNewCycle(cycle);

					// Set Safety Distance
					int safetyDistance = Integer.parseInt(values[2]);
					DEObj.setSafetyDistance(safetyDistance);

					// Set lineColor
					int lineColor = Integer.parseInt(values[3]);
					DEObj.setLineColor(lineColor);
				}
				
				// Send Cycle to database if robot detects an obstacle
				if (DEObj.getStatus() == 1) {
					int obstacleDistanceValue = DEObj.getObstacleDistance();
					urlObstacleDistance = new URL("http://" + ipAddress
							+ ":8080/rest/obstacledetectedservices/addobstacle/" + obstacleDistanceValue);
					connObstacleDistance = (HttpURLConnection) urlObstacleDistance.openConnection();
					InputStream isObstacleDistance = null;
					try {
						isObstacleDistance = connObstacleDistance.getInputStream();
					} catch (Exception e) {
						System.out.println("Exception conn1.getInputSteam()");
						e.printStackTrace();
						System.out.println("Cannot get InputStream from URL Obstacle Distance!");
					}
					isrObstacleDistance = new InputStreamReader(isObstacleDistance);
					brObstacleDistance = new BufferedReader(isrObstacleDistance);
					String sObstacleDistance = null;

					System.out.println("Obstacle Distance: " + sObstacleDistance);
				}

				
				// Sending values to the Light Table in the database
				urlColorSensor = new URL(
						"http://" + ipAddress + ":8080/rest/lightservices/colorsensor/1/" + DEObj.getLineChecker());
				connColorSensor = (HttpURLConnection) urlColorSensor.openConnection();
				InputStream isColorSensor = null;
				try {
					isColorSensor = connColorSensor.getInputStream();
				} catch (Exception e) {
					System.out.println("Exception connColorSensor.getInputSteam()");
					e.printStackTrace();
					System.out.println("Cannot get InputStream from URL ColorSensor!");
				}
				isrColorSensor = new InputStreamReader(isColorSensor);
				brColorSensor = new BufferedReader(isrColorSensor);
//				String sColorSensor = null;
//				while ((sColorSensor = brColorSensor.readLine()) != null) {
//				}
				
				Thread.sleep(1000); // wait for 1 second before trying again
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem!");
		} finally {
			try {
				// SlayRoboto
				if (brSlayRoboto != null)
					brSlayRoboto.close();
				if (isrSlayRoboto != null)
					isrSlayRoboto.close();
				if (connSlayRoboto != null)
					connSlayRoboto.disconnect();
				// Obstacle Distance
				if (brObstacleDistance != null)
					brObstacleDistance.close();
				if (isrObstacleDistance != null)
					isrObstacleDistance.close();
				if (connObstacleDistance != null)
					connObstacleDistance.disconnect();

				// LineColor from ColorSensor
				if (brColorSensor != null)
					brColorSensor.close();
				if (isrColorSensor != null)
					isrColorSensor.close();
				if (connColorSensor != null)
					connColorSensor.disconnect();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}