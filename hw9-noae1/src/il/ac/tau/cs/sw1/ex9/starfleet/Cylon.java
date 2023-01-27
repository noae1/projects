package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends MyAbstractCrewMember {

	private int modelNumber;  // 0-12
	
	public Cylon (String name, int age, int yearsInService, int modelNumber) {
		super(name, age, yearsInService);
		this.modelNumber = modelNumber;
	}
	
	public int getModelNumber() {
		return modelNumber;
	}
	

}
