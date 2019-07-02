import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class read {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fileName = new File("/Users/hashmatibrahimi/eclipse-workspace/HackChain/src/my.key1");
		System.out.println();
		
		
}
	
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}
