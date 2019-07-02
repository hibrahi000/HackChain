

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Cryptographer {
	// imports an encrypting/decrypting python script to use for the class
	// down the line (Scalability)
	// this is the beginnings of the translation to java, which can be used by
	// hospitals with java frameworks can use this, and potentially in Android as well
	
	// The basis of how it works is that it runs the python script and captures the console output
	
	// For full program functionality, user's machine must have python installed as well as the
	// cryptography library for python
	private static final String PYTHON_DIR = 
			"/usr/bin/python";
	
	private static final String CRYPTO_DIR =
			//System.getProperty("user.dir").replace('\\', '/') + "/crypto.py";
			"/Users/hashmatibrahimi/eclipse-workspace/HackChain/src/crypto.py";
	
	private static final String KEY_FILE = 
			"/Users/hashmatibrahimi/eclipse-workspace/HackChain/src/my.key1";
	
	private static final String PROJ_DIR =
			"/Users/hashmatibrahimi/eclipse-workspace/HackChain";
	
	public String[] encrypt(String str) throws IOException {
	// precondition:	str is a password entered by the user
	// postcondition:	uses the python script to encrypt the string
	//					the string is outputted to the console and captured by this program
	//					the string is then appended to a file
		
		String[] commands = {
				PYTHON_DIR, CRYPTO_DIR, "-e", str, "--genkey"
		};
		
		Process process = Runtime.getRuntime().exec(commands);
		
		String ciphertext = "";
		String hashingtext = "";
		
		try {
			
			BufferedReader bfr = new BufferedReader(
					new InputStreamReader(process.getInputStream()) );
			
			// guard against errors in the python file
			BufferedReader errorchecker = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
			String errline;
			if ((errline = errorchecker.readLine()) != null) {
				System.err.println("Error in python code detected.");
				System.err.println(errline);
				
				while((errline = errorchecker.readLine()) != null) {
					System.err.println(errline);
				}
				
				System.exit(-1);
			}
			
			ciphertext = bfr.readLine();
			
			// this one god forsaken line
//			ciphertext = ciphertext.substring(0, ciphertext.length() - 1);
			
			bfr.readLine();
			hashingtext = bfr.readLine();
			
			
			System.out.println(hashingtext);
			System.out.println(ciphertext);
			
		} catch (IOException ioe) {
			
			System.err.println("IO Exception Thrown from encrypt(str = " + str + ")");
			ioe.printStackTrace();
			System.exit(-1);
			
		} catch (Exception e) {
			System.err.println("Unknown exception occurred in encrypt function");
			e.printStackTrace();
			System.exit(-1);
		}
		
		// now that ciphertext is acquired, append it to existing file
		// the python script does this, but when called from here it doesn't seem to work
		
		try(FileWriter fw = new FileWriter(PROJ_DIR + "\\my.key1", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)) {
			
			out.println(ciphertext);
			    
		} catch (IOException ioe) {
		    //exception handling left as an exercise for the reader
			System.err.println("IO Exception Thrown from encrypt(ciphertext writing)");
			ioe.printStackTrace();
			System.exit(-1);
		}
		
		//System.out.println("output written to file");
		
		String[] result = { ciphertext, hashingtext };
		
		return result;
	}
	
	public String decrypt(String cipher, String hashing) throws IOException {
	// precondition:	user enters the password key
	// postcondition:	key is encrypted, before being searched within the key file
	//					this will then return the decrypted key
	//					if key is not present, will return null
		
		String decryptedkey = null;
		
		String[] commands = {
				PYTHON_DIR, CRYPTO_DIR, "-d", hashing, "-k", cipher
		};
		
		Process process = Runtime.getRuntime().exec(commands);
		
		try {
			
			BufferedReader bfr = new BufferedReader(
					new InputStreamReader(process.getInputStream()) );
			
			// guard against errors in the python file
			BufferedReader errorchecker = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			
			String errline;
			if ((errline = errorchecker.readLine()) != null) {
				System.err.println("Error in python code detected.");
				System.err.println(errline);
				
				while((errline = errorchecker.readLine()) != null) {
					System.err.println(errline);
				}
				
				System.exit(-1);
			}
			
			decryptedkey = bfr.readLine();
			
			System.out.println(decryptedkey);
			
		} catch (IOException ioe) {
			System.err.println("IOException thrown in decrypt method.");
			ioe.printStackTrace();
			System.exit(-1);
		}
		
		return decryptedkey;
	}
}
