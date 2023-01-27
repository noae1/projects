package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StarfleetManagerTester {

	static int crewId=0; //used for generating unique names for crew members

	public static void main(String[] args) {

		System.out.println("*** STARFLEET COMMAND OFFICIAL REPORT ***\n");

		List<Spaceship> fleet = generateStarfleet(); //Generates fleet objects

		System.out.println("Fleet ships sorted by fire power and commission year:");
		for (String shipDescription : StarfleetManager.getShipDescriptionsSortedByFirePowerAndCommissionYear(fleet)) {
			System.out.println(shipDescription);
		}

		System.out.println();
		System.out.println("Ship counts by type:");
		Map<String, Integer> instancesNumberPerClass = StarfleetManager.getInstanceNumberPerClass(fleet);
		List<String> sortedNames = new ArrayList<>(instancesNumberPerClass.keySet());
		Collections.sort(sortedNames);
		for (String spaceshipType : sortedNames) {
			System.out.println("\t" + instancesNumberPerClass.get(spaceshipType) + "\t" + spaceshipType);
		}

		System.out.println();
		System.out.println("Weapon types:");
		SortedSet<String> weapons = new TreeSet<>(StarfleetManager.getFleetWeaponNames(fleet));
		for (String  weaponName: weapons) {
			System.out.println("\t" + weaponName);
		}

		System.out.println();
		System.out.println("Highest ranking officer per ship:");
		Map<Officer,Spaceship> officersToShipsMap = StarfleetManager.getHighestRankingOfficerPerShip(fleet); 
		SortedSet<Officer> sortedOfficers = new TreeSet<>(new Comparator<Officer>() {

			@Override
			public int compare(Officer o1, Officer o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		sortedOfficers.addAll(officersToShipsMap.keySet());
		for (Officer officer : sortedOfficers) {
			System.out.println("\t" + officer.getRank() + "\t" + officer.getName() + "\t" +  officersToShipsMap.get(officer) .getName());
		}

		
		System.out.println();
		System.out.println("Officer ranks sorted ascendingly by popularity:");
		for (Map.Entry<OfficerRank, Integer>  rankCountPair: StarfleetManager.getOfficerRanksSortedByPopularity(fleet)) {
			System.out.println("\t" + rankCountPair.getValue() + "\t" + rankCountPair.getKey());
		}

		System.out.printf("\nFleet Totals:\n");
		System.out.printf("\tTotal fleet crew members:\t\t\t%d\n", StarfleetManager.getTotalNumberOfFleetCrewMembers(fleet));
		System.out.printf("\tAverage age of fleet officers:\t\t\t%.2f\n", StarfleetManager.getAverageAgeOfFleetOfficers(fleet));
		System.out.printf("\tTotal annual maintenance cost:\t\t\t%d\n", StarfleetManager.getTotalMaintenanceCost(fleet));
	
	
		//check if same:
		String tester = "*** STARFLEET COMMAND OFFICIAL REPORT ***\r\n"
				+ "\r\n"
				+ "Fleet ships sorted by fire power and commission year:\r\n"
				+ "Fighter\r\n"
				+ "	Name=USS Defiant\r\n"
				+ "	CommissionYear=2423\r\n"
				+ "	MaximalSpeed=6.0\r\n"
				+ "	FirePower=290\r\n"
				+ "	CrewMembers=130\r\n"
				+ "	AnnualMaintenanceCost=8990\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Quantum Torpedoes, firePower=120, annualMaintenanceCost=100], Weapon [name=TAU Phasers, firePower=150, annualMaintenanceCost=280]]\r\n"
				+ "Bomber\r\n"
				+ "	Name=USS Yamaguchi \r\n"
				+ "	CommissionYear=2416\r\n"
				+ "	MaximalSpeed=9.9\r\n"
				+ "	FirePower=140\r\n"
				+ "	CrewMembers=233\r\n"
				+ "	AnnualMaintenanceCost=5185\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Photon Torpedoes, firePower=120, annualMaintenanceCost=260]]\r\n"
				+ "	NumberOfTechnicians=5\r\n"
				+ "ColonialViper\r\n"
				+ "	Name=Viper1\r\n"
				+ "	CommissionYear=2451\r\n"
				+ "	MaximalSpeed=7.2\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8500\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "ColonialViper\r\n"
				+ "	Name=Viper2\r\n"
				+ "	CommissionYear=2451\r\n"
				+ "	MaximalSpeed=7.2\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8500\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Galaxy\r\n"
				+ "	CommissionYear=2370\r\n"
				+ "	MaximalSpeed=9.0\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=4\r\n"
				+ "	AnnualMaintenanceCost=12050\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "CylonRaider\r\n"
				+ "	Name=Raider 1\r\n"
				+ "	CommissionYear=2056\r\n"
				+ "	MaximalSpeed=3.5\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8600\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Odyssey\r\n"
				+ "	CommissionYear=2419\r\n"
				+ "	MaximalSpeed=9.0\r\n"
				+ "	FirePower=20\r\n"
				+ "	CrewMembers=4\r\n"
				+ "	AnnualMaintenanceCost=11750\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Amsterdamer\r\n"
				+ "	CommissionYear=2410\r\n"
				+ "	MaximalSpeed=9.2\r\n"
				+ "	FirePower=20\r\n"
				+ "	CrewMembers=3\r\n"
				+ "	AnnualMaintenanceCost=11950\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\r\n"
				+ "TransportShip\r\n"
				+ "	Name=USS Lantree\r\n"
				+ "	CommissionYear=2457\r\n"
				+ "	MaximalSpeed=5.1\r\n"
				+ "	FirePower=10\r\n"
				+ "	CrewMembers=9\r\n"
				+ "	AnnualMaintenanceCost=48000\r\n"
				+ "	CargoCapacity=3000\r\n"
				+ "	PassengerCapacity=10000\r\n"
				+ "TransportShip\r\n"
				+ "	Name=USS Astral Queen\r\n"
				+ "	CommissionYear=2396\r\n"
				+ "	MaximalSpeed=5.1\r\n"
				+ "	FirePower=10\r\n"
				+ "	CrewMembers=23\r\n"
				+ "	AnnualMaintenanceCost=28000\r\n"
				+ "	CargoCapacity=2000\r\n"
				+ "	PassengerCapacity=5000\r\n"
				+ "\r\n"
				+ "Ship counts by type:\r\n"
				+ "	1	Bomber\r\n"
				+ "	2	ColonialViper\r\n"
				+ "	1	CylonRaider\r\n"
				+ "	1	Fighter\r\n"
				+ "	3	StealthCruiser\r\n"
				+ "	2	TransportShip\r\n"
				+ "\r\n"
				+ "Weapon types:\r\n"
				+ "	Evaporator\r\n"
				+ "	Laser Cannons\r\n"
				+ "	Photon Torpedoes\r\n"
				+ "	Quantum Torpedoes\r\n"
				+ "	TAU Phasers\r\n"
				+ "\r\n"
				+ "Highest ranking officer per ship:\r\n"
				+ "	Captain	Archer #171	USS Amsterdamer\r\n"
				+ "	Captain	Archer #27	USS Lantree\r\n"
				+ "	Lieutenant	Cara Thrace	Viper2\r\n"
				+ "	Captain	Data #167	USS Odyssey\r\n"
				+ "	Captain	FitzRoy #163	USS Galaxy\r\n"
				+ "	Captain	Lee Adama	Viper1\r\n"
				+ "	Captain	Nemo #9	USS Astral Queen\r\n"
				+ "	Captain	Picard #194	USS Yamaguchi \r\n"
				+ "	Captain	Sisko #52	USS Defiant\r\n"
				+ "\r\n"
				+ "Officer ranks sorted ascendingly by popularity:\r\n"
				+ "	7	LieutenantCommander\r\n"
				+ "	7	Commander\r\n"
				+ "	8	Captain\r\n"
				+ "	15	Lieutenant\r\n"
				+ "	22	Ensign\r\n"
				+ "\r\n"
				+ "Fleet Totals:\r\n"
				+ "	Total fleet crew members:			409\r\n"
				+ "	Average age of fleet officers:			42.66\r\n"
				+ "	Total annual maintenance cost:			151525\r\n";
		
		String my = "*** STARFLEET COMMAND OFFICIAL REPORT ***\r\n"
				+ "\r\n"
				+ "Fleet ships sorted by fire power and commission year:\r\n"
				+ "Fighter\r\n"
				+ "	Name=USS Defiant\r\n"
				+ "	CommissionYear=2423\r\n"
				+ "	MaximalSpeed=6.0\r\n"
				+ "	FirePower=290\r\n"
				+ "	CrewMembers=130\r\n"
				+ "	AnnualMaintenanceCost=8990\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Quantum Torpedoes, firePower=120, annualMaintenanceCost=100], Weapon [name=TAU Phasers, firePower=150, annualMaintenanceCost=280]]\r\n"
				+ "Bomber\r\n"
				+ "	Name=USS Yamaguchi \r\n"
				+ "	CommissionYear=2416\r\n"
				+ "	MaximalSpeed=9.9\r\n"
				+ "	FirePower=140\r\n"
				+ "	CrewMembers=233\r\n"
				+ "	AnnualMaintenanceCost=5185\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Photon Torpedoes, firePower=120, annualMaintenanceCost=260]]\r\n"
				+ "	NumberOfTechnicians=5\r\n"
				+ "ColonialViper\r\n"
				+ "	Name=Viper1\r\n"
				+ "	CommissionYear=2451\r\n"
				+ "	MaximalSpeed=7.2\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8500\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "ColonialViper\r\n"
				+ "	Name=Viper2\r\n"
				+ "	CommissionYear=2451\r\n"
				+ "	MaximalSpeed=7.2\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8500\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Galaxy\r\n"
				+ "	CommissionYear=2370\r\n"
				+ "	MaximalSpeed=9.0\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=4\r\n"
				+ "	AnnualMaintenanceCost=12050\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "CylonRaider\r\n"
				+ "	Name=Raider 1\r\n"
				+ "	CommissionYear=2056\r\n"
				+ "	MaximalSpeed=3.5\r\n"
				+ "	FirePower=50\r\n"
				+ "	CrewMembers=1\r\n"
				+ "	AnnualMaintenanceCost=8600\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Odyssey\r\n"
				+ "	CommissionYear=2419\r\n"
				+ "	MaximalSpeed=9.0\r\n"
				+ "	FirePower=20\r\n"
				+ "	CrewMembers=4\r\n"
				+ "	AnnualMaintenanceCost=11750\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\r\n"
				+ "StealthCruiser\r\n"
				+ "	Name=USS Amsterdamer\r\n"
				+ "	CommissionYear=2410\r\n"
				+ "	MaximalSpeed=9.2\r\n"
				+ "	FirePower=20\r\n"
				+ "	CrewMembers=3\r\n"
				+ "	AnnualMaintenanceCost=11950\r\n"
				+ "	WeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\r\n"
				+ "TransportShip\r\n"
				+ "	Name=USS Lantree\r\n"
				+ "	CommissionYear=2457\r\n"
				+ "	MaximalSpeed=5.1\r\n"
				+ "	FirePower=10\r\n"
				+ "	CrewMembers=9\r\n"
				+ "	AnnualMaintenanceCost=48000\r\n"
				+ "	CargoCapacity=3000\r\n"
				+ "	PassengerCapacity=10000\r\n"
				+ "TransportShip\r\n"
				+ "	Name=USS Astral Queen\r\n"
				+ "	CommissionYear=2396\r\n"
				+ "	MaximalSpeed=5.1\r\n"
				+ "	FirePower=10\r\n"
				+ "	CrewMembers=23\r\n"
				+ "	AnnualMaintenanceCost=28000\r\n"
				+ "	CargoCapacity=2000\r\n"
				+ "	PassengerCapacity=5000\r\n"
				+ "\r\n"
				+ "Ship counts by type:\r\n"
				+ "	1	Bomber\r\n"
				+ "	2	ColonialViper\r\n"
				+ "	1	CylonRaider\r\n"
				+ "	1	Fighter\r\n"
				+ "	3	StealthCruiser\r\n"
				+ "	2	TransportShip\r\n"
				+ "\r\n"
				+ "Weapon types:\r\n"
				+ "	Evaporator\r\n"
				+ "	Laser Cannons\r\n"
				+ "	Photon Torpedoes\r\n"
				+ "	Quantum Torpedoes\r\n"
				+ "	TAU Phasers\r\n"
				+ "\r\n"
				+ "Highest ranking officer per ship:\r\n"
				+ "	Captain	Archer #171	USS Amsterdamer\r\n"
				+ "	Captain	Archer #27	USS Lantree\r\n"
				+ "	Lieutenant	Cara Thrace	Viper2\r\n"
				+ "	Captain	Data #167	USS Odyssey\r\n"
				+ "	Captain	FitzRoy #163	USS Galaxy\r\n"
				+ "	Captain	Lee Adama	Viper1\r\n"
				+ "	Captain	Nemo #9	USS Astral Queen\r\n"
				+ "	Captain	Picard #194	USS Yamaguchi \r\n"
				+ "	Captain	Sisko #52	USS Defiant\r\n"
				+ "\r\n"
				+ "Officer ranks sorted ascendingly by popularity:\r\n"
				+ "	7	LieutenantCommander\r\n"
				+ "	7	Commander\r\n"
				+ "	8	Captain\r\n"
				+ "	15	Lieutenant\r\n"
				+ "	22	Ensign\r\n"
				+ "\r\n"
				+ "Fleet Totals:\r\n"
				+ "	Total fleet crew members:			409\r\n"
				+ "	Average age of fleet officers:			42.66\r\n"
				+ "	Total annual maintenance cost:			151525\r\n";
	
	
		if (my.equals(tester)) {
			System.out.println("same!!!");
		}
		else {
			System.out.println("dif!!!");
		}
	}

	// Generates a list of spaceship objects with synthesized data
	private static List<Spaceship> generateStarfleet() {
		List<Spaceship> fleet = new ArrayList<>();

		fleet.add(new TransportShip("USS Astral Queen", 2396, 5.1f, generateCrew (9,14), 2000,5000));
		fleet.add(new TransportShip("USS Lantree", 2457, 5.1f, generateCrew (4,5), 3000,10000));	

		List<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,110));
		weapons.add(new Weapon("Quantum Torpedoes",120,100));
		weapons.add(new Weapon("TAU Phasers",150,280));
		fleet.add(new Fighter("USS Defiant",2423,6f, generateCrew (20,110), weapons));

		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		fleet.add(new StealthCruiser("USS Galaxy",2370,9f, generateCrew (1,3), weapons));

		fleet.add(new StealthCruiser("USS Odyssey",2419,9f, generateCrew (1,3)));
		fleet.add(new StealthCruiser("USS Amsterdamer",2410,9.2f, generateCrew (1,2)));

		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,110));
		weapons.add(new Weapon("Photon Torpedoes",120,260));
		fleet.add(new Bomber("USS Yamaguchi ",2416,9.9f, generateCrew (21,212), weapons,5));
				
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		Set<Cylon> cylons = new HashSet<>();
		cylons.add(new Cylon("Sharon", 35, 35, 5));
		fleet.add(new CylonRaider("Raider 1", 2056, 3.5f, cylons, weapons));
		
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		Set<CrewWoman> crewWomen = new HashSet<>();
		crewWomen.add(new Officer("Lee Adama", 28, 5, OfficerRank.Captain));
		fleet.add(new ColonialViper("Viper1", 2451,7.2f, crewWomen, weapons));

		crewWomen = new HashSet<>();
		crewWomen.add(new Officer("Cara Thrace", 28, 5, OfficerRank.Lieutenant));
		fleet.add(new ColonialViper("Viper2", 2451,7.2f, crewWomen, weapons));


		return fleet;
	} 

	 
	
	
	// Generates a set containing crew-member objects containing synthesized data
	private static Set<CrewMember> generateCrew (int numberOfOfficers, int numberOfCrewmen) {

		Set<CrewMember> crew = new HashSet<>();

		//Generating Officers
		for (int i=0; i< numberOfOfficers-1; i++) {
			crewId++;
			String name = generateName();
			Integer age = generateAge();
			Integer yearsInService = generateYearsInService();
			OfficerRank rank = generateRank();

			crew.add(new Officer(name,age,yearsInService,rank));
		}

		// Adding one captain
		crewId++;
		crew.add(new Officer(generateName(),generateAge(),generateYearsInService(),OfficerRank.Captain));

		//Generating Crewmen
		for (int i=0; i< numberOfCrewmen; i++) {
			crewId++;
			String name = generateName();
			Integer age = generateAge();
			Integer yearsInService = generateYearsInService();

			crew.add(new CrewWoman(age,yearsInService,name));
		}

		return crew;
	}

	private static String generateName() {
		final String[] nameRepository = new String[]{"Riker", "Kathryn", "Picard", "Archer", "Sisko","Troi","Crusher", "FitzRoy", "Sparrow", "Nemo", "America","Data"};
		return  nameRepository[(crewId % (nameRepository.length))] + " #" +crewId;
	}

	private static Integer generateAge() {	
		final Integer[] ageRepository = new Integer[]{31, 47, 22, 21, 57, 104, 28, 19, 35, 64};
		return ageRepository[crewId % (ageRepository.length)];
	}

	private static Integer generateYearsInService() {	
		final Integer[] yearsRepository =  new Integer[]{7, 2, 14, 6, 32, 16, 12, 2, 1, 17, 5};
		return yearsRepository[crewId % (yearsRepository.length)];
	}

	private static OfficerRank generateRank() {	
		final OfficerRank[] ranksRepository =  new OfficerRank[]{OfficerRank.Ensign, OfficerRank.Ensign, OfficerRank.Commander, OfficerRank.Lieutenant, OfficerRank.Ensign, OfficerRank.LieutenantCommander,  OfficerRank.Lieutenant,};
		return ranksRepository[crewId % (ranksRepository.length)];
	}


}
