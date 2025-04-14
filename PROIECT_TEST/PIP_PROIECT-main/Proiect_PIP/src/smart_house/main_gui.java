package smart_house;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Smart House");
		setSize(800, 600);
		
		setLayout(null);
		JButton b1 = new JButton("Next");
		JButton b2 = new JButton("Close");
		b1.setBounds(570, 500, 100, 40); // Buton "Close"
		b2.setBounds(120, 500, 100, 40); // Buton "Next"
		
		b1.addActionListener(e -> {
		    dispose();         
		    new control();        
		});
		b2.addActionListener(e -> dispose());

		
		ImageIcon backgroundIcon = new ImageIcon("MAIN.png"); 
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 600);
        background.setLayout(null);
		setVisible(true);
		
		 background.add(b1);
	     background.add(b2);
	     
	     b1.setBackground(new Color(102, 0, 204)); 
	     b1.setForeground(Color.WHITE);    
	     
	     b2.setBackground(new Color(255, 51, 153)); 
	     b2.setForeground(Color.WHITE);             
	     
	     setContentPane(background);
	     setVisible(true);

	}
}
public class main_gui {

	public static void main(String[] args) {
		new main();
	}

}
