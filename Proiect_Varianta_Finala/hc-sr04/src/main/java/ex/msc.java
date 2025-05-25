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
 * Clasa GUI pentru interfata cu senzorul de miscare PIR
 * Afiseaza un panou cu butoane si o zona de log, unde sunt raportate evenimentele detectate
 */
public class msc extends JFrame {
    private static final long serialVersionUID = 1L;
    private boolean isOn = false;
    private JTextArea area;
 
    /**
     * Constructor standard — pentru rulare normală
    */
    public msc() {
        this(false); // apeleaza constructorul testMode=false
    }
 
    /**
     * Constructor cu opțiune de test — evită lansarea UI și firelor
     * @param testMode dacă este true, nu se lansează GUI complet
     */
    public msc(boolean testMode) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Senzor de miscare");
        setSize(800, 600);
        setLayout(null);
 
        // Buton pentru inchidere fereastra
        JButton b1 = new JButton("Close");
        b1.setBounds(700, 15, 80, 20);
        b1.addActionListener(e -> dispose());
 
        // Buton pentru revenire fereastra
        JButton b2 = new JButton("Prev.");
        b2.setBounds(620, 15, 80, 20);
        b2.addActionListener(e -> {
            dispose();
            new control();
        });
 
        // Buton pentru activare/dezactivare senzori
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
                } else {
                    b3.setText("OFF");
                    b3.setBackground(Color.RED);
                    logToArea("Senzor dezactivat");
                }
            }
        });
 
        // Zona de text pentru afisare mesaje si log-uri
        area = new JTextArea();
        area.setBounds(30, 60, 720, 480);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30, 60, 720, 480);
        
        

        add(b1);
        add(b2);
        add(b3);
        add(area);
        add(scroll);
 
        if (!testMode) {
            setVisible(true);
            new Thread(this::initPIRSensor).start();
        }
    }
 
    /**
     * Initializeaza senzorul PIR folosind biblioteca PI4J
     * Asculta schimbarile de stare si raporteaza miscarile detectate
     */
    private void initPIRSensor() {
        Context pi4j = Pi4J.newAutoContext();
 
        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("pir-sensor")
                .name("PIR Sensor")
                .address(17)
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .provider("pigpio-digital-input")
                .build();
        DigitalInput pirSensor = pi4j.create(config);
        pirSensor.addListener((DigitalStateChangeListener) event -> {
            if (event.state() == DigitalState.HIGH && isOn) {
            	logToArea("Miscare detectata");
            }
        });
 
        logToArea("Senzor initializat. Asteptare miscare.....");
    }
 
    /**
     * Afiseaza un mesaj in zona de text din interfata ruland pe firul UI
     * @param message mesajul de adaugat
     */
    public void logToArea(String message) {
        SwingUtilities.invokeLater(() -> area.append(message + "\n"));
    }
 
    /**
     * Returneaza referinta la JTextArea (pentru testare)
     */
    public JTextArea getTextArea() {
        return area;
    }
    
    
}