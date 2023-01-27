package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyAbstractFighter{
	
	private static int BASIC_MAINT_COST = 2500;
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINT_COST + super.getWeaponsMaintenanceCost() + (int)Math.floor(1000*this.getMaximalSpeed());
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Fighter").append( System.lineSeparator() );
		str.append( super.toString() );
		
		return str.toString();
	}

	
	
}
