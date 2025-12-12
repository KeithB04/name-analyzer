/*
 * This program takes a comma seperated list of names from the user, then repeatedly shows a menu that lets the user
 * analyze or filter those names in different ways. The menu options include sorting the names alphabetically, 
 * showing only full names(those with spaces), showing single names, calculating statistics(like count, total 
 * letters, average length, shortest/longest name, standard deviation), and filtering names by even/odd length or 
 * capatilization. It can also determine the most frequent name, allow the user to enter a new list, or exit the 
 * program. The program then keeps looping until the user chooses to quit.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class nameAnalyzer.
 */
public class nameAnalyzer {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		displayMenu(1);
		
	}
	
	/**
	 * Display menu.
	 *
	 * @param num the num
	 */
	public static void displayMenu(int num) {
		
		boolean flag = true;
		
		Scanner scnr = new Scanner(System.in);
		
		String input = getList(scnr);
		
		do {
			
			System.out.println("1) Display List Ordered");
			System.out.println("2) Display Full Names");
			System.out.println("3) Display Single Names");
			System.out.println("4) Display Name Statistics");
			System.out.println("5) Display Names with Even Length");
			System.out.println("6) Display Names with Odd Length");
			System.out.println("7) Display Names not Capitalized");
			System.out.println("8) Display Most Frequent Name");
			System.out.println("9) Enter new list of Names");
			System.out.println("0) Exit program");
			
			String selection = scnr.nextLine();
			
			switch(selection) {
			
			case "1":
				displayListOrdered(input);
				break;
			case "2":
				displayFullNames(input);
				break;
			case "3":
				displaySingleNames(input);
				break;
			case "4":
				displayNameStats(input);
				break;
			case "5":
				displayEvenNames(input);
				break;
			case "6":
				displayOddNames(input);
				break;
			case "7":
				displayNamesNoCaps(input);
				break;
			case "8":
				displayFrequentNames(input);
				break;
			case "9":
				input = getList(scnr);
				break;
			case "0":
				flag = false;
				break;
			default:
				System.out.println("Wrong Selection");
			}
		}while(flag);
	}
	
	/**
	 * Display list ordered.
	 *
	 * @param input the input
	 */
	//option 1
	public static void displayListOrdered(String input) {
		
		String[] names = input.split(",");
		
		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
		
		String result = String.join(", ", names);
		
		System.out.println("[" + result + "]"); 
		
	}
	
	/**
	 * Display full names.
	 *
	 * @param input the input
	 */
	//option 2
	public static void displayFullNames(String input) {
		
		String[] names = input.split(",");
		
		for(int i = 0; i < names.length; i++)
		{
			if(names[i].contains(" "))
			{
				System.out.println(names[i]);
			}
		}
		
	}
	
	/**
	 * Display single names.
	 *
	 * @param input the input
	 */
	//option 3
	public static void displaySingleNames(String input) {
		
		String[] names = input.split(",");
		
		for(int i = 0; i < names.length; i++)
		{
			if(!names[i].contains(" "))
			{
				System.out.println(names[i]);
			}
		}
		
	}

	/**
	 * Display name stats.
	 *
	 * @param input the input
	 */
	//option 4
	public static void displayNameStats(String input) {
		
		String[] names = input.split(",");
		
		int numNames = numNames(names);
		int numLetters = numLetters(input);
		double avgLength = (double) numLetters / numNames;
		String shortName = getShortName(names);
		String longName = getLongName(names);
		double standardDeviation = getStandardDeviation(avgLength, names, numNames);
		
		System.out.println("Name Count: " + numNames);
		System.out.println("Letter Count Total: " + numLetters);
		System.out.printf("Avg Name Length: %.2f\n", avgLength);
		System.out.println("Shortest Name: " + shortName);
		System.out.println("Longest Name: " + longName);
		System.out.printf("Population Standard Deviation: %.2f\n", standardDeviation);
		
	
	}
	
	/**
	 * Num names.
	 *
	 * @param names the names
	 * @return the int
	 */
	public static int numNames(String[] names) {
		int numNames = 0;
		
		for(int i = 0; i < names.length; i++)
		{
			numNames++;
		}
		
		return numNames;
	}
	
	/**
	 * Num letters.
	 *
	 * @param input the input
	 * @return the int
	 */
	public static int numLetters(String input) {
		int numLetters = 0;
		
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) != ' ' && input.charAt(i) != ',')
			{
				numLetters++;
			}
		}
		
		return numLetters;
	}
	
	/**
	 * Gets the short name.
	 *
	 * @param names the names
	 * @return the short name
	 */
	public static String getShortName(String[] names) {
		String shortName = names[0];
		
		for(int i = 0; i < names.length; i++)
		{
			if(names[i].trim().length() < shortName.length())
			{
				shortName = names[i];
			}
		}
		
		return shortName;
	}
	
	/**
	 * Gets the long name.
	 *
	 * @param names the names
	 * @return the long name
	 */
	public static String getLongName(String[] names) {
		String longName = names[0];
				
		for(int i = 0; i < names.length; i++)
		{
			if(names[i].trim().length() > longName.length())
			{
						longName = names[i];
			}
		}
				
		return longName;
	}
	
	/**
	 * Gets the standard deviation.
	 *
	 * @param mean the mean
	 * @param names the names
	 * @param numNames the num names
	 * @return the standard deviation
	 */
	public static double getStandardDeviation(double mean, String[] names, int numNames) {
		double standardDeviation = 0;
		
		double sumOfSquares = 0;
		
		//double sum = 0;
		
		for(int i = 0; i < numNames; i++)
		{
			sumOfSquares += Math.pow((names[i].replaceAll(" ", "").length() - mean), 2);
		}
		
		standardDeviation = Math.sqrt(sumOfSquares / numNames);
		
		return standardDeviation;
	}

	/**
	 * Display even names.
	 *
	 * @param input the input
	 */
	//option 5
	public static void displayEvenNames(String input) {
		
		String[] names = input.split(",");
		
		for(int i = 0; i < names.length; i++)
		{
			if(names[i].replaceAll(" ", "").length() % 2 == 0)
			{
				System.out.println(names[i]);
			}
		}
	
	}

	/**
	 * Display odd names.
	 *
	 * @param input the input
	 */
	//option 6
	public static void displayOddNames(String input) {
		
		String[] names = input.split(",");
		
		for(int i = 0; i < names.length; i++)
		{
			if(names[i].replaceAll(" ", "").length() % 2 != 0)
			{
				System.out.println(names[i]);
			}
		}
	
	}

	/**
	 * Display names no caps.
	 *
	 * @param input the input
	 */
	//option 7
	public static void displayNamesNoCaps(String input) {
		
		String[] names = input.split(",");
		
		for(int i = 0; i < names.length; i++)
		{
			String[] tempName = names[i].split(" ");
			for(int j = 0; j < tempName.length; j++)
			{
				if(!Character.isUpperCase(tempName[j].charAt(0)))
				{
					System.out.println(tempName[j]);
				}
			}
		}
	
	}

	/**
	 * Display frequent names.
	 *
	 * @param input the input
	 */
	//option 8
	public static void displayFrequentNames(String input) {
		String current;
		boolean found = false;
		String [] names = input.split(",");
		String mostFrequentName = "";
	
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		for(int i = 0; i < names.length; i++) 
		{
			current = names[i];
			found = false;
			
			for(int j = 0; j < name.size(); j++) 
			{
				if(name.get(j).equalsIgnoreCase(current)) 
				{
					count.set(j,  count.get(j) + 1);
					found = true;
				}
			}
			if(!found) 
			{
				name.add(current);
				count.add(1);
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < count.size(); i++)
		{
			if(count.get(i) > max)
			{
				max = count.get(i);
				mostFrequentName = name.get(i);
			}
		}
		
		if(max > 1)
		{
			System.out.println("Most Frequent Name: " + mostFrequentName);
		}
		else
		{
			System.out.println("No Most Frequent Name");
		}
		
		
	}
	
	/**
	 * Gets the list.
	 *
	 * @param scnr the scnr
	 * @return the list
	 */
	//option 9
	public static String getList(Scanner scnr) {
		System.out.println("Enter a list of names");
		
		String input = scnr.nextLine();
		
		return input;
	}

}


