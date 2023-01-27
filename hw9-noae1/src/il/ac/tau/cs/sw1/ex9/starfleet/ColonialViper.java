package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends MyAbstractFighter {

	private static int BASIC_MAINT_COST = 4000;
	private static int CREW_MEMBER_MAINT_COST = 500;
	
	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, (Set<? extends CrewMember>) crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int numCrewWomens = this.getCrewMembers().size();
		int cost = CREW_MEMBER_MAINT_COST * numCrewWomens;
		cost += BASIC_MAINT_COST + super.getWeaponsMaintenanceCost() + (int)(500*this.getMaximalSpeed());
		return cost;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("ColonialViper").append( System.lineSeparator() );
		str.append( super.toString() );
		
		return str.toString();
	}

}
