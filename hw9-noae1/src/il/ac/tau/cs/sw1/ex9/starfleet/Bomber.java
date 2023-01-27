package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MyAbstractFighter{

	private int numberOfTechnicians;   //0-5
	private static int BASIC_MAINT_COST = 5000;
	
	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}
	
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINT_COST + (int)(Math.round((1-((double)numberOfTechnicians/10))*super.getWeaponsMaintenanceCost()));
	}
	
	public int getNumberOfTechnicians() {
		return numberOfTechnicians;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Bomber").append( System.lineSeparator() );
		str.append( super.toString() ).append( System.lineSeparator() );
		
		str.append("\tNumberOfTechnicians=").append(numberOfTechnicians);
		
		return str.toString();
	}


}
