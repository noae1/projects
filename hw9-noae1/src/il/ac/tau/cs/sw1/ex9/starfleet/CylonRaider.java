package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends MyAbstractFighter{

	private static int BASIC_MAINT_COST = 3500;
	private static int CREW_MEMBER_MAINT_COST = 500;
	private static int ENGINE_MAINT_COST = 1200;
	
	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, (Set<? extends CrewMember>) crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int numCylon = this.getCrewMembers().size();
		int cost = CREW_MEMBER_MAINT_COST * numCylon;
		cost += BASIC_MAINT_COST + super.getWeaponsMaintenanceCost() + (int)ENGINE_MAINT_COST*this.getMaximalSpeed();
		return cost;
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("CylonRaider").append( System.lineSeparator() );
		str.append( super.toString() );

		return str.toString();
	}

	

}
