import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



 
public class FileTransferMF extends JFrame{

	
	private String UserPrivateKey;
	private String UserPublicKey;
	private String privateKey;
	private String publicKey;
	public String hash;
	
	
	public FileTransferMF() {
	JFrame FileTransferME = new JFrame("Block Chain Encryptor");
	FileTransferME.setMinimumSize(new Dimension(700,500));
	FileTransferME.setMaximumSize(new Dimension(700,600));
	FileTransferME.setBackground(Color.lightGray);
	FileTransferME.setLayout(new FlowLayout());
	
	Cryptographer crypt = new Cryptographer();
	

ImageIcon transfer = new ImageIcon("/Users/hashmatibrahimi/eclipse-workspace/HackChain1/src/blue.jpg");
ImageIcon transGood = new ImageIcon("/Users/hashmatibrahimi/eclipse-workspace/HackChain1/src/Green.jpg");
ImageIcon transBad = new ImageIcon("/Users/hashmatibrahimi/eclipse-workspace/HackChain1/src/red.jpg");





JFileChooser fc = new JFileChooser("Select file");
 
JTextField fileName = new JTextField();

JTextField key = new JTextField();
JLabel lblForKey = new JLabel("Private Key");
JTextArea display = new JTextArea();
display.setPreferredSize(new Dimension(400,400));


JLabel lblTrans = new JLabel(transfer);


JButton btnEncrypt = new JButton("Encrypt and Transport");
	btnEncrypt.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setUserPublicKey(JOptionPane.showInputDialog(null, "Enter your Public Key"));
					//System.out.print(getUserPublicKey());
					String Record = fileName.getText();
					String file = "";
					try {
						file = readFile(Record);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						String[] encryptInfo = crypt.encrypt(file);
						setPrivateKey(encryptInfo[0]);
						hash = encryptInfo[1];
//						display.append(hash);
						System.out.println(privateKey);
						File newF = createFile("Encrypted_Doc");
						String path = newF.getPath();
						write(hash, newF);
						fileName.setText(path);
						
						desktopOpen(newF);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
			
		
		
	});
	
	
	
	
	JButton btnRead = new JButton("Decrypt and read");
	btnRead.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			boolean tryAgain = true;
			while (tryAgain) {
				lblTrans.setIcon(transfer);
				setUserPrivateKey(JOptionPane.showInputDialog("Enter Private Key"));
			if(getUserPrivateKey().equals(getPrivateKey())==true) {
				lblTrans.setIcon(transGood);
			try {
			
				
				File f = new File(fileName.getText());
				write(crypt.decrypt(privateKey, hash), f );
			
				desktopOpen(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tryAgain = false;
			}
			else if(getUserPrivateKey().equals(getPrivateKey()) == false){
				lblTrans.setIcon(transBad);
				int repeat =JOptionPane.showConfirmDialog(null, "Sorry, \nWould you like to try again?");
				if(repeat == 0) {
					tryAgain =true;
					
				}
				else if(repeat == 1) {
				  tryAgain = false;
					
				  
				}
				
			}
			
			}
		}	
		
	});
	
	
	

	JButton btnBrowse = new JButton("Browse");
	
	fileName.setPreferredSize(new Dimension(500, 40));
	btnBrowse.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		fc.setCurrentDirectory(fc.getCurrentDirectory());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		System.out.println(fc.getCurrentDirectory());
		if (fc.showOpenDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
			fileName.setText(fc.getSelectedFile().getPath());
		}
		
								
		}
			
		
		
	});
	
	

	
	JTextField email = new JTextField("Email");
	email.addMouseListener(new MouseAdapter()
			{
		
			public void mouseEntered(MouseEvent e) {	
					lblTrans.setIcon(transfer);
			}
			});
	email.setPreferredSize(new Dimension(500,30));
	
	
	JButton clear = new JButton("Clear");
	clear.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		email.setText("Email");
		fileName.setText("File");
		}	
		
		
	});
	
	
	JButton send = new JButton("send");
	send.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				lblTrans.setIcon(transBad);				
			}													
	});
	
	
	
	FileTransferME.add(fileName);
	FileTransferME.add(btnBrowse);
	FileTransferME.add(email);
	FileTransferME.add(send);
	FileTransferME.add(btnRead);
	FileTransferME.add(btnEncrypt);
	FileTransferME.add(clear);
	//FileTransferME.add(display);
	FileTransferME.add(lblTrans, BorderLayout.PAGE_END);
	
	
	
	
	FileTransferME.setVisible(true);
	FileTransferME.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setPrivateKey(String key) {
		privateKey = key;
		
	}
	private String getPrivateKey() {
		return privateKey;
	}
	
	private void setUserPrivateKey(String s) {
		UserPrivateKey = s;
	}
	private String getUserPrivateKey() {
		return UserPrivateKey;
	}
private void setUserPublicKey(String key) {
	UserPublicKey = key;
	
}
private String getUserPublicKey() {
	return UserPublicKey;
}

private String readFile(String fileName) throws IOException {
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


public static void write (String s , File f) throws IOException {
	FileWriter fw = new FileWriter(f);
	fw.write(s);
	
	fw.close();
	
}

private void desktopOpen(File f) throws IOException {
	Desktop.getDesktop().open(f);
}

private File createFile(String fileName) {
	File file = new File(fileName+".txt");
	return file;
}

}



	

	
	





