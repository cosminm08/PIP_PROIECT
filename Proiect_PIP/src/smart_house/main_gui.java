package smart_house;

import javax.swing.JButton;
import javax.swing.JFrame;

class main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Smart House");
		setSize(800, 600);
		
		setLayout(null);
		JButton b1 = new JButton("Next");
		JButton b2 = new JButton("Close");
		b1.setBounds(630, 500, 100, 40); // Buton "Close"
		b2.setBounds(50, 500, 100, 40); // Buton "Next"
		
		b1.addActionListener(e -> new control());
		b2.addActionListener(e -> dispose());

		add(b1);
		add(b2);
		
		setVisible(true);
	}
}
public class main_gui {

	public static void main(String[] args) {
		new main();
	}

}
