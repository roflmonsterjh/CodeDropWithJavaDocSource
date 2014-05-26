/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 22/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author hogan
 * @author Jarrod Eades N8855722
 *
 */
public class MotorCycleTests {
	// Constants	
	int ARRIVAL_TIME = 10;
	int PARKING_TIME = 35;
	int INTENDED_DURATION = 30;
	int DEPARTURE_TIME = PARKING_TIME+20;
	int EXIT_TIME = ARRIVAL_TIME + 15;
	
	String VEH_ID = "123ABC";
	String VEH_ID_2 = "456DEF";
	
	// Variables 
	MotorCycle theVehicle;
	MotorCycle theSecondVehicle;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		theVehicle = new MotorCycle(VEH_ID, ARRIVAL_TIME);
		theSecondVehicle = new MotorCycle(VEH_ID_2, ARRIVAL_TIME + 15);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/******** Tests from UnitTests.txt ******/
	
	/* MotorCycleTests */
	/*
	 * Confirm that the API spec has not been violated through the
	 * addition of public fields, constructors or methods that were
	 * not requested
	 */
	@Test
	public void NoExtraPublicMethods() {
		//MotorCycle Class implements Vehicle
		final int NumVehicleClassMethods = Array.getLength(Vehicle.class.getMethods());
		final int NumMotorCycleClassMethods = Array.getLength(MotorCycle.class.getMethods());
		assertTrue("veh:"+NumVehicleClassMethods+":MotorCycle:"+NumMotorCycleClassMethods,(NumVehicleClassMethods)==NumMotorCycleClassMethods);
	}
	
	@Test 
	public void NoExtraPublicFields() {
		//Same as Vehicle 
		final int NumVehicleClassFields = Array.getLength(Vehicle.class.getFields());
		final int NumMotorCycleClassFields = Array.getLength(MotorCycle.class.getFields());
		assertTrue("veh:"+NumVehicleClassFields+":MotorCycle:"+NumMotorCycleClassFields,(NumVehicleClassFields)==NumMotorCycleClassFields);
	}
	
	@Test 
	public void NoExtraPublicConstructors() {
		//Same as Vehicle
		final int NumVehicleClassConstructors = Array.getLength(Vehicle.class.getConstructors());
		final int NumMotorCycleClassConstructors = Array.getLength(MotorCycle.class.getConstructors());
		assertTrue(":veh:"+NumVehicleClassConstructors+":mc:"+NumMotorCycleClassConstructors,(NumVehicleClassConstructors)==NumMotorCycleClassConstructors);
	}
	
	/************ Constructor Tests *************/
	
	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 * @throws VehicleException 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testMotorCycle() throws VehicleException {
		MotorCycle motorCycle = new MotorCycle(VEH_ID, ARRIVAL_TIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 */
	@SuppressWarnings("unused")
	@Test(expected= VehicleException.class)
	public void testArrivalTimeZeroException() throws VehicleException{
		MotorCycle motorCycle = new MotorCycle(VEH_ID, ARRIVAL_TIME - ARRIVAL_TIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 */
	@SuppressWarnings("unused")
	@Test(expected= VehicleException.class)
	public void testArrivalTimeNegOneException() throws VehicleException{
		MotorCycle motorCycle = new MotorCycle(VEH_ID, ARRIVAL_TIME - ARRIVAL_TIME - 1);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testArrivalTimePosOneNoException() throws VehicleException{
		MotorCycle motorCycle = new MotorCycle(VEH_ID, ARRIVAL_TIME-ARRIVAL_TIME + 1);
	}
	
	/***************************\	
	|***************************|
	|*Vehicle Super Class Tests*|
	|***************************|
	\***************************/	
	
	/************ enterParkedState() Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test
	public void testEnterParkedState() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
	}
	
		// Test for Exception when already parked
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test(expected= VehicleException.class)
	public void testEnterParkedStateAlreadyParked() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.enterParkedState(PARKING_TIME+5, INTENDED_DURATION);
	}
	
		// Test for Exception when already queued
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test(expected= VehicleException.class)
	public void testEnterParkedStateAlreadyQueued() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
	}
	
		// Test for Exception when parkingTime is less than zero
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test(expected= VehicleException.class)
	public void testEnterParkedStateParkingTimeNegOne() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME-PARKING_TIME-1, INTENDED_DURATION);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test
	public void testEnterParkedStateParkingTimeZero() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME-PARKING_TIME, INTENDED_DURATION);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test
	public void testEnterParkedStateParkingTimePosOne() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME-PARKING_TIME +1, INTENDED_DURATION);
	}
	
		// Test for Exception when intendedDuration is less than the MINIMUM_STAY constant
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test(expected=VehicleException.class)
	public void testEnterParkedStateIntendedDurationLessThanMin() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, Constants.MINIMUM_STAY-1);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test
	public void testEnterParkedStateIntendedDurationEqualToMin() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, Constants.MINIMUM_STAY);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterParkedState()}.
	*/
	@Test
	public void testEnterParkedStateIntendedDurationGreaterThanMin() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, Constants.MINIMUM_STAY+1);
	}
	
	/************ enterQueuedState Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	*/
	@Test
	public void testEnterQueuedState() throws VehicleException{
		theVehicle.enterQueuedState();
	}
		
		// Test for Exception when already parked
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	*/
	@Test(expected = VehicleException.class)
	public void testEnterQueuedStateAlreadyParked() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.enterQueuedState();
	}
	
		// Test for Exception when already queued
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	*/
	@Test(expected = VehicleException.class)
	public void testEnterQueuedStateAlreadyQueued() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.enterQueuedState();
	}
	
	/************ exitParkedState Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test
	public void testExitParkedState() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(DEPARTURE_TIME);
	}
	
		// Test for Exception when not in parked state
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitParkedStateNotParked() throws VehicleException{
		theVehicle.exitParkedState(DEPARTURE_TIME);
	}
	
		// Test for Exception when in queued state
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitParkedStateQueuedState() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitParkedState(DEPARTURE_TIME);
	}
	
		// Test for Exception when revised departureTime is less than parkingTime
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitParkedStateDepartureLessThanParkingTime() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(PARKING_TIME-1);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test
	public void testExitParkedStateDepartureEqualToParkingTime() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(PARKING_TIME);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	* @throws VehicleException 
	*/
	@Test
	public void testExitParkedStateDepartureGreaterThanParkingTime() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(PARKING_TIME + 1);
	}
	
	/************ exitQueuedState Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test
	public void testExitQueuedState() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(EXIT_TIME);
	}
	
		// Test for Exception if not in queued state
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitQueuedStateNotQueued() throws VehicleException{
		theVehicle.exitQueuedState(EXIT_TIME);
	}
	
		// Test for Exception if vehicle parked
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitQueuedStateParked() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitQueuedState(EXIT_TIME);
	}
	
		// Test for Exception if exitTime <= arrivalTime
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitQueuedStateExitLessThanArrival() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(ARRIVAL_TIME-1);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test(expected = VehicleException.class)
	public void testExitQueuedStateExitEqualToArrival() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(ARRIVAL_TIME);
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState()}.
	* @throws VehicleException 
	*/
	@Test
	public void testExitQueuedStateExitGreaterThanArrival() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(ARRIVAL_TIME+1);
	}
	
	/************ getArrivalTime Tests *************/
	
		// Single Vehicle Test
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	*/
	@Test
	public void testGetArrivalTime(){
		assertEquals("The arrival times are not the same.", ARRIVAL_TIME, theVehicle.getArrivalTime() );
	}
	
		// Multiple Vehicle Test
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	*/
	@Test
	public void testGetArrivalTimeMultipleVehicles() throws VehicleException {
		assertTrue(theVehicle.getArrivalTime() != theSecondVehicle.getArrivalTime());
	}
	
	/************ getDepartureTime Tests *************/
	
		// Before parking result should be zero
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	*/
	@Test
	public void testGetDepartureTimeNoPark(){
		assertEquals("The departure time doesn't match.", 0, 
			theVehicle.getDepartureTime());
	}
	
		// After parking
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	* @throws VehicleException 
	*/
	@Test
	public void testGetDepartureTimeAfterPark() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertEquals("The departure time doesn't match.", PARKING_TIME + INTENDED_DURATION,
			theVehicle.getDepartureTime());
	}
	
		// Exited parking
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	* @throws VehicleException 
	*/
	@Test
	public void testGetDepartureTimeExitedPark() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(DEPARTURE_TIME);
		assertEquals("The departure time doesn't match.", DEPARTURE_TIME,
				theVehicle.getDepartureTime());
	}
	
	/* Test Two Vehicles to make sure that the departure times of two 
	 * vehicles are not overwriting each other  */
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	* @throws VehicleException 
	*/	
	@Test
	public void testGetDepartureMultipleVehicleExitedPark() throws VehicleException {
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theSecondVehicle.enterParkedState(PARKING_TIME+5, INTENDED_DURATION);
		
		theVehicle.exitParkedState(DEPARTURE_TIME);
		theSecondVehicle.exitParkedState(DEPARTURE_TIME+10);
		assertTrue(theVehicle.getDepartureTime() != theSecondVehicle.getDepartureTime());
	}
	
	
	/************ getParkingTime Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	* @throws VehicleException 
	*/
	@Test
	public void testGetParkingTime() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertEquals("The parking time doesn't match.", PARKING_TIME,
				theVehicle.getParkingTime());
	}
	
		// Multiple Vehicle Test
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	* @throws VehicleException 
	*/
	@Test
	public void testGetParkingTimeMultipleVehicles() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theSecondVehicle.enterParkedState(PARKING_TIME + 10, INTENDED_DURATION);
		assertTrue(theVehicle.getParkingTime() != theSecondVehicle.getParkingTime());
	}
	
	/************ getVehID Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	*/
	@Test
	public void testGetVehID(){
		assertEquals("The vehicle ID is not the same.", VEH_ID, theVehicle.getVehID() );
	}
	
		// Multiple Vehicle Test
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	*/
	@Test
	public void testGetVehIDMultipleVehicles(){
		assertTrue(theVehicle.getVehID() != theSecondVehicle.getVehID());
	}
	
	/************ isParked Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsParkedFalse(){
		assertFalse(theVehicle.isParked());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsParkedTrue() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertTrue(theVehicle.isParked());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsParkedMultipleVehiclesFalse() throws VehicleException{
		theSecondVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertFalse(theVehicle.isParked());
	}
	
	/************ isQueued Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsQueuedFalse(){
		assertFalse(theVehicle.isQueued());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsQueuedTrue() throws VehicleException{
		theVehicle.enterQueuedState();
		assertTrue(theVehicle.isQueued());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsQueuedMultipleVehicles() throws VehicleException{
		theSecondVehicle.enterQueuedState();
		assertFalse(theVehicle.isQueued());
	}
	
	/************ isSatisfied Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsSatisfiedFalse(){
		assertFalse(theVehicle.isSatisfied());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsSatisfiedTrue() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertTrue(theVehicle.isSatisfied());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	* @throws VehicleException 
	*/
	@Test
	public void testIsSatisfiedMultipleVehicles() throws VehicleException{
		theSecondVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		assertFalse(theVehicle.isSatisfied());
	}
	
	
	/************ wasParked Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasParkedFalse(){
		assertFalse(theVehicle.wasParked());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasParkedTrue() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(DEPARTURE_TIME);
		assertTrue(theVehicle.wasParked());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasParkedMultipleVehicles() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(DEPARTURE_TIME);
		assertFalse(theSecondVehicle.wasParked());
	}
	
	/************ wasQueued Tests *************/
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasQueuedFalse(){
		assertFalse(theVehicle.wasQueued());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasQueuedTrue() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(EXIT_TIME);
		assertTrue(theVehicle.wasQueued());
	}
	
	/**
	* Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	* @throws VehicleException 
	*/
	@Test
	public void testWasQueuedMultipleVehicle() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(EXIT_TIME);
		assertFalse(theSecondVehicle.wasQueued());
	}
	

	
	/************ toString() Tests *************/

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testToString() {
		System.out.println(theVehicle.toString());
		fail("Check Console");
	}
	
		// Test the result if queued
	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testToStringQueued() throws VehicleException{
		theVehicle.enterQueuedState();
		theVehicle.exitQueuedState(EXIT_TIME);
		System.out.println(theVehicle.toString());
		fail("Check Console");	
	}


		// Test the result if parked which also checks satisfied
	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testToStringParked() throws VehicleException{
		theVehicle.enterParkedState(PARKING_TIME, INTENDED_DURATION);
		theVehicle.exitParkedState(DEPARTURE_TIME);
		System.out.println(theVehicle.toString());
		fail("Check Console"); 	
	}
}
