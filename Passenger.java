
public class Passenger {
	private String bookingId;
    private String flightNumber;
    private String passengerName;
    public int numOfTickets;

    public Passenger(String bookingId, String flightNumber, String passengerName,int numOfTickets) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.numOfTickets=numOfTickets;
    }

    public String getBookingId() {
        return bookingId;
    }
    
    public int getNumOfTickets()
    {
    	return numOfTickets;
    }
  

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

}
