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


public class slayrobotohttp {

	public static void main(String[] args) {
		
        System.out.println("Read some text from URL\n");
        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();

        URL url = null;
        URL urlCelebration;
  		HttpURLConnection conn = null;
  		InputStreamReader isr = null;
  		BufferedReader br=null;

  		String s=null;
		try {
//			url = new URL("https://ev3test-380115.appspot.com/rest/ev3service/sayhello");
//			url = new URL("http://192.168.0.102:8080/rest/ev3service/sayhello");
//			url = new URL("http://192.168.1.64:8080/rest/laptopservive/servicename");
//			url = new URL("http://192.168.0.115:8080/rest/tadaa/tuduu");
			url = new URL("http://192.168.0.100:8080/rest/celebrationservices/tuduu");
//			url = new URL("http://192.168.0.100:8080/rest/celebrationservices/singlecelebration/4");
//			url = new URL("http://192.168.0.101");
			conn = (HttpURLConnection)url.openConnection();
  			System.out.println(conn.toString()); //Tulostaa vain URLin
//  			System.out.println(urlCelebration);
//			if (conn==null) {
//	  			System.out.println("No connection!!!");
//			}
			InputStream is=null;
			try {
				is=conn.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			isr = new InputStreamReader(is);
      		br=new BufferedReader(isr);
			while ((s=br.readLine())!=null){
				System.out.println(s.toString());
			}
			
			
      		
      		/*
      		 * USE THIS TO SPLIT EACH VALUE INTO PARTS
      		 * NOTICE THE ABSENCE OF A WHILE LOOP BECAUSE WE MIGHT JUST WANT TO GET THE DATA ONCE
      		 * wHILE LOOP IS GOOD FOR WHEN YOU WANT TO CONSTANTLY BE CHECKING THE DATABASE. 
			s=br.readLine();
			String [] values =s.split(" ");
			*/
		}
  		catch(Exception e) {
  			e.printStackTrace();
            System.out.println("Some problem!");
  		}
        System.out.println("Press any key to FINISH");
        Button.waitForAnyPress();
	}

}