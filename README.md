#Name Analyzer
A Java console application that allows users to analyze and filter a comma-separated list of names. The program provides a menu-driven interface with options for sorting names, calculating statistics, identifying duplicate names, and filtering names based on different criteria.

##Features
1. Alphabetical Sorting: Displays names in alphabetical order, ignoring capitalization
1. Full Name Filtering: Displays names containing multiple words
1. Single Name Filtering: Displays names containing only one word
1. Name Statistics: Calculates the following:    
	Total number of names    
	Total number of letters    
	Average name length    
	Shortest and longest names    
	Population standard deviation of name lengths    
1. Even/Odd Length Filtering: Displays names with even or odd lengths
1. Capitalization Checking: Identifies names containing words that do not begin with an uppercase letter
1. Most Frequent Name: Finds the most frequently occurring name, ignoring capitalization
1. List Replacement: Allows users to enter a new list without restarting the program

Spaces are excluded when calculating name lengths

##Requirements
- JDK 8 or later
- A terminal or Java compatible IDE

##How to Run
1. Download or clone the repository
1. Open a terminal in the directory containing NameAnalyzer.java
1. Compile the program
1. Run the program
1. Put in list of names, for example: John Smith, Mary, alex, John Smith, SARAH
1. Pick which feature you would like the program to perform

##Inputs
- Names must be separated by commas
- Leading and trailing whitespace is automatically removed
- Empty entries are ignored
- At least one valid name must be entered
- Name comparisons for frequency calculations are case insensitive.
- If multiple names share the highest frequency, the first encountered name is displayed.
- If no name occurs more than once, the program displays "No Most Frequent Name".

##Technology Used
- Java
- ArrayList
- Arrays and string manipulation
- Basic statistical calculations

##Testing
The project includes a basic Java test file that verifies name parsing, letter counts, shortest and longest names, and population standard deviation.  
      
Compile and run the tests using:   
"javac NameAnalyzer.java NameAnalyzerTest.java" then    
"java NameAnalyzerTest"

##Author
Keith Brandenburg 


