import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	
	public static void write (String s , File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		fw.write(s);
		
		fw.close();
		
	}
//	public static void main(String[] args) throws IOException {
//		File f = new File("FileEncrypted.txt");
//		String s = "JHKJHJKHKJHJKHJKHJKHJKkjchvnioskdnvoikldznoivkln oiklvjnsdoiklvjcnoidkslzjfnmiovksdl,nvoiklds,jnfiokvlsdhnzviokldsjznmovkl,jsdmzioxfklvjsdmokjsdncio";
//		write(s, f);
//		Desktop.getDesktop().open(f);
//		
//		
//	
//}
	
}
