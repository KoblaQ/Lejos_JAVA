package SlayRobo9;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Enumeration;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation.Builder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;

import lejos.hardware.Button;
import lejos.hardware.Sound;


public class slayrobotohttp extends Thread {
	
	private static DataExchange DEObj;
	
	public slayrobotohttp(DataExchange DE) {
		DEObj = DE;
	}

	public void run() {
	    System.out.println("Read some text from URL\n");
	    System.out.println("Press any key to start");

	//  Button.waitForAnyPress();

	    URL url1 = null;
	    HttpURLConnection conn1 = null;
	    InputStreamReader isr1 = null;
	    BufferedReader br1 = null;

	    URL url2 = null;
	    HttpURLConnection conn2 = null;
	    InputStreamReader isr2 = null;
	    BufferedReader br2 = null;
	    
	    URL url3 = null;
	    HttpURLConnection conn3 = null;
	    InputStreamReader isr3 = null;
	    BufferedReader br3 = null;

	    try {
	        while (true) {
	            url1 = new URL("http://192.168.1.181:8080/rest/slayrobotoservices/basespeed");
	            conn1 = (HttpURLConnection) url1.openConnection();
//	            System.out.println(conn1.toString()); // Tulostaa vain URLin
	            InputStream is1 = null;
	            try {
	                is1 = conn1.getInputStream();
	            } catch (Exception e) {
	                System.out.println("Exception conn1.getInputSteam()");
	                e.printStackTrace();
	                System.out.println("Cannot get InputStream from URL 1!");
	            }
	            isr1 = new InputStreamReader(is1);
	            br1 = new BufferedReader(isr1);
	            String s1 = null;
	            while ((s1 = br1.readLine()) != null) {
//	                System.out.println("Response from URL 1: " + s1);
	                int baseSpeed = Integer.parseInt(s1);
	                DEObj.setBaseSpeed(baseSpeed);
	            }

	            url2 = new URL("http://192.168.1.181:8080/rest/slayrobotoservices/cycle");
	            conn2 = (HttpURLConnection) url2.openConnection();
//	            System.out.println(conn2.toString()); // Tulostaa vain URLin
	            InputStream is2 = null;
	            try {
	                is2 = conn2.getInputStream();
	            } catch (Exception e) {
	                System.out.println("Exception conn2.getInputSteam()");
	                e.printStackTrace();
	                System.out.println("Cannot get InputStream from URL 2!");
	            }
	            isr2 = new InputStreamReader(is2);
	            br2 = new BufferedReader(isr2);
	            String s2 = null;
	            while ((s2 = br2.readLine()) != null) {
	                System.out.println("Response from URL 2: " + s2);
	                int cycle = Integer.parseInt(s2);
	                DEObj.setNewCycle(cycle);
	                
	            }
	            
	            url3 = new URL("http://192.168.1.181:8080/rest/slayrobotoservices/basespeed");
	            conn3 = (HttpURLConnection) url1.openConnection();
//	            System.out.println(conn1.toString()); // Tulostaa vain URLin
	            InputStream is3 = null;
	            try {
	                is3 = conn3.getInputStream();
	            } catch (Exception e) {
	                System.out.println("Exception conn1.getInputSteam()");
	                e.printStackTrace();
	                System.out.println("Cannot get InputStream from URL 1!");
	            }
	            isr3 = new InputStreamReader(is1);
	            br3 = new BufferedReader(isr1);
	            String s3 = null;
	            while ((s3 = br1.readLine()) != null) {
//	                System.out.println("Response from URL 1: " + s1);
	                int safety_distance = Integer.parseInt(s1);
	                DEObj.setSafetyDistance(safety_distance);
	            }


	            Thread.sleep(1000); // wait for 1 second before trying again
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Some problem!");
	    } finally {
	        try {
	            if (br1 != null) br1.close();
	            if (isr1 != null) isr1.close();
	            if (conn1 != null) conn1.disconnect();
	            if (br2 != null) br2.close();
	            if (isr2 != null) isr2.close();
	            if (conn2 != null) conn2.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	//  System.out.println("Press any key to FINISH");
	//  Button.waitForAnyPress();
	}

}