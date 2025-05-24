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
		
		JButton b1 = new JButton("Close");
		b1.setBounds(50, 500, 100, 40);
		b1.addActionListener(e->dispose());
		
		JButton b2 = new JButton("Proximitate");
		b2.setBounds(650, 23, 100, 40);
		
		
		JButton b3 = new JButton("Gaz");
		b3.setBounds(650, 100, 100, 40);
		
		JButton b4 = new JButton("");
		b4.setBounds(650, 177, 100, 40);
		
		JButton b5 = new JButton("");
		b5.setBounds(650, 254, 100, 40);
				
		
		add(area);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		setVisible(true);
	}
}

public class control_gui {

	public static void main(String[] args) {
		new control();
	}

}
