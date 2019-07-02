

import java.io.IOException;

public class Tester {
	
	public static void main(String[] args) throws IOException {
		
		//System.out.println(System.getProperty("user.dir"));
		
		Cryptographer c = new Cryptographer();
		
		String[] set1 = c.encrypt("DeezNuts");
		String[] set2 = c.encrypt("bolas");
		
		// encrypt returns a string array of 2 elements, essentially a pair
		// the first element is the ciphertext
		// the second element is the hashtext
		
		System.out.println("Decrypted Key 1: " + c.decrypt(set1[0], set1[1]));
		System.out.println("Decrypted Key 2: " + c.decrypt(set2[0], set2[1]));
	}
	
}
