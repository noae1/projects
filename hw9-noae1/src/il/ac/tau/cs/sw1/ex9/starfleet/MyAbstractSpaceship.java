package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;




public abstract class MyAbstractSpaceship implements Spaceship {

	protected static int BasicFirePower = 10;
		
	private String name;
	private int commissionYear;
	private float maximalSpeed;
	private Set <? extends CrewMember> crewMembers;
		
	
	public MyAbstractSpaceship (String name, int commissionYear, float maximalSpeed, Set <? extends CrewMember> crewMember) {
		super();
		this.name = name;
		this.commissionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMember;
	}
	
	public abstract int getAnnualMaintenanceCost();
	
	public String getName() {
		return name;
	}

	public int getCommissionYear() {
		return commissionYear;
	}

	public float getMaximalSpeed() {
		return maximalSpeed;
	}
	
	public Set<? extends CrewMember> getCrewMembers() {
		return this.crewMembers;
	}
	
	public int getFirePower() {
		return BasicFirePower;
	}
	
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\tName=").append(name).append( System.lineSeparator() );
		str.append("\tCommissionYear=").append(commissionYear).append( System.lineSeparator() );
		str.append("\tMaximalSpeed=").append(maximalSpeed);
		
		return str.toString();
		
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(commissionYear, maximalSpeed, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAbstractSpaceship other = (MyAbstractSpaceship) obj;
		return commissionYear == other.commissionYear
				&& Float.floatToIntBits(maximalSpeed) == Float.floatToIntBits(other.maximalSpeed)
				&& Objects.equals(name, other.name);
	}
	
	
	
	
	
	
	


}
