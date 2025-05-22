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

class gz extends JFrame {
    private static final long serialVersionUID = 1L;
    private boolean isOn = false;
    private JTextArea area;

    gz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Senzor de gaz MQ-3");
        setSize(800, 600);
        setLayout(null);

        JButton b1 = new JButton("Close");
        b1.setBounds(700, 15, 80, 20);
        b1.addActionListener(e -> dispose());

        JButton b2 = new JButton("Prev.");
        b2.setBounds(620, 15, 80, 20);
        b2.addActionListener(e -> {
            dispose();
            new control(); // Asigură-te că ai clasa `control` în același package
        });

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

    private void initMQ3Sensor() {
        Context pi4j = Pi4J.newAutoContext();

        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("mq3-sensor")
                .name("MQ-3 Gaz Sensor")
                .address(27) // <- ajustează pinul GPIO dacă e altul
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .provider("linuxfs-digital-input")
                .build();

        DigitalInput gazSensor = pi4j.create(config);
        gazSensor.addListener((DigitalStateChangeListener) event -> {
            if (event.state() == DigitalState.HIGH && isOn) {
                logToArea("Gaz detectat (alcool sau alt compus)");
            }
        });

        logToArea("Senzor MQ-3 initializat. Asteptare detectie gaz...");
    }

    private void logToArea(String message) {
        SwingUtilities.invokeLater(() -> area.append(message + "\n"));
    }
}

public class Gaz {
    public static void main(String[] args) {
        new gz();
    }
}
