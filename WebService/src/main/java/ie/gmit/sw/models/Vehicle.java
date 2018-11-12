package ie.gmit.sw.models;

public class Vehicle {
	private int id;
	private String registrationNumber;
	private int milage;
	private String condition;
	
	public Vehicle(int id, String registrationNumber, int milage, String condition) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.milage = milage;
		this.condition = condition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getMilage() {
		return milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
