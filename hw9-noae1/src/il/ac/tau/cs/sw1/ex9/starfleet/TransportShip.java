package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class TransportShip extends MyAbstractSpaceship{

	
	private int cargoCapacity;
	private int passengerCapacity;
	private static int BASIC_MAINT_COST = 3000;
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}
	
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	
	
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINT_COST + 5*cargoCapacity + 3*passengerCapacity;
		
	}
	
	public List<Weapon> getWeapon(){
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("TransportShip").append( System.lineSeparator() );
		str.append( super.toString() ).append( System.lineSeparator() );
		str.append("\tFirePower=").append(super.getFirePower()).append( System.lineSeparator() );
		str.append("\tCrewMembers=").append(super.getCrewMembers().size()).append( System.lineSeparator() );
		str.append("\tAnnualMaintenanceCost=").append(getAnnualMaintenanceCost()).append( System.lineSeparator() );
		
		str.append("\tCargoCapacity=").append(cargoCapacity).append( System.lineSeparator() );
		str.append("\tPassengerCapacity=").append(passengerCapacity);
		
		return str.toString();
	}
	
}
