package ex;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;
import com.pi4j.io.gpio.digital.PullResistance;

/**
 * Clasa GUI pentru controlul si monitorizarea senzorului de gaz MQ-3
 * Afiseaza o interfata grafica cu butoane si un jurnal de evenimente,
 * conectata la Raspberry Pi prin biblioteca Pi4J
 */

class gz extends JFrame {
    private static final long serialVersionUID = 1L;
    /**
     * Starea curenta a senzorului: activat sau dezactivat*/
    private boolean isOn = false;
    /** Zona de text pentru afisarea logului */
    private JTextArea area;
    /** Context PI4J necesar pentru controlul GPIO*/
    private Context pi4j;
    /**
     * Obiectul care reprezinta senzorul de gaz MQ-3;
     */
    private DigitalInput gazSensor;
    /**
     * Constructorul principal care initializeaza interfata grafica
     */
    gz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Senzor de gaz MQ-3");
        setSize(800, 600);
        setLayout(null);
        
        /**
         * Buton inchidere fereastra
         */
        JButton b1 = new JButton("Close");
        b1.setBounds(700, 15, 80, 20);
        b1.addActionListener(e -> dispose());
        
        /**
         * Buton de revenire la fereastra de control
         */
        JButton b2 = new JButton("Prev.");
        b2.setBounds(620, 15, 80, 20);
        b2.addActionListener(e -> {
            dispose();
            new control(); 
        });
        
        /**
         * Buton de control digital al senzorului
         */
        JButton b3 = new JButton("OFF");
        b3.setBounds(15, 15, 80, 20);
        b3.setBackground(Color.RED);
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b3.setFocusPainted(false);
        
        
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOn = !isOn;
                if (isOn) {
                    b3.setText("ON");
                    b3.setBackground(new Color(0, 180, 0));
                    logToArea("Senzor activat");
                    new Thread(()->initMQ3Sensor()).start();
                } else {
                    b3.setText("OFF");
                    b3.setBackground(Color.RED);
                    logToArea("Senzor dezactivat");
                    shutdownSensor();
                }
            }
        });
        
        /**
         * Zona de log pentru afisarea mesajelor
         */
        area = new JTextArea();
        area.setBounds(30, 60, 720, 480);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30, 60, 720, 480);

        add(b1);
        add(b2);
        add(b3);
        add(scroll);

        setVisible(true);

        new Thread(this::initMQ3Sensor).start();
    }
    
    /**
     * Initializeaza senzorul de gaz MQ-3 si configureaza listenerul pentru detectia de gaz
     * Se foloseste PI4J pentru a configura pinul GPIO
     */
    private void initMQ3Sensor() {
        Context pi4j = Pi4J.newAutoContext();

        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("mq3-sensor")
                .name("MQ-3 Gaz Sensor")
                .address(27) //Pinul GPIO27 conectat la iesirea D0 a senzorului
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .provider("linuxfs-digital-input")
                .build();

        gazSensor = pi4j.create(config);
        gazSensor.addListener((DigitalStateChangeListener) event -> {
            if (event.state() == DigitalState.HIGH && isOn) {
                logToArea("Gaz detectat (alcool sau alt compus)");
            }
        });

        logToArea("Senzor MQ-3 initializat. Asteptare detectie gaz...");
    }
    /**
     * Dezactiveaza senzorul si elibereaza resursele PI4J
     * Este apelata cand utilizatorul opreste senzorul 
     */
    private void shutdownSensor() {
    	try {
    		if(pi4j != null) {
    			pi4j.shutdown();
    			pi4j = null;
    			gazSensor = null;
    			logToArea("Senzor oprit si resurse eliberate");
    		}
    	}catch(Exception ex) {
    		logToArea("Eroare la oprirea senzorului!!!" + ex.getMessage());
    	}
    }
    /**
     * Afiseaza un mesaj in zona de log a aplicatiei
     * @param message Textul care va fi afisat
     */
    void logToArea(String message) {
        SwingUtilities.invokeLater(() -> area.append(message + "\n"));
    }
    
    public JTextArea getTextArea() {
    	return area;
    }
}

public class Gaz {
    public static void main(String[] args) {
        new gz();
    }
}