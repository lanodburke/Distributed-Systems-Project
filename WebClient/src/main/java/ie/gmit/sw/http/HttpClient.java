package ie.gmit.sw.http;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import ie.gmit.sw.models.Booking;

@Service
public class HttpClient {
	
	private String URL = "http://localhost:8080/WebService/webapi/";
	
	public HttpClient() {
		
	}
	
	public void getBookings() throws IOException {
		URL url = null;
		url = new URL(URL + "booking/bookings/");

		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		System.out.println(connection.getResponseCode());
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
