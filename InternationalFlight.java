class InternationalFlight extends Flight {
    private boolean immigrationRequired;
  
    public InternationalFlight(String flightNumber, String source, String destination, int capacity, double baseFare, boolean immigrationRequired,String airline) {
        super(flightNumber, source, destination, capacity, baseFare,airline);
     
        this.immigrationRequired = immigrationRequired;
    }
    

    public boolean isImmigrationRequired() {
        return immigrationRequired;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Immigration Required: " + immigrationRequired + "\n";
    }
}
   
