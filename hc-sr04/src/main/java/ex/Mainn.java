 package ex;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class Mainn {

	public static void main(String[] args) throws InterruptedException{
			Context pi4j = Pi4J.newAutoContext();
			
			var pirConfig = DigitalInput.newConfigBuilder(pi4j)
					.id("pir-sensor")
					.name("PIR Sensor")
					.address(17)
					.pull(PullResistance.PULL_DOWN)
					.debounce(3000L)
					.build();
			
			var pirSensor = pi4j.create(pirConfig);
			
			pirSensor.addListener(event -> {
				if(event.state() == DigitalState.HIGH) {
					System.out.println("Miscare detectata!!");
				}else {
					System.out.println("Miscarea a incetat!!");
				}
			});
			
			while(true) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					break;
				}
			}
			}
	}

