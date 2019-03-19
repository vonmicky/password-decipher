import java.io.*;
import java.util.*;

public class PasswordDecipher {

	public static void main(String[] args) {
		String menu, msg, password, shadow, algorithm = null, time;
		int lineNumber, count = 0, hours, minutes, seconds, remainder;
		long startTime, endTime, elapsedTime;
		PrintWriter fileWriter = null;
		String passwordFile = "files/passwordFile.txt"; //a file contain list of password
		String shadowFile = "files/shadowFile.txt";// a file contain list of hash (md5, sha1 etc)
		String outputDir = "output"; //directory for saving passwords found
		String fileDate = String.format("%1$td%1$tb%1$tY-%1$tH%1$tM%1$tS", new Date());
		String outputFile = outputDir+"/Log "+fileDate+".txt"; //a file for printing passwords found
		
		Scanner input = new Scanner(System.in);
			
		System.out.println("\n** A Java Program for cracking Passwords **");
		System.out.println("Actually it tells which password was used to create a hash");
		System.out.println("that is located at a certain line in a shadow file.");
		System.out.print("\nSelect Hashing Algorithm Used:\n");
	    System.out.print("1 : MD5\n2 : SHA-1\n3 : SHA-256\n4 : SHA-512\n");
	    System.out.print("or type anything to quit: ");
	    
	    menu = input.next();
	    input.close();
		System.out.println();
		switch(menu) {
			case "1": 
				algorithm = "MD5";
			  break;
			case "2": 
				algorithm = "SHA-1";
			  break;
			case "3": 
				algorithm = "SHA-256";
			  break; 
			case "4": 
				algorithm = "SHA-512";
			  break;
		    default:
		    	System.out.print("GoodBye!");
		        System.exit(0);
		}
		
		msg = "Here are the list of password(s) that were used to create "+algorithm+" hashes :\n";
		System.out.println(msg);
		//Start a timer
		startTime = System.nanoTime(); 
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader pfReader = new FileReader(passwordFile);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedPf = new BufferedReader(pfReader);
			//Create directory and file for printing passwords found
			File file = new File(outputDir);
			if (!file.exists()) { file.mkdir(); }
			fileWriter = new PrintWriter(new FileWriter(outputFile));
			fileWriter.println(msg);
			fileWriter.println();
			//Read password from a password file
			while((password = bufferedPf.readLine()) != null){
				lineNumber = 0; // hash line number in a shadow file
				Hash objHash = new Hash(password, algorithm);
				String hash = objHash.getHash(); //create hash from a password
				FileReader sfReader = new FileReader(shadowFile);
				BufferedReader bufferedSf = new BufferedReader(sfReader);
				   //Read hash from a shadow file
		           while((shadow = bufferedSf.readLine()) != null) {
		        	   ++lineNumber; 
		        	   //compare hash from a password with a hash from a shadow file
						if(hash.equals(shadow)){
		            		System.out.println("Line"+lineNumber+": "+password); //prints on the console
		            		fileWriter.println("Line"+lineNumber+": "+password); //prints in the file
		            		++count; //count passwords found
		            	}
		           } bufferedSf.close();
			} bufferedPf.close();
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, a program was unable to open a file");
			System.out.println("\n*** Passwords Cracking Program, Terminated. ***");
			System.exit(0);
			
		} catch(IOException ex) {
			System.out.println("Error in creating or reading a file");
			System.out.println("\n*** Passwords Cracking Program, Terminated. ***");
			System.exit(0);
	    }
		
		// Stop a timer
		endTime = System.nanoTime(); 
		
		//find elapsed time and convert it into hours, minutes and second
		elapsedTime = endTime - startTime;
		double elapsedSeconds = (double)elapsedTime / 1000000000.0;
		seconds = (int)elapsedSeconds;
		if(seconds >= 3600 ){
			hours = seconds / 3600; 
			minutes = (seconds % 3600) / 60;
			remainder = seconds % 60;
			time = hours + " hour(s), " + minutes + " minute(s) and " + remainder + " seconds.";
		} else if(seconds >= 60 && seconds < 3600){
			minutes = seconds / 60;
			remainder = seconds % 60 ;
			time = minutes + " minute(s) and " + remainder + " seconds.";
		} else {
			time = seconds + " seconds.";
		}
		
		System.out.println("\n*** Passwords Cracking Successfully Finished. ***");
		msg = "A Program cracks ("+count+") Passwords in "+ time +"\n";
		System.out.print(msg);
		fileWriter.println();
		fileWriter.println(msg);
		fileWriter.close();
				
		
	}
}
