import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OpenFrame extends JFrame{

	public OpenFrame(){
		ImageIcon trascrYpt = new ImageIcon("/Users/hashmatibrahimi/eclipse-workspace/HackChain/src/transcrYpt.jpg");
		JLabel productName = new JLabel();
		productName.setIcon(trascrYpt);
		productName.setPreferredSize(new Dimension(200,200));
		
		this.setBackground(Color.blue);
		this.setLayout(new FlowLayout());
		
		
		JButton enter = new JButton("ENTER");
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}

			
			
		});
		
		
		this.add(productName);
		this.add(enter);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

}
