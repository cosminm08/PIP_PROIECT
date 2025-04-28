package smart_house;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class gz extends JFrame{
	private static final long serialVersionUID = 1L;
	gz(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Senzor de gaz");
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
		
		JButton b3 = new JButton("OFF");
		b3.setBounds(15, 15, 80, 20);
		b3.setBackground(Color.RED);
	    b3.setForeground(Color.WHITE);
	    b3.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    b3.setFocusPainted(false);
	    
	    
		
	    b3.addActionListener(new ActionListener() {
            private boolean isOn = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isOn = !isOn;
                if (isOn) {
                    b3.setText("ON");
                    b3.setBackground(new Color(0, 180, 0));
                } else {
                    b3.setText("OFF");
                    b3.setBackground(Color.RED);
                }
            }
        });
		JTextArea area = new JTextArea();
		area.setBounds(30, 60, 720, 480);
		
		add(b1);
		add(b2);
		add(b3);
		add(area);
		
		setVisible(true);
	}
}

public class Gaz {

	public static void main(String[] args) {
		new gz();
	}

}
