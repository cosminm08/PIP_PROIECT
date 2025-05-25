package ex;

import static org.junit.Assert.assertTrue;

import javax.swing.SwingUtilities;

import org.junit.Test;

public class test_miscare {

	@Test
	public void test() throws Exception{
		msc frame = new msc(true);
		
		
		SwingUtilities.invokeAndWait(() -> {
			frame.logToArea("Senzor activat");
			frame.logToArea("Test Mesaj");
		});
		Thread.sleep(100);
		
		String content = frame.getTextArea().getText();
		
		assertTrue("Mesajul nu a fost adaugat in JTextArea", content.contains("Miscare detectata"));
		
		frame.dispose();
	}
	
	@Test
	public void testDetectieSenzor() throws Exception{
		msc frame = new msc(true);
		
		SwingUtilities.invokeAndWait(()->{
			frame.logToArea("Miscare detectata");
		});
		
		Thread.sleep(100);
		
		String content = frame.getTextArea().getText();
		
		assertTrue("Mesajul apare deoarece metoda a fost apelata direct dar in productie ar trebui sa fie blocat", content.contains("Miscare detetctata"));
	}

}
