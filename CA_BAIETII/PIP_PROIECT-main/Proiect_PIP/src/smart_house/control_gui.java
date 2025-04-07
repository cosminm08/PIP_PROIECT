package smart_house;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class control extends JFrame{
	private static final long serialVersionUID = 1L;
	control(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Smart House");
		setSize(800, 600);
		
		setLayout(null);
		
		JTextArea area = new JTextArea();
		area.setBounds(40, 20, 350, 450);
		area.setEditable(false);
		
		JButton b1 = new JButton("Clear");
		b1.setBounds(200, 500, 100, 40);
		b1.addActionListener(e -> area.setText(""));
		
		JButton b2 = new JButton("Proximitate");
		b2.setBounds(650, 23, 100, 40);
		
		
		JButton b3 = new JButton("Gaz");
		b3.setBounds(650, 100, 100, 40);
		
		JButton b4 = new JButton("Miscare");
		b4.setBounds(650, 177, 100, 40);
		
		JButton b5 = new JButton("");
		b5.setBounds(650, 254, 100, 40);
		
		JButton b6 = new JButton("Close");
		b6.setBounds(650, 500, 100, 40);
		b6.addActionListener(e->dispose());
		
		JButton b7 = new JButton("Prev");
		b7.setBounds(50, 500, 100, 40);
		b7.addActionListener(e -> {
		    dispose();         
		    new main();        
		});
				
		
		
		add(area);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		
		setVisible(true);
	}
}

public class control_gui {

	public static void main(String[] args) {
		new control();
	}

}
