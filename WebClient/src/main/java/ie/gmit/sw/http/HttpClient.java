package ie.gmit.sw.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import ie.gmit.sw.models.Booking;

@Service
public class HttpClient {
	
	public HttpClient() {
		
	}
	
	public List<Booking> getAllBookings() {
        Client client = Client.create();
        WebResource webresource = client.resource("http://localhost:8080/WebService/webapi/booking/bookings");
                             
        return webresource.get(new GenericType<List<Booking>>(){});
    }
	
	public Booking getBooking(int bookingId) throws IOException, JAXBException {
		String uri =
			    "http://localhost:8080/WebService/webapi/booking/" + bookingId;
			URL url = new URL(uri);
			HttpURLConnection connection =
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			JAXBContext jc = JAXBContext.newInstance(Booking.class);
			InputStream xml = connection.getInputStream();
			Booking booking =
			    (Booking) jc.createUnmarshaller().unmarshal(xml);
			
			return booking;
	}
	
	public void createBooking(Booking booking) throws IOException, JAXBException {
		StringBuffer content = new StringBuffer();
		String uri =
			    "http://localhost:8080/WebService/webapi/booking/createBooking/" +  booking.getBookingId();
			URL url = new URL(uri);
			HttpURLConnection connection =
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/xml");
			
			connection.setDoOutput(true);
			connection.setConnectTimeout(5000);
			
			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(getBookingAsXML(booking));
			wr.flush();
			wr.close();
			
			 
			// read response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String str;
			while ((str = in.readLine()) != null) {
				content.append(str);
			}
			in.close();
			
			System.out.println(content.toString());
			
	}
	
	private String getBookingAsXML(Booking bo) {
		StringWriter sw = new StringWriter();
		Marshaller m;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(bo, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
		return sw.toString();
	}
	
	private Booking getBookingFromXml(String input) {
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Booking boFromXml = null;
		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.models");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Booking> boElement1 = um1.unmarshal(source1, Booking.class);
			boFromXml = (Booking) boElement1.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return boFromXml;
	}
}
