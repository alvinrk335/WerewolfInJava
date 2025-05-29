package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.RandomTarget;
import InterfacePackage.Sight;

public class Seer extends Player{
	public Seer(String name) {
		super(name, new Sight(), "seer");
	}
	
	public void action(Player target) throws InterruptedException {
		ability.action(target);
	}
	
	public void actionForNpc(int size, ArrayList<Player>players) throws InterruptedException {
		RandomTarget random = new RandomTarget();
		Player randomPick = random.generateRandomTarget(size, players);
		System.out.println("seer sees " + randomPick.getName());
		Thread.sleep(2000);
		ability.action(randomPick);
		
	}
}
