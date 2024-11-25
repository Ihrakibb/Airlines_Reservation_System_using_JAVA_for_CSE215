public class Flight {
	 
	    private String flightNumber;
	    private String source;
	    private String destination;
	    private int capacity;
	    private double baseFare;
	    private String airline;

	    public Flight(String flightNumber, String source, String destination, int capacity, double baseFare,String airline) {
	        this.flightNumber = flightNumber;
	        this.source = source;
	        this.destination = destination;
	        this.capacity = capacity;
	        this.baseFare = baseFare;
	        this.airline=airline;
	    }

	    public String getFlightNumber() {
	        return flightNumber;
	    }

	    public String getSource() {
	        return source;
	    }

	    public String getDestination() {
	        return destination;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public double getBaseFare() {
	        return baseFare;
	    }
	    
	    public String getAirline() {
	        return airline;
	    }


	    @Override
	    public String toString() {
	        return "Flight Number: " + flightNumber + "\n" +
	                "Source: " + source + "\n" +
	                "Destination: " + destination + "\n" +
	                "Capacity: " + capacity + "\n" +
	                "Base Fare: $" + baseFare + "\n"+
	                "Airline:"+airline+"\n";
	    }
	}