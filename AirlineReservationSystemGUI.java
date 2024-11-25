

//group4 project


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AirlineReservationSystemGUI
{
    private static final String FILE_PATH = "bookingsfinal.txt";
    private static List<Flight> flights= new ArrayList<>();
     
    private static JComboBox<String> destinationComboBox;
    private static JComboBox<String> flightTypeComboBox;
    private static JRadioButton economyClassRadio;
    private static JRadioButton businessClassRadio;
    private static JCheckBox roundTripCheckbox;
    private static JCheckBox foodCheckbox;
    private static JTextArea textArea;
    private static JButton bookButton,cancelButton,calculateFareButton,refreshButton,passengerDetailsButton;
    private static List<Passenger> passengers=new ArrayList<>();
    private static JFrame frame;
   
    public static void main(String[] args) {
    	frame=new JFrame("Bangladesh NOVO Airline Reservation System");
    	frame.setSize(800, 600);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    
    	JPanel panel=new JPanel();
    	frame.add(panel, BorderLayout.CENTER);
    	panelComponents(panel);
    	
    	JPanel controlPanel=new JPanel();
    	frame.add(controlPanel, BorderLayout.NORTH);
    	controlPanelComponents(controlPanel);
    	frame.setVisible(true);
    	AirlineReservationSystemGUI obj=new AirlineReservationSystemGUI();
    	obj.myAction();
    	
    }
       private static void panelComponents(JPanel panel) 
       {
    	 
        panel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
      
        JLabel planeLabel = new JLabel(new ImageIcon("C:\\Users\\User\\Pictures\\Screenshots\\istockphoto-155439315-612x612.jpg"));
        panel.add(planeLabel, BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel();
        bookButton = new JButton("Book Flight");
        cancelButton = new JButton("Cancel Flight");
        calculateFareButton = new JButton("Calculate Fare");
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(calculateFareButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

       
        }
        private static void controlPanelComponents(JPanel controlPanel) 
        {
         
        controlPanel.setLayout(new GridLayout(5,2));

        JLabel destinationLabel = new JLabel("Destination:");
        destinationComboBox = new JComboBox<>(new String[]{"Chittagong", "Sylhet", "Cox's Bazar", "Rajshahi"});
        controlPanel.add(destinationLabel);
        controlPanel.add(destinationComboBox);
   

        JLabel flightTypeLabel = new JLabel("Flight Type:");
        flightTypeComboBox = new JComboBox<>(new String[]{"Domestic", "International"});
        controlPanel.add(flightTypeLabel);
        controlPanel.add(flightTypeComboBox);
        
  
        JLabel classLabel = new JLabel("Class:");
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        economyClassRadio = new JRadioButton("Economy");
        businessClassRadio = new JRadioButton("Business");
        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(economyClassRadio);
        classGroup.add(businessClassRadio);
        classPanel.add(economyClassRadio);
        classPanel.add(businessClassRadio);
        controlPanel.add(classLabel);
        controlPanel.add(classPanel);
        
        JLabel optionsLabel = new JLabel("Options:");
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        roundTripCheckbox = new JCheckBox("Round-trip");
        foodCheckbox = new JCheckBox("Food");
        optionsPanel.add(roundTripCheckbox);
        optionsPanel.add(foodCheckbox);
        controlPanel.add(optionsLabel);
        controlPanel.add(optionsPanel);

        refreshButton = new JButton("Refresh Flights");
        controlPanel.add(refreshButton);

        passengerDetailsButton = new JButton("Passenger Details");
        controlPanel.add(passengerDetailsButton);
        }
        private void myAction() 
        {
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookFlight();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                cancelFlight();
            	}
            	catch(FlightBookingException m) { 
            		JOptionPane.showMessageDialog(frame, "Invalid input of booking ID", "Flight Cancellation Error", JOptionPane.ERROR_MESSAGE);}
            }
        });

        calculateFareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateFare();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                populateFlights();
            }
        });
        

        passengerDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPassengerDetails();
            }
        });
        }
        
    
        String selectedDestination="";
        private void populateFlights() {
        flights.clear();
        

        if (flightTypeComboBox.getSelectedItem().equals("Domestic")) {
        	selectedDestination=destinationComboBox.getSelectedItem().toString();
        	destinationComboBox.removeAllItems();
            flights.add(new DomesticFlight("BG101", "Dhaka", "Chittagong", 100, 100.0, "Biman Bangladesh"));
            flights.add(new DomesticFlight("BG102", "Dhaka", "Sylhet", 120, 120.0, "Biman Bangladesh"));
            flights.add(new DomesticFlight("BG103", "Dhaka", "Cox's Bazar", 150, 150.0, "Biman Bangladesh"));
            flights.add(new DomesticFlight("BG104", "Dhaka", "Rajshahi", 80, 80.0, "Biman Bangladesh"));
            flights.add(new DomesticFlight("US101", "Dhaka", "Chittagong", 100, 100.0, "US-Bangla Airlines"));
            flights.add(new DomesticFlight("US102", "Dhaka", "Sylhet", 120, 120.0, "US-Bangla Airlines"));
            flights.add(new DomesticFlight("US103", "Dhaka", "Cox's Bazar", 150, 150.0, "US-Bangla Airlines"));
            flights.add(new DomesticFlight("US104", "Dhaka", "Rajshahi", 80, 80.0, "US-Bangla Airlines"));
           
        } else if (flightTypeComboBox.getSelectedItem().equals("International")){
        	selectedDestination=destinationComboBox.getSelectedItem().toString();
        	destinationComboBox.removeAllItems();
            flights.add(new InternationalFlight("EK202", "Dhaka", "Dubai", 200, 500.0, true,"Sigapore Airlines"));
            flights.add(new InternationalFlight("QR101", "Dhaka", "Doha", 180, 400.0, true,"B. British Airways"));
            flights.add(new InternationalFlight("SV201", "Dhaka", "Riyadh", 220, 450.0, false,"C. Condor Airlines "));
            flights.add(new InternationalFlight("BA201", "Dhaka", "London", 250, 600.0, true,"B. British Airways"));
          
        }

       for (Flight flight : flights) {
           destinationComboBox.addItem(flight.getDestination()); 
        }
        displayAvailableFlights();
    }

    private void displayAvailableFlights(){
        StringBuilder availableFlights = new StringBuilder();
         
        for (Flight flight : flights) {
            if (flight.getDestination().equals(selectedDestination))
            {
                if (flight.getCapacity() > 0)
                {
                    availableFlights.append(flight.toString()).append("\n");
                } 
                else {
                    availableFlights.append(flight.toString()).append("Flight is fully booked.\n\n");
                }
            }
        }

        textArea.setText(availableFlights.toString());
    }
    Flight selectedFlight;
    private void bookFlight() {
        String selectedDestination = destinationComboBox.getSelectedItem().toString();

        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDestination().equals(selectedDestination) && flight.getCapacity() > 0) {
                availableFlights.add(flight);
            }
        }

        if (availableFlights.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No available flights for the selected destination.", "Flight Booking", JOptionPane.ERROR_MESSAGE);
            return;
        }

        selectedFlight = (Flight) JOptionPane.showInputDialog(frame, "Select a flight:", "Flight Booking", JOptionPane.PLAIN_MESSAGE, null, availableFlights.toArray(),null);

        if (selectedFlight != null) {
            String passengerName = JOptionPane.showInputDialog(frame, "Enter passenger name:");
            int numOfTickets=Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of tickets you want to book:"));

            Random random = new Random();
            String bookingId = String.valueOf(random.nextInt(900000) + 100000);

            
            
            
            
            
            passengers.add(new Passenger(bookingId, selectedFlight.getFlightNumber(), passengerName,numOfTickets));
            selectedFlight.setCapacity(selectedFlight.getCapacity() - numOfTickets);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
                writer.write(bookingId + "," + selectedFlight.getFlightNumber() + "," + passengerName);
                writer.newLine();
                writer.close();

                JOptionPane.showMessageDialog(frame, "Flight booked successfully.\nBooking ID: " + bookingId, "Flight Booking", JOptionPane.INFORMATION_MESSAGE);
                displayAvailableFlights();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "An error occurred while booking the flight: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    String bookingId;
    private void cancelFlight() throws FlightBookingException{
        bookingId = JOptionPane.showInputDialog(frame, "Enter Booking ID:");
        List<String> bookings = new ArrayList<>();
        boolean found = false;
        int i=0;
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] booking = line.split(",");
                if (booking[i].equals(bookingId)) {
                    found = true;
            
                } else {
                    bookings.add(line);
                }
                i++;
            }
            scanner.close();

            if (found) {            
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
                for (String booking : bookings) {
                    writer.write(booking);
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(frame, "Flight cancelled successfully.", "Flight Cancellation", JOptionPane.INFORMATION_MESSAGE);
                displayAvailableFlights();
                 
                
                showPassengerDetails();
            } else {
            	 throw new FlightBookingException("");
            	
            }
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(frame, "An error occurred while cancelling the flight: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    private void calculateFare() {
        String selectedDestination = destinationComboBox.getSelectedItem().toString();
        Flight selectedFlight = null;

        for (Flight flight : flights) {
            if (flight.getDestination().equals(selectedDestination)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight != null) {
            double fare = selectedFlight.getBaseFare();

            if (businessClassRadio.isSelected()) {
                fare *= 2;
            }

            if (roundTripCheckbox.isSelected()) {
                fare *= 2;
            }

            if (foodCheckbox.isSelected()) {
                fare += 20;
            }

            JOptionPane.showMessageDialog(frame, "The fare is: $" + fare, "Calculate Fare", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showPassengerDetails() {
        StringBuilder passengerDetails = new StringBuilder();
        String selectedDestination = destinationComboBox.getSelectedItem().toString();
          int i=0;
        	for (Passenger passenger : passengers) 
        	{
        	
                Flight flight = getFlightByFlightNumber(passenger.getFlightNumber());
                if(passenger.getBookingId().equals(bookingId))
                {
                	 selectedFlight.setCapacity(selectedFlight.getCapacity() + passenger.numOfTickets);
                	 displayAvailableFlights();
                	passengers.remove(i);  	
                	continue;	
                }
         
                else if (flight != null && flight.getDestination().equals(selectedDestination)) {
                    passengerDetails.append("Booking ID: ").append(passenger.getBookingId()).append("\n");
                    passengerDetails.append("Flight Number: ").append(passenger.getFlightNumber()).append("\n");
                    passengerDetails.append("Passenger Name: ").append(passenger.getPassengerName()).append("\n\n");
                }
                i++;
            }
        
        if (passengerDetails.length() > 0) {
            JOptionPane.showMessageDialog(frame, passengerDetails.toString(), "Passenger Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "No passengers found for the selected destination.", "Passenger Details", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Flight getFlightByFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}


