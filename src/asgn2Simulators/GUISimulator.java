/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 20/04/2014
 * 
 */
package asgn2Simulators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {

	// Initialise the text fields as global to use during events
		// CarPark
	JTextField maxCarSpacesTxt;
	JTextField maxSmallCarSpacesTxt;
	JTextField maxMotorCycleSpacesTxt;
	JTextField maxQueueSizeTxt;
	
	private static int maxCSpaces;
	private static int maxSCSpaces;
	private static int maxMCSpaces;
	private static int maxQSize;
	
		// Simulator
	JTextField seedTxt;
	JTextField meanStayTxt;
	JTextField sdStayTxt;
	JTextField carProbTxt;
	JTextField smallCarProbTxt;
	JTextField mCProbTxt;
	
	private static int seed;
	private static double meanStay;
	private static double sdStay;
	private static double carProb;
	private static double smallCarProb;
	private static double mCProb;

		// Error Field
	static JTextArea errorArea;
	private String newLine = "\n";
	private boolean errorDetected = false;
	
	// Window Settings
	final int WIDTH = 750;
	final int HEIGHT = 500;

	final String TITLE = "INB370 Car Park Simulation";

	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		 
		//Create and set up the window. 
		JFrame.setDefaultLookAndFeelDecorated(true); 

		JFrame frame = new JFrame(TITLE); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
	// Create the widgets for the Simulation
		
		// Create the car park panel
		JPanel carParkPanel = createCarParkPanel();
		
		// Create the simulator panel
		JPanel simulatorPanel = createSimulatorPanel();
		
		
		// Create a Start Button
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("Start the Simulation");
		buttonPanel.add(startButton, "Center");
		
		startButton.addActionListener(this);
		
		// Create an Error box to show invalid inputs
		JPanel errorPanel = new JPanel();
		errorArea = new JTextArea(10, 25);
		
		// Set the error areas settings;
		errorArea.setBackground(Color.LIGHT_GRAY);
		errorArea.setEditable(false);
		errorPanel.add(errorArea);
		
		// Create and add to the Settings Panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.add(carParkPanel, "West");
		settingsPanel.add(simulatorPanel, "East");
		
		// Create and add to the run Simulations Panel
		JPanel runSimPanel = new JPanel();
		runSimPanel.add(errorPanel, "North");
		runSimPanel.add(buttonPanel, "South");		
		
		// Add the panels to the window
		frame.add(settingsPanel, "North");
		frame.add(runSimPanel, "South");
		
		//Display the window. 
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack(); 
		frame.setVisible(true); 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(""); //TODO - change "" for args

		// Check the arg lengths and set the Text Field values
		if(args.length == 10){
			constantTextFieldInitialisor(); // TODO - change for actual values
		}if(args.length == 0){
			constantTextFieldInitialisor();
		}else{
			errorArea.setText("There were an incorrect amount of arguments passed.");
			constantTextFieldInitialisor();
		}
		
		// run the gui
		gui.run();
	}

	/**
	 * Action Event for when the start button is clicked. This checks the user's values and 
	 * either runs the program or stops it and declares errors.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// Reset GUI for new errors
		reset();
		
		// Check Values are greater than zero
		textFieldsGreaterThanZero();
		
		// Check that the maxCarSpaces is greater than the maxSmallCarSpaces
		if(tryGettingInt(maxCarSpacesTxt) < tryGettingInt(maxSmallCarSpacesTxt)){
			maxCarSpacesTxt.setBackground(Color.RED);
			maxSmallCarSpacesTxt.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + maxCarSpacesTxt.getName() + " is smaller than " 
					+ maxSmallCarSpacesTxt.getName() + ".");
		}
		
		// If no errors in the given  values then: 
		if(!errorDetected){
			// TODO - run the program
			statisticMultiLineGraph();
			summaryBarGraph();
		}
	}

	/**
	 * Creates a JPanel and applies initial settings
	 * @return - the created panel with settings applied
	 */
	private JPanel thePanelSettings(){
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBackground(Color.lightGray);
		panel.setPreferredSize(new Dimension(200, 250));
		return panel;
	}
	
	/**
	 * Creates a heading label and applies initial settings
	 * @param words - what the label is going to say
	 * @return - a created label with settings applied
	 */
	private JLabel theHeadingLabelSettings(String words){
		JLabel label = new JLabel(words);
		label.setFont(new Font(Font.SANS_SERIF,Font.ITALIC+Font.BOLD,16));
		return label;
	}
	
	/**
	 * Creates a normal label and applies it's initial settings
	 * @param words - what the label is going to say
	 * @return - A label with applied settings
	 */
	private JLabel theLabelSettings(String words){
		JLabel label = new JLabel(words);
		label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		return label;
	}
	
	/**
	 * Creates a JTextField with it's initial settings
	 * @param value - The value of the text field before the user changes it
	 * @param name - The fields name
	 * @return - a JTextField with applied settings
	 */
	private JTextField theTextFieldSettings(String value, String name){
		JTextField textField = new JTextField(value);
		textField.setName(name);
		return textField;
	}

	/**
	 * Creates the Car Park Panel and applies settings
	 * @return - JPanel with settings applied
	 */
	private JPanel createCarParkPanel(){

		// Start of CarPark Panel
		JPanel carParkPanel = thePanelSettings();
		
			// Create a Header for the carPark settings
		JLabel carParkLabel = theHeadingLabelSettings("Car Park Settings");
		
			// Create the labels for this panel
		JLabel maxCarSpacesLbl = theLabelSettings("Max Car Spaces ");
		JLabel maxSmallCarSpacesLbl = theLabelSettings("Max Small Car Spaces ");
		JLabel maxMotorCycleSpacesLbl = theLabelSettings("Max MotorCycle Spaces ");
		JLabel maxQueueSizeLbl = theLabelSettings("Max Queue Size ");
		
			// Create the text boxes
		maxCarSpacesTxt = theTextFieldSettings(""+maxCSpaces, maxCarSpacesLbl.getText());
		maxSmallCarSpacesTxt = theTextFieldSettings(""+maxSCSpaces, maxSmallCarSpacesLbl.getText());
		maxMotorCycleSpacesTxt = theTextFieldSettings(""+maxMCSpaces, maxMotorCycleSpacesLbl.getText());
		maxQueueSizeTxt = theTextFieldSettings(""+maxQSize, maxQueueSizeLbl.getText());
		
			// Add the labels and text fields to the panel
		carParkPanel.add(carParkLabel);
		
		carParkPanel.add(maxCarSpacesLbl);
		carParkPanel.add(maxCarSpacesTxt);
		
		carParkPanel.add(maxSmallCarSpacesLbl);
		carParkPanel.add(maxSmallCarSpacesTxt);
		
		carParkPanel.add(maxMotorCycleSpacesLbl);
		carParkPanel.add(maxMotorCycleSpacesTxt);
		
		carParkPanel.add(maxQueueSizeLbl);
		carParkPanel.add(maxQueueSizeTxt);
		
		return carParkPanel;
	}

	/**
	 * Creates the Simulator Panel with it's initial settings
	 * @return - A JPanel with applied settings
	 */
	private JPanel createSimulatorPanel(){

		JPanel simulatorPanel = thePanelSettings();
		
		// Create the Heading
		JLabel simulatorLbl = theHeadingLabelSettings("Simulator Settings");
		
		// Create the labels for this panel
		JLabel seedLbl = theLabelSettings("Seed ");
		JLabel meanStayLbl = theLabelSettings("meanStay ");
		JLabel sdStayLbl = theLabelSettings("sdStay ");
		JLabel carProbLbl = theLabelSettings("Car Probability ");
		JLabel smallCarProbLbl = theLabelSettings("Small Car Probability ");
		JLabel mCProbLbl = theLabelSettings("MotorCycle Probability ");
		
		// Create the text boxes
		seedTxt = theTextFieldSettings(""+seed, seedLbl.getText());
		meanStayTxt = theTextFieldSettings(""+meanStay, meanStayLbl.getText());
		sdStayTxt = theTextFieldSettings(""+sdStay, sdStayLbl.getText());
		carProbTxt = theTextFieldSettings(""+carProb, carProbLbl.getText());
		smallCarProbTxt = theTextFieldSettings(""+smallCarProb, smallCarProbLbl.getText());
		mCProbTxt = theTextFieldSettings(""+mCProb, mCProbLbl.getText());
		
		// Add the labels and text fields to the panel
		simulatorPanel.add(simulatorLbl, "North");
		
		simulatorPanel.add(seedLbl);
		simulatorPanel.add(seedTxt);
		
		simulatorPanel.add(meanStayLbl);
		simulatorPanel.add(meanStayTxt);
		
		simulatorPanel.add(sdStayLbl);
		simulatorPanel.add(sdStayTxt);
		
		simulatorPanel.add(carProbLbl);
		simulatorPanel.add(carProbTxt);
		
		simulatorPanel.add(smallCarProbLbl);
		simulatorPanel.add(smallCarProbTxt);
		
		simulatorPanel.add(mCProbLbl);
		simulatorPanel.add(mCProbTxt);

		return simulatorPanel;
	}

	/**
	 * Gets a text field and converts its stored value to its corresponding int 
	 * or sends the user a written error and turns the field red
	 * @param text - what a text field has stored 
	 * @return - either 0, if the value is 0 or not an int OR 
	 * 			 the integer that is stored as a String
	 */
	private int tryGettingInt(JTextField text){
		try{
			return Integer.parseInt(text.getText());
		} catch (NumberFormatException e){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is not a integer (whole number)." + newLine);
			errorDetected = true;
			return 0;
		}	
	}
	
	/**
	 * Gets a text field and converts its stored value to its corresponding double 
	 * or sends the user a written error and turns the field red
	 * @param text - what a text field has stored 
	 * @return - either 0.0, if the value is 0.0 or not a double OR 
	 * 			 the double that is stored as a String
	 */
	private double tryGettingDouble(JTextField text){
		try{
			return Double.parseDouble(text.getText());
		} catch (NumberFormatException e){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is not a number." + newLine);
			errorDetected = true;
			return 0.0;
		}	
	}
	
	/**
	 * Checks if zero is greater than the value and if the value is an int.
	 * If its not it provides an error message and turns the text field red.
	 * @param text - the text field to be checked
	 */
	private void zeroGreaterThanInt(JTextField text){
		if(0 > tryGettingInt(text)){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is below zero." + newLine);
			errorDetected = true;
		}
	}

	/**
	 * Checks if zero is greater than the value and if the value is a double.
	 * If it is not, than provides an error message an turns the text field red.
	 * @param text - the text field to be checked
	 */
	private void zeroGreaterThanDbl(JTextField text){
		if(0 > tryGettingDouble(text)){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is below zero." + newLine);
			errorDetected = true;
		}
	}

	/**
	 * Checks all the text field values to be greater than zero
	 */
	private void textFieldsGreaterThanZero(){
		zeroGreaterThanInt(maxCarSpacesTxt);
		zeroGreaterThanInt(maxMotorCycleSpacesTxt);
		zeroGreaterThanInt(maxQueueSizeTxt);
		zeroGreaterThanInt(maxSmallCarSpacesTxt);
		zeroGreaterThanInt(seedTxt);
		zeroGreaterThanDbl(meanStayTxt);
		zeroGreaterThanDbl(sdStayTxt);
		zeroGreaterThanDbl(carProbTxt);
		zeroGreaterThanDbl(smallCarProbTxt);
		zeroGreaterThanDbl(mCProbTxt);
	}

	/**
	 * Resets the errorArea and the TextFields backgrounds so that 
	 * only the latest errors are showing. (the errors from when the
	 * user hits the start button)
	 */
	private void reset(){

		// Clear error area of previous errors
		errorArea.setText("");
		
		// Reset the background of the text fields;
		maxCarSpacesTxt.setBackground(Color.WHITE);
		maxSmallCarSpacesTxt.setBackground(Color.WHITE);
		maxMotorCycleSpacesTxt.setBackground(Color.WHITE);
		maxQueueSizeTxt.setBackground(Color.WHITE);
		carProbTxt.setBackground(Color.WHITE);
		smallCarProbTxt.setBackground(Color.WHITE);
		mCProbTxt.setBackground(Color.WHITE);
		seedTxt.setBackground(Color.WHITE);
		meanStayTxt.setBackground(Color.WHITE);
		sdStayTxt.setBackground(Color.WHITE);
	
		// Set the errorDetected variable to false
		errorDetected = false;
	}

	/**
	 * Sets the text fields to the constant values
	 */
	private static void constantTextFieldInitialisor(){
		
		//CarPark Initialise
		maxCSpaces = Constants.DEFAULT_MAX_CAR_SPACES;
		maxSCSpaces = Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
		maxMCSpaces = Constants.DEFAULT_MAX_MOTORCYCLE_SPACES;
		maxQSize = Constants.DEFAULT_MAX_QUEUE_SIZE;
		
		// Simulator Initialise
		seed = Constants.DEFAULT_SEED;
		meanStay = Constants.DEFAULT_INTENDED_STAY_MEAN;
		sdStay = Constants.DEFAULT_INTENDED_STAY_SD;
		carProb = Constants.DEFAULT_CAR_PROB;
		smallCarProb = Constants.DEFAULT_SMALL_CAR_PROB;
		mCProb = Constants.DEFAULT_MOTORCYCLE_PROB;		
	}

	/**
	 * Creates a Bar Graph of the simulation
	 */
	private void summaryBarGraph(){
		
		// Create the data set
		DefaultCategoryDataset dataSet = dataSet();
		
		// Create the chart
		JFreeChart objChart = ChartFactory.createBarChart(
			       "Summary Satisfaction levels",     //Chart title
			    "Vehicle Type",     //Domain axis label
			    "Customers",         //Range axis label
			    dataSet,          
			    PlotOrientation.VERTICAL, 
			    true,             // include legend
			    true,             // include tooltips
			    false             //  don't include URLs
			);
		
		// TODO - Currently creates a new frame, you can choose if you want to change it
		// Create it on a new frame
		ChartFrame barGraph = new ChartFrame("Demo", objChart);
		barGraph.pack();
		barGraph.setVisible(true);
	}

	/**
	 * Creates a dataSet for the bar graph
	 * @return - A dataSet with the data from the simulation
	 */
	private DefaultCategoryDataset dataSet(){
		
		// Initialize the data set
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		//TODO - this section needs the results of the simulation.
		// Initialize the values
		int totalCar = 50;
		int totalSmallCar = 30;
		int totalMotorCycle = 15;
		
		int dissatisfiedCar = 25;
		int dissatisfiedSmallCar = 10;
		int dissatisfiedMotorCycle = 5;
		// END TODO
		
		// Create the data sets
		dataSet.setValue(totalCar,"Total Customers","Car");
		dataSet.setValue(totalSmallCar,"Total Customers","Small Car");
		dataSet.setValue(totalMotorCycle,"Total Customers","Motor Cycle");
		
		dataSet.setValue(dissatisfiedCar,"Disatisfied Customers","Car");
		dataSet.setValue(dissatisfiedSmallCar,"Disatisfied Customers","Small Car");
		dataSet.setValue(dissatisfiedMotorCycle,"Disatisfied Customers","Motor Cycle");
	
		return dataSet;
	}
	
	/**
	 * Creates a line Graph of the statistics
	 */
	private void statisticMultiLineGraph(){
		
		// Create the collection of data sets
		XYSeriesCollection dataSetCollection = dataSetCollection();
        
        // Create the chart
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Vehicles Line Chart",   // chart title
            "Time",                  // x axis label
            "Vehicle Number",        // y axis label
            dataSetCollection,                  
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // no urls
        );

        // Change the line colours
        lineColourChange(chart, dataSetCollection);
        
        // Create the Panel
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(750, 500));
        setContentPane(chartPanel);
		
        // TODO - once again decide if new window or different one and change necessary
        // Create the window 
    	ChartFrame lineGraph = new ChartFrame("Statistics of the Simulation", chart);
		lineGraph.pack();
	    RefineryUtilities.centerFrameOnScreen(lineGraph);
        lineGraph.setVisible(true);
	}
	
	/**
	 * Creates the data set for the line graph
	 * @return the series collection of the data
	 */
	private XYSeriesCollection dataSetCollection(){
    	
		// TODO - change for the actual time
		final int time = 10;
		
		// Create the XY series
		final XYSeries numVehicles = new XYSeries("Number of Vehicles");
		final XYSeries currentlyParked = new XYSeries("Currently Parked Vehicles");
		final XYSeries numCarsInCarPark = new XYSeries("Total Cars in Car Park");
		final XYSeries numSmallCarsInCarPark = new XYSeries("Number of Small Cars in Car Park");
		final XYSeries numMotorCyclesInCarPark = new XYSeries("Number Motor Cycles in CarPark");
		final XYSeries numVehInQueue = new XYSeries("Number of Vehicles in Queue");
		final XYSeries numDissatisfied = new XYSeries("Number Archived");
		
		// TODO - .add(time, value) change for complete
		for(int timeThrough = 0; timeThrough < time; timeThrough++){
			numVehicles.add(timeThrough, timeThrough + 1);
			currentlyParked.add(timeThrough, timeThrough + 2);
			numCarsInCarPark.add(timeThrough, timeThrough + 4);
			numSmallCarsInCarPark.add(timeThrough, timeThrough + 3);
			numMotorCyclesInCarPark.add(timeThrough, timeThrough + 5);
			numVehInQueue.add(timeThrough, timeThrough + 6);
			numDissatisfied.add(timeThrough, timeThrough + 7);
		}
		
		// Create the collection
		XYSeriesCollection dataSetCollection = new XYSeriesCollection();
        
		// Add to the collection
		dataSetCollection.addSeries(numVehicles);
        dataSetCollection.addSeries(currentlyParked);
        dataSetCollection.addSeries(numCarsInCarPark);
        dataSetCollection.addSeries(numSmallCarsInCarPark);
        dataSetCollection.addSeries(numMotorCyclesInCarPark);
        dataSetCollection.addSeries(numVehInQueue);
        dataSetCollection.addSeries(numDissatisfied);
        
        return dataSetCollection;
    }
	
	/**
	 * Changes the colour of the lines for the line graph
	 */
    private void lineColourChange(JFreeChart chart, XYSeriesCollection dataSetCollection){

        // Get the plot from the chart
    	XYPlot plot = (XYPlot) chart.getPlot(); 
        
    	// Create a line and shape renderer
        XYLineAndShapeRenderer theSet = new XYLineAndShapeRenderer();
        
        // Change the colour of the lines (index, colour)
        theSet.setSeriesPaint(0, Color.BLACK); 
        theSet.setSeriesPaint(1, Color.BLUE); 
        theSet.setSeriesPaint(2, Color.CYAN);
        theSet.setSeriesPaint(3, Color.GRAY); 
        theSet.setSeriesPaint(4, Color.DARK_GRAY); 
        theSet.setSeriesPaint(5, Color.YELLOW);
        theSet.setSeriesPaint(4, Color.GREEN); 
        theSet.setSeriesPaint(5, Color.RED);
        
        plot.setDataset(0, dataSetCollection);
        plot.setRenderer(0, theSet);	
    }
}
