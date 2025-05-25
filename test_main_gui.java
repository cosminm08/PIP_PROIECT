package ex;
 
import static org.junit.Assert.*;
import org.junit.Test;
 
import java.awt.Component;
import java.lang.reflect.Field;
 
import javax.swing.JButton;
import javax.swing.JFrame;
 
/**
 * Clasa de teste unitare pentru interfata grafica (@code main) din pachetul(@code ex)
 * Verifica proprietatile ferestrei si existenta butoanelor "Next" si "Close" folosind doua metode diferite
 */
public class test_main_gui {
	/**
	 * Testeaza daca fereastra principala are titlul corect si dimensiunile asteptate
	 */
 
    @Test
    public void testWindowProperties() {
        JFrame window = new ex.main();
 
        assertEquals("Smart House", window.getTitle());
        assertEquals(800, window.getWidth());
        assertEquals(600, window.getHeight());
 
        window.dispose();
    }
    /**
     * Verifica daca butoanele "Next" si "Close" exista, accesand campurile private ale clasei folosind Reflection
     * @throws Exception daca accesul la campurile declarate nu este posibil
     */
    @Test
    public void testButtonsExistUsingReflection() throws Exception {
        JFrame window = new ex.main();
 
        Field[] fields = window.getClass().getDeclaredFields();
        boolean foundNext = false;
        boolean foundClose = false;
 
        for (Field f : fields) {
            if (f.getType() == JButton.class) {
                f.setAccessible(true);					//permite accesul la campuri private 
                JButton btn = (JButton) f.get(window);
                if (btn.getText().equals("Next")) {
                    foundNext = true;
                } else if (btn.getText().equals("Close")) {
                    foundClose = true;
                }
            }
        }
 
        assertTrue("Butonul 'Next' nu a fost gasit.", foundNext);
        assertTrue("Butonul 'Close' nu a fost gasit.", foundClose);
 
        window.dispose();
    }
    
    /**
     * Verifica daca butoanele "Next" si "Close" exista in arborele de componente ale containerului principal
     */
 
    @Test
    public void testButtonsFromComponentTree() {
        JFrame window = new ex.main();
        Component[] components = window.getContentPane().getComponents();
 
        boolean foundNext = false;
        boolean foundClose = false;
 
        for (Component c : components) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                if ("Next".equals(btn.getText())) {
                    foundNext = true;
                } else if ("Close".equals(btn.getText())) {
                    foundClose = true;
                }
            }
        }
 
        assertTrue("Butonul 'Next' nu a fost gasit în container.", foundNext);
        assertTrue("Butonul 'Close' nu a fost gasit în container.", foundClose);
 
        window.dispose();
    }
}