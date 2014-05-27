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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {

	// Initialise the text fields as global to use during events
	JTextField maxCarSpacesTxt;
	JTextField maxSmallCarSpacesTxt;
	JTextField maxMotorCycleSpacesTxt;
	JTextField maxQueueSizeTxt;
	
	private int maxCSpaces = Constants.DEFAULT_MAX_CAR_SPACES;
	private int maxSCSpaces = Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
	private int maxMCSpaces = Constants.DEFAULT_MAX_MOTORCYCLE_SPACES;
	private int maxQSize = Constants.DEFAULT_MAX_QUEUE_SIZE;
	
	JTextField seedTxt;
	JTextField meanStayTxt;
	JTextField sdStayTxt;
	JTextField carProbTxt;
	JTextField smallCarProbTxt;
	JTextField mCProbTxt;
	
	private int seed = Constants.DEFAULT_SEED;
	private double meanStay = Constants.DEFAULT_INTENDED_STAY_MEAN;
	private double sdStay = Constants.DEFAULT_INTENDED_STAY_SD;
	private double carProb = Constants.DEFAULT_CAR_PROB;
	private double smallCarProb = Constants.DEFAULT_SMALL_CAR_PROB;
	private double mCProb = Constants.DEFAULT_MOTORCYCLE_PROB;
	
	JTextArea errorArea;
	private String newLine = "\n";
	
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

		final int WIDTH = 750;
		final int HEIGHT = 500;
	
		JFrame.setDefaultLookAndFeelDecorated(true); 
		 
		//Create and set up the window. 
		JFrame frame = new JFrame("INB370 CarPark Simulation"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
		// Create the Variables for the Simulation
		
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
		carParkPanel.add(carParkLabel, "North");
		
		carParkPanel.add(maxCarSpacesLbl, "North");
		carParkPanel.add(maxCarSpacesTxt, "North");
		
		carParkPanel.add(maxSmallCarSpacesLbl, "North");
		carParkPanel.add(maxSmallCarSpacesTxt, "North");
		
		carParkPanel.add(maxMotorCycleSpacesLbl, "North");
		carParkPanel.add(maxMotorCycleSpacesTxt, "North");
		
		carParkPanel.add(maxQueueSizeLbl, "North");
		carParkPanel.add(maxQueueSizeTxt, "North");
		
		// End CarPark Panel
		
		// Start Simulator Panel
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
		
		simulatorPanel.add(seedLbl, "North");
		simulatorPanel.add(seedTxt, "North");
		
		simulatorPanel.add(meanStayLbl, "North");
		simulatorPanel.add(meanStayTxt, "North");
		
		simulatorPanel.add(sdStayLbl, "North");
		simulatorPanel.add(sdStayTxt,"North");
		
		simulatorPanel.add(carProbLbl, "North");
		simulatorPanel.add(carProbTxt, "North");
		
		simulatorPanel.add(smallCarProbLbl, "North");
		simulatorPanel.add(smallCarProbTxt, "North");
		
		simulatorPanel.add(mCProbLbl, "North");
		simulatorPanel.add(mCProbTxt, "North");
		
		// End Simulator Panel
		
		// Create a Start Button
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("Start the Simulation");
		buttonPanel.add(startButton, "Center");
		
		startButton.addActionListener(this);
		
		// Create an Error box to show invalid inputs
		JPanel errorPanel = new JPanel();
		errorArea = new JTextArea(5, 20);
		
		// Set up a scroll in case of numerous errors
		JScrollPane areaScrollPane = new JScrollPane(errorArea);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		
		// Set the error areas settings;
		errorArea.setBackground(Color.LIGHT_GRAY);
		errorArea.setEditable(false);
		errorPanel.add(errorArea);
		
		// Create and add to the Settings Panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.add(carParkPanel, "West");
		settingsPanel.add(simulatorPanel, "East");
		
		// Create the run simulations panel
		JPanel runSimPanel = new JPanel();
		runSimPanel.add(errorPanel);
		runSimPanel.add(buttonPanel);		
		// Add the panel to the window
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
		gui.run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// Reset GUI for new errors
		reset();
		
		// Check Values are greater than zero
		zeroGreaterThan(maxCarSpacesTxt);
		zeroGreaterThan(maxMotorCycleSpacesTxt);
		zeroGreaterThan(maxQueueSizeTxt);
		zeroGreaterThan(maxSmallCarSpacesTxt);
		
		// Check that the maxCarSpaces is greater than the maxSmallCarSpaces
		if(tryGettingInt(maxCarSpacesTxt) < tryGettingInt(maxSmallCarSpacesTxt)){
			maxCarSpacesTxt.setBackground(Color.RED);
			maxSmallCarSpacesTxt.setBackground(Color.RED);
		}
		
		/*	Check that the values are all in the correct format
		 *	Already checked maxCarSpaces, maxMotorCycleSpaces
		 *	, maxQueueSize and maxSmallCarSpaces above
		 */
		tryGettingInt(seedTxt);
		tryGettingDouble(meanStayTxt);
		tryGettingDouble(sdStayTxt);
		tryGettingDouble(carProbTxt);
		tryGettingDouble(smallCarProbTxt);
		tryGettingDouble(mCProbTxt);
	}


	private JPanel thePanelSettings(){
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	
	private JLabel theHeadingLabelSettings(String words){
		JLabel label = new JLabel(words);
		return label;
	}
	
	private JLabel theLabelSettings(String words){
		JLabel label = new JLabel(words);
		return label;
	}
	
	private JTextField theTextFieldSettings(String value, String name){
		JTextField textField = new JTextField(value);
		textField.setName(name);
		return textField;
	}

	@SuppressWarnings("null")
	private int tryGettingInt(JTextField text){
		try{
			return Integer.parseInt(text.getText());
		} catch (NumberFormatException e){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is not a integer (whole number)." + newLine);
			return (Integer) null;
		}	
	}
	
	private Double tryGettingDouble(JTextField text){
		try{
			return Double.parseDouble(text.getText());
		} catch (NumberFormatException e){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is not a number." + newLine);
			return (Double) null;
		}	
	}
	
	private void zeroGreaterThan(JTextField text){
		if(0 > tryGettingInt(text)){
			text.setBackground(Color.RED);
			errorArea.setText(errorArea.getText() + text.getName() + " is below zero." + newLine);
		}
	}

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
	}

}
