/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 23/04/2014
 * 
 */
package asgn2Simulators;

import java.io.IOException;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

/**
 * Class to operate the simulation, taking parameters and utility methods from the Simulator
 * to control the CarPark, and using Log to provide a record of operation. 
 * @author hogan
 *
 */
public class SimulationRunner {
	private CarPark carPark;
	private Simulator sim;
	
	private Log log;
	
	/**
	 * Constructor just does initialisation 
	 * @param carPark CarPark currently used 
	 * @param sim Simulator containing simulation parameters
	 * @param log Log to provide logging services 
	 */
	public SimulationRunner(CarPark carPark, Simulator sim,Log log) {
		this.carPark = carPark;
		this.sim = sim;
		this.log = log;
	}
	
	
	/**
	 * Method to run the simulation from start to finish. Exceptions are propagated upwards from Vehicle,
	 * Simulation and Log objects as necessary 
	 * @throws VehicleException if Vehicle creation or operation constraints violated 
	 * @throws SimulationException if Simulation constraints are violated 
	 * @throws IOException on logging failures
	 */
	public void runSimulation() throws VehicleException, SimulationException, IOException {
		this.log.initialEntry(this.carPark,this.sim);
		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
			//queue elements exceed max waiting time
			if (!this.carPark.queueEmpty()) {
				//carPark.josh();
				this.carPark.archiveQueueFailures(time);
			}
			//vehicles whose time has expired
			if (!this.carPark.carParkEmpty()) {
				//force exit at closing time, otherwise normal
				boolean force = (time == Constants.CLOSING_TIME);
				this.carPark.archiveDepartingVehicles(time, force);
			}
			//attempt to clear the queue 
			if (!this.carPark.carParkFull()) {
				this.carPark.processQueue(time,this.sim);
			}
			// new vehicles from minute 1 until the last hour
			if (newVehiclesAllowed(time)) { 
				this.carPark.tryProcessNewVehicles(time,this.sim);
			}
			//Log progress 
			this.log.logEntry(time,this.carPark);
		}
		this.log.finalise(this.carPark);
	}

	/**
	 * Main program for the simulation 
	 * @param args Arguments to the simulation 
	 * @throws SimulationException - if one or more constructor values invalid
	 */
	public static void main(String[] args) throws SimulationException {
		CarPark cp = new CarPark();
		Simulator s = null;
		Log l = null; 
		try {
			s = new Simulator();
			l = new Log();
		} catch (IOException | SimulationException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		/**	
		 * Simulator [meanStay=120.0, sdStay=39.6, seed=100, carProb=1.0, smallCarProb=0.2, mcProb=0.05]
		 * CarPark [maxCarSpaces: 100 maxSmallCarSpaces: 30 maxMotorCycleSpaces: 20 maxQueueSize: 10]
		 * @author Jarrod Eades N8855722
		 */	
		
		// Check if there are more than 10 arguments
		if(args.length > 10){
			System.err.println("There are to many arguments.");
        	System.exit(1);
		}
		
		// Check if there are 10 arguments
		if(args.length == 10){
			
			// Check First Argument 
			double meanStay = 0.0;
			try { 
				meanStay = Double.parseDouble(args[0]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[0] + " must be a double.");
	        	System.exit(1);
	    	}
			
			// Check Second Argument
			double sdStay = 0.0;
			try {
				sdStay = Double.parseDouble(args[1]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[1] + " must be a double.");
	        	System.exit(1);
	    	}
			
			// Check Third Argument
			int seed = 0;
			try {
				seed = Integer.parseInt(args[2]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[2] + " must be an integer.");
	        	System.exit(1);
	    	}
			
			// Check Fourth Argument
	    	double carProb = 0.0;
			try {
				carProb = Double.parseDouble(args[3]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[3] + " must be a double.");
	        	System.exit(1);
	    	}
			
			// Check Fifth Argument
			double smallCarProb = 0.0;
	    	try {
				smallCarProb = Double.parseDouble(args[4]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[4] + " must be a double.");
	        	System.exit(1);
	    	}
	    	
	    	// Check Sixth Argument
	    	double mcProb = 0.0;
	    	try {
				mcProb = Double.parseDouble(args[5]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[5] + " must be a double.");
	        	System.exit(1);
	    	}
	    	
	    	// Check Seventh Argument
	    	int maxCarSpaces = 0;
	    	try {
				maxCarSpaces = Integer.parseInt(args[6]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[6] + " must be an integer.");
	        	System.exit(1);
	    	}
	    	
	    	// Check Eighth Argument
	    	int maxSmallCarSpaces = 0;
	    	try {
				maxSmallCarSpaces = Integer.parseInt(args[7]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[7] + " must be an integer.");
	        	System.exit(1);
	    	}
	    	
	    	// Check Ninth Argument
	    	int maxMotorCycleSpaces = 0;
	    	try {
				maxMotorCycleSpaces = Integer.parseInt(args[8]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[8] + " must be an integer.");
	        	System.exit(1);
	    	}
	    	
	    	// Check Tenth Argument
	    	int maxQueueSize = 0;
	    	try {
				maxQueueSize = Integer.parseInt(args[9]);
	    	} catch (NumberFormatException e) {
	        	System.err.println("Argument" + args[9] + " must be an integer.");
	        	System.exit(1);
	    	}
	    	
	    	// Using the arguments, create the CarPark and Simulator
			cp = new CarPark(maxCarSpaces, maxSmallCarSpaces, maxMotorCycleSpaces, maxQueueSize);
			s = new Simulator(seed, meanStay, sdStay, carProb, smallCarProb, mcProb);
		}
		
		//Run the simulation 
		SimulationRunner sr = new SimulationRunner(cp,s,l);
		try {
			sr.runSimulation();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} 
	} 

	/**
	 * Helper method to determine if new vehicles are permitted
	 * @param time int holding current simulation time
	 * @return true if new vehicles permitted, false if not allowed due to simulation constraints. 
	 */
	private boolean newVehiclesAllowed(int time) {
		boolean allowed = (time >=1);
		return allowed && (time <= (Constants.CLOSING_TIME - 60));
	}

}
