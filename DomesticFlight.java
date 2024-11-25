class DomesticFlight extends Flight {
    private final boolean photoID=true;
    public DomesticFlight(String flightNumber, String source, String destination, int capacity, double baseFare, String airline) {
        super(flightNumber, source, destination, capacity, baseFare,airline);
        
    }
    @Override
    public String toString() {
        return super.toString() +
                "PhotoID Required:"+photoID+"\n";
    }
}

