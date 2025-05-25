package ex;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Teste unitare pentru clasa, care reprezinta interfata grafica a senzorului de gaz MQ-3 
 * Testele verifica initializarea corecta a ferestrei, logarea corecta in JTextArea si functionarea butonului ON/OFF
 */
public class test_gaz {
	
	private gz gui;
	
	/**
	 * Initializeaza o instanta a interfetei grafice inainte de fiecare test
	 */
	
	@Before
	public void setUp()
	{
		gui = new gz();
	}
	
	/**
	 * Inchide fereastra dupa fiecare test pentru a elibera resursele 
	 */
	
	@After
	public void tearDown() {
		gui.dispose();
	}
	
	/**
	 * Testeaza daca instanta GUI este creata cu succes si daca titlul ferestrei este corect
	 */
	
	@Test
	public void test() {
		assertNotNull(gui);
		assertEquals("Senzor de gaz MQ-3", gui.getTitle());
	}
	/**
	 * Testeaza daca metoda {@code logToArea(String)} adauga corect un mesaj in zona de text 
	 * @throws Exception daca metoda {@code invokeAndWait} esueaza 
	 */
	@Test
	public void testlogToArea() throws Exception{
		SwingUtilities.invokeAndWait(()->gui.logToArea("Mesaj Test"));
		String content = gui.getTextArea().getText();
		assertTrue(content.contains("Mesaj test"));
	}
	
	/**
	 * Testeaza functionalitatea butonului ON/OFF, verificand schimbarea textului
	 * intre "OFF" -> "ON" si apoi inapoi la OFF dupa doua click-uri
	 * @throws Exception daca metoda {@code invokeAndWait} esueaza
	 */
	
	@Test
	public void testButoane() throws Exception{
		//Obtine al treilea component adaugat, care este presupus a fi butonul ON/OFF
		JButton toggleButton = (JButton) gui.getContentPane().getComponent(2);
		
		//Simuleaza primul click -> textul ar trebui sa devina "ON"
		SwingUtilities.invokeAndWait(toggleButton::doClick);
		assertEquals("ON", toggleButton.getText());
		
		//Simuleaza al doilea click -> textul ar trebui sa revina la "OFF"
		SwingUtilities.invokeAndWait(toggleButton::doClick);
		assertEquals("OFF", toggleButton.getText());
	}
}
