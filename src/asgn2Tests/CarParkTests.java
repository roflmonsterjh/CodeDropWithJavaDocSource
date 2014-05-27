/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 29/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Simulator;
import asgn2Vehicles.Car;

/**
 * @author hogan, Joshua Henley N8858594
 *
 */
public class CarParkTests {

	CarPark cp;
	Car s;
	Simulator sim;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		cp = new CarPark();
		s = new Car("testcar", 5, true);	
		sim = new Simulator();
		cp.tryProcessNewVehicles(6, sim);
		cp.tryProcessNewVehicles(7, sim);
		cp.tryProcessNewVehicles(8, sim);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 */
	@Test
	public void testArchiveDepartingVehicles() {
		//cp.archiveDepartingVehicles(time, force);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 */
	@Test
	public void testArchiveNewVehicle() throws SimulationException {
		cp.archiveNewVehicle(s);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test
	public void testArchiveQueueFailures() throws VehicleException, SimulationException {
		cp.archiveQueueFailures(8);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 */
	@Test
	public void testCarParkEmpty() {
		assertEquals(cp.carParkEmpty(), false);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}.
	 */
	@Test
	public void testCarParkFull() {
		assertEquals(cp.carParkFull(), false);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testEnterQueue() throws SimulationException, VehicleException {
		cp.enterQueue(s);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testExitQueue() throws SimulationException, VehicleException {
		cp.enterQueue(s);
		
		cp.exitQueue(s, 9);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#finalState()}.
	 */
	@Test
	public void testFinalState() {
		cp.finalState();
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 */
	@Test
	public void testGetNumCars() {
		cp.getNumCars();
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 */
	@Test
	public void testGetNumMotorCycles() {
		assertNotNull(cp.getNumMotorCycles());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 */
	@Test
	public void testGetNumSmallCars() {
		assertNotNull(cp.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getStatus(int)}.
	 */
	@Test
	public void testGetStatus() {
		assertNotNull(cp.getStatus(5));
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#initialState()}.
	 */
	@Test
	public void testInitialState() {
		assertNotNull(cp.initialState());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 */
	@Test
	public void testNumVehiclesInQueue() {
		assertNotNull(cp.numVehiclesInQueue());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testParkVehicle() throws SimulationException, VehicleException {
		cp.parkVehicle(s, 5, 20);
		
		assertEquals(s.isParked(), true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#processQueue(int, asgn2Simulators.Simulator)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test
	public void testProcessQueue() throws VehicleException, SimulationException {
		cp.processQueue(7, sim);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}.
	 */
	@Test
	public void testQueueEmpty() {
		assertEquals(cp.queueEmpty(), true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}.
	 */
	@Test
	public void testQueueFull() {
		assertEquals(cp.queueFull(), false);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 */
	@Test
	public void testSpacesAvailable() {
		assertEquals(cp.spacesAvailable(s), true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#toString()}.
	 */
	@Test
	public void testToString() {
		assertNotNull(cp.toString());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#tryProcessNewVehicles(int, asgn2Simulators.Simulator)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test
	public void testTryProcessNewVehicles() throws VehicleException, SimulationException {
		cp.tryProcessNewVehicles(8, sim);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle, int)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test
	public void testUnparkVehicle() throws VehicleException, SimulationException {
		cp.parkVehicle(s, 6, 20);
		
		cp.unparkVehicle(s, 25);
	}

}
