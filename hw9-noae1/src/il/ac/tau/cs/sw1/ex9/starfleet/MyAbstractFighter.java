package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class MyAbstractFighter extends MyAbstractSpaceship{
	
	private List<Weapon> weapons;
	
	
	public MyAbstractFighter (String name, int commissionYear, float maximalSpeed,
			Set<? extends CrewMember> crewMember, List<Weapon> weapons) {
		
		super(name, commissionYear, maximalSpeed, crewMember);
		this.weapons = weapons;
	}

	public List<Weapon> getWeapon() {
		return weapons;
	}
	
	
	public int getFirePower() {
		int result = 0;
		
		result = super.BasicFirePower;
		if (weapons.size() != 0) {
			
			for (Weapon item : weapons) {
				result += item.getFirePower();
			}
		}
		return result;
	}
	
	
	public int getWeaponsMaintenanceCost() {
		int cost = 0;
		
		if (weapons.size() != 0) {
			
			for (Weapon item : weapons) {
				cost += item.getAnnualMaintenanceCost();
			}
		}
		return cost;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append( super.toString() ).append( System.lineSeparator() );
		str.append("\tFirePower=").append(getFirePower()).append( System.lineSeparator() );
		str.append("\tCrewMembers=").append(super.getCrewMembers().size()).append( System.lineSeparator() );
		str.append("\tAnnualMaintenanceCost=").append(getAnnualMaintenanceCost()).append( System.lineSeparator() );
		str.append("\tWeaponArray=").append(getWeapon());
		
		return str.toString();
	}
	
	


}
