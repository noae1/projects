package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




public class StarfleetManager {

	// Spaceship - Comparator
	public static class SpaceShipComparator implements Comparator<Spaceship>{
		
		@Override
		public int compare(Spaceship o1, Spaceship o2) {
			int o1_fire = o1.getFirePower();
			int o2_fire = o2.getFirePower();
			if (o1_fire != o2_fire) {
				return Integer.compare(o2_fire, o1_fire);
			}
			if (o1.getCommissionYear() != o2.getCommissionYear()) {
				return Integer.compare(o2.getCommissionYear(), o1.getCommissionYear());
			}
			return o1.getName().compareTo(o2.getName());
		}
	}
	
	
	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		
		List <Spaceship> fleetList = new ArrayList <Spaceship> (fleet);
		Collections.sort(fleetList, new SpaceShipComparator());
		
		String str = "";
		List<String> descriptions = new ArrayList <String> ();
		
		for (Spaceship item : fleetList) {
			str = item.toString();
			descriptions.add(str);
		}
		return descriptions;
	}

	

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		
		Map <String, Integer> numPerClass = new <String, Integer> HashMap ();
		String className = "";
		
		for (Spaceship item : fleet) {
			className = item.getClass().getSimpleName();
			if (numPerClass.containsKey(className)) {
				numPerClass.put(className, numPerClass.get(className)+1 );
			}
			else {
				numPerClass.put(className, 1);
			}
		}
		
		return numPerClass;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		
		int sum = 0;
		for (Spaceship item : fleet) {
			sum += item.getAnnualMaintenanceCost();
		}
		
		return sum;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		
		Set<String> fleetWeaponNames = new <String>HashSet ();
		
		for (Spaceship item : fleet) {
			if (item instanceof MyAbstractFighter) {     // instanceof fighter
				MyAbstractFighter fighter = (MyAbstractFighter) item;
				List <Weapon> weapons = fighter.getWeapon();

				for (Weapon w :weapons) {
					fleetWeaponNames.add(w.getName());
				}
			}
		}
		
		return fleetWeaponNames;
	}

	
	/**
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		
		int sum = 0;
		for (Spaceship item : fleet) {
			sum += item.getCrewMembers().size();
		}
		
		return sum;
	}

	/**
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		
		int sum = 0;
		int cntOfficers = 0;
		
		for (Spaceship item : fleet) {
			Set<? extends CrewMember> members = item.getCrewMembers();
			List <Officer> officers = getOnlyOfficerMembers(members);
			for (Officer mem : officers) {
				sum += mem.getAge();
				cntOfficers += 1;
			}  
		}
		return (float)sum/cntOfficers;
	}

	// get list of officer members only
	public static List <Officer> getOnlyOfficerMembers (Set<? extends CrewMember> crewMembers){
		List <Officer> members = new <Officer> ArrayList ();
		for (CrewMember mem : crewMembers) {
			if (mem instanceof Officer) {
				members.add((Officer) mem);
			}
		}
		return members;
		
	}
	
	
	public static void sortByOfficerRank (List <Officer> officerMembers) {
		
		Collections.sort(officerMembers,
				new Comparator <Officer>() {
					public int compare(Officer e1, Officer e2) {
						return e2.getRank().compareTo(e1.getRank());
					}
				});
	} 
	
	
	/**
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		
		Map <Officer, Spaceship> HighestRankingOfficerPerShip = new <Officer, Spaceship> HashMap ();
		for (Spaceship item : fleet) {
			List <Officer> officers = getOnlyOfficerMembers(item.getCrewMembers());
			if (officers.size() != 0) {
				sortByOfficerRank(officers);
				HighestRankingOfficerPerShip.put(officers.get(0), item);
			}
		}
		
		return HighestRankingOfficerPerShip;
	}
	
	
	
	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		
		Map <OfficerRank, Integer> cntPerOfficerRanks = getNumAppearancesPerOfficerRanks(fleet);
		Set <Map.Entry<OfficerRank, Integer>> pairsSet = cntPerOfficerRanks.entrySet();
		List <Map.Entry<OfficerRank, Integer>> list_cntPerOfficerRanks = new <Map.Entry<OfficerRank, Integer>> ArrayList (pairsSet);
		
		//sort:
		Collections.sort(list_cntPerOfficerRanks,
				new Comparator <Map.Entry<OfficerRank, Integer>>() {
					public int compare( Map.Entry<OfficerRank, Integer> e1, Map.Entry<OfficerRank, Integer> e2) {
						if (e1.getValue() != e2.getValue()) {
							return e1.getValue().compareTo(e2.getValue());
						}
						return e1.getKey().compareTo( e2.getKey() );
					}
				});
	
		return list_cntPerOfficerRanks;
	
	}

	public static Map <OfficerRank, Integer> getNumAppearancesPerOfficerRanks (Collection<Spaceship> fleet){
		Map <OfficerRank, Integer> cntPerOfficerRanks = new <OfficerRank, Integer> HashMap ();
		OfficerRank rank = null;
		
		for (Spaceship item : fleet) {
			List <Officer> officers = getOnlyOfficerMembers(item.getCrewMembers());
			if (officers.size() != 0) {
				
				for (Officer off : officers) {
					rank = off.getRank();
					if (cntPerOfficerRanks.containsKey(rank)) {
						cntPerOfficerRanks.put(rank, cntPerOfficerRanks.get(rank)+1);
					}
					else {
						cntPerOfficerRanks.put(rank, 1);
					}
				}
			}
		}
		
		return cntPerOfficerRanks;
	}
	
	
	

	
	
}
