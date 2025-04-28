package smart_house;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class control extends JFrame{
	private static final long serialVersionUID = 1L;
	control(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Smart House");
		setSize(800, 600);
		
		setLayout(null);
		
		ImageIcon backgroundIcon = new ImageIcon("pagina2.jpg"); 
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 600);
        background.setLayout(null);
		setVisible(true);
		
		
		setContentPane(background);
	    setVisible(true);
		
		JTextArea area = new JTextArea();
		area.setBounds(40, 20, 350, 450);
		area.setEditable(false);
		area.setBackground(new Color(230, 240, 255));
		area.setForeground(new Color(0, 51, 102));
		area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		area.setBorder(BorderFactory.createLineBorder(new Color(153, 204, 255), 2));
		
		
		JButton b1 = new JButton("Clear");
		b1.setBounds(200, 500, 100, 40);
		b1.addActionListener(e -> area.setText(""));
		b1.setBackground(new Color(10, 132, 255));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setFocusPainted(false);
        
		JButton b2 = new JButton("Proxi");
		b2.setBounds(650, 23, 100, 40);
		b2.addActionListener(e->{
			dispose();
			new proxi();
		});
		b2.setBackground(new Color(10, 132, 255));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setFocusPainted(false);
		
		
		JButton b3 = new JButton("Gaz");
		b3.setBounds(650, 100, 100, 40);
		b3.addActionListener(e -> {
			dispose();
			new gz();
		});
		b3.setBackground(new Color(10, 132, 255));
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b3.setFocusPainted(false);
		
		JButton b4 = new JButton("Miscare");
		b4.setBounds(650, 177, 100, 40);
		b4.addActionListener(e -> {
			dispose();
			new msc();
		});
		b4.setBackground(new Color(10, 132, 255));
        b4.setForeground(Color.WHITE);
        b4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b4.setFocusPainted(false);
		
		JButton b5 = new JButton("");
		b5.setBounds(650, 254, 100, 40);
		b5.setBackground(new Color(10, 132, 255));
        b5.setForeground(Color.WHITE);
        b5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b5.setFocusPainted(false);
		
		
		JButton b6 = new JButton("Close");
		b6.setBounds(650, 500, 100, 40);
		b6.addActionListener(e->dispose());
		b6.setBackground(new Color(10, 132, 255));
        b6.setForeground(Color.WHITE);
        b6.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b6.setFocusPainted(false);
		
        JButton b7 = new JButton("Prev");
		b7.setBounds(50, 500, 100, 40);
		b7.addActionListener(e -> {
		    dispose();         
		    new main();        
		});
		b7.setBackground(new Color(10, 132, 255));
        b7.setForeground(Color.WHITE);
        b7.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b7.setFocusPainted(false);		
		
		
		background.add(area);
		background.add(b1);
		background.add(b2);
		background.add(b3);
		background.add(b4);
		background.add(b5);
		background.add(b6);
		background.add(b7);
		
		setContentPane(background);
		setVisible(true);
	}
}

public class control_gui {

	public static void main(String[] args) {
		new control();
	}

}
