package ex;
 
import static org.junit.Assert.*;
 
import java.awt.Component;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
 
import org.junit.Test;

/**
 * Clasa de teste pentru interfata grafica a clasei
 * Verifica proprietati ale ferestrei, existenta butoanelor, zona de text si functionalitatea butoanelor\
 * 
 */
public class test_control {
	/**
	 * Testeaza daca fereastra principala are titlu si dimensiunile corecte
	 */
 
    @Test
    public void testWindowProperties() {
        JFrame frame = new ex.control();
 
        assertEquals("Smart House", frame.getTitle());
        assertEquals(800, frame.getWidth());
        assertEquals(600, frame.getHeight());
 
        frame.dispose();
    }
    
    /**
     * Verifica daca toate butoanele asteptate("Close", "Clear", "Prev", "Gaz", "Misare") exista in containerul ferestrei
     */
 
    @Test
    public void testButtonsExistUsingText() {
        JFrame frame = new ex.control();
        Component[] components = frame.getContentPane().getComponents();
 
        boolean foundClear = false;
        boolean foundClose = false;
        boolean foundPrev = false;
        boolean foundGaz = false;
        boolean foundMiscare = false;
        boolean foundProxi = false;
 
        for (Component c : components) {
            if (c instanceof JButton) {
                String text = ((JButton) c).getText();
                switch (text) {
                    case "Clear": foundClear = true; break;
                    case "Close": foundClose = true; break;
                    case "Prev": foundPrev = true; break;
                    case "Gaz": foundGaz = true; break;
                    case "Miscare": foundMiscare = true; break;
                    case "Proxi": foundProxi = true; break;
                }
            }
        }
 
        assertTrue("Butonul Clear nu a fost gasit", foundClear);
        assertTrue("Butonul Close nu a fost gasit", foundClose);
        assertTrue("Butonul Prev nu a fost gasit", foundPrev);
        assertTrue("Butonul Gaz nu a fost gasit", foundGaz);
        assertTrue("Butonul Miscare nu a fost gasit", foundMiscare);
        assertTrue("Butonul Proxi nu a fost gasit", foundProxi);
 
        frame.dispose();
    }
    
    /**
     * Verifica daca exista o componenta de tip JTextArea in containerul ferestrei
     */
 
    @Test
    public void testTextAreaExists() {
        JFrame frame = new ex.control();
        Component[] components = frame.getContentPane().getComponents();
 
        boolean foundTextArea = false;
 
        for (Component c : components) {
            if (c instanceof JTextArea) {
                foundTextArea = true;
                break;
            }
        }
 
        assertTrue("Zona JTextArea nu a fost găsita", foundTextArea);
 
        frame.dispose();
    }
    
    /**
     * Verifica daca functionalitatea  butonului "Clear": introduce text in (@link JTextArea, apasa butonul "Clear"
     * apoi verifica daca zona text a fost stearsa
     * @throws Exception daca apar erori neprevazute in accesarea componentelor
     */
 
    @Test
    public void testClearButtonFunctionality() throws Exception {
        JFrame frame = new ex.control();
 
        JTextArea area = null;
        JButton clearButton = null;
 
        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JTextArea) {
                area = (JTextArea) c;
            }
            if (c instanceof JButton && "Clear".equals(((JButton) c).getText())) {
                clearButton = (JButton) c;
            }
        }
 
        assertNotNull("JTextArea nu a fost gasita", area);
        assertNotNull("Butonul Clear nu a fost gasit", clearButton);
 
        area.setText("Test text");
        assertEquals("Test text", area.getText());
 
        clearButton.doClick();
        assertEquals("Textul din JTextArea nu a fost sters", "", area.getText());
 
        frame.dispose();
    }
}