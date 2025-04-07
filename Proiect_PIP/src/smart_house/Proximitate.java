package smart_house;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class proxi extends JFrame{
	private static final long serialVersionUID = 1L;
	proxi(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Senzor ultrasonic");
		setSize(800, 600);
		
		setLayout(null);
		
		JButton b1 = new JButton("Close");
		b1.setBounds(700, 15, 80, 20);
		b1.addActionListener(e->dispose());
		
		JButton b2 = new JButton("Prev.");
		b2.setBounds(620, 15, 80, 20);
		b2.addActionListener(e->{
			dispose();
			new control();
		});
		
		JButton b3 = new JButton("Act./Dez.");
		b3.setBounds(15, 15, 80, 20);
		
		JTextArea area = new JTextArea();
		area.setBounds(30, 60, 720, 480);
		
		add(b1);
		add(b2);
		add(b3);
		add(area);
		
		setVisible(true);
	}
}

public class Proximitate {

	public static void main(String[] args) {
		new proxi();
	}

}
