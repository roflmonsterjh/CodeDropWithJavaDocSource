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
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {

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
		JLabel carParkLabel = new JLabel("Car Park Settings");
		
		// Create the labels for this panel
		JLabel maxCarSpacesLbl = theLabelSettings("Max Car Spaces: ");
		JLabel maxSmallCarSpacesLbl = theLabelSettings("Max Small Car Spaces: ");
		JLabel maxMotorCycleSpacesLbl = theLabelSettings("Max MotorCycle Spaces: ");
		JLabel maxQueueSizeLbl = theLabelSettings("Max Queue Size: ");
		
		// Create the text boxes
		JTextField maxCarSpacesTxt = theTextFieldSettings();
		JTextField maxSmallCarSpacesTxt = theTextFieldSettings();
		JTextField maxMotorCycleSpacesTxt = theTextFieldSettings();
		JTextField maxQueueSizeTxt = theTextFieldSettings();
		
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
		JLabel simulatorLbl = theLabelSettings("Simulator Settings");
		
		// Create the labels for this panel
		JLabel seedLbl = theLabelSettings("Seed : ");
		JLabel meanStayLbl = theLabelSettings("meanStay: ");
		JLabel sdStayLbl = theLabelSettings("sdStay: ");
		JLabel carProbLbl = theLabelSettings("Car Probability: ");
		JLabel smallCarProbLbl = theLabelSettings("Small Car Probability: ");
		JLabel mCProbLbl = theLabelSettings("MotorCycle Probability: ");
		
		// Create the text boxes
		JTextField seedTxt = theTextFieldSettings();
		JTextField meanStayTxt = theTextFieldSettings();
		JTextField sdStayTxt = theTextFieldSettings();
		JTextField carProbTxt = theTextFieldSettings();
		JTextField smallCarProbTxt = theTextFieldSettings();
		JTextField mCProbTxt = theTextFieldSettings();
		
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
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.add(carParkPanel, "West");
		settingsPanel.add(simulatorPanel, "East");
		
		// Add the panel to the window
		frame.add(settingsPanel, "North");
		frame.add(buttonPanel, "South");
		
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

	private JPanel thePanelSettings(){
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	
	private JLabel theLabelSettings(String words){
		JLabel label = new JLabel(words);
		return label;
	}
	
	private JTextField theTextFieldSettings(){
		JTextField textField = new JTextField();
		return textField;
	}
	
}
