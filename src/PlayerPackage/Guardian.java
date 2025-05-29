package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.RandomTarget;
import InterfacePackage.Shield;

public class Guardian extends Player {

	public Guardian(String name) {
		super(name, new Shield(), "guardian");
	}

	@Override
	public void action(Player target) throws InterruptedException {
		ability.action(target);
		Thread.sleep(2000);
	}
	
	public void actionForNpc(int size, ArrayList<Player>players) throws InterruptedException {
		RandomTarget random = new RandomTarget();
		Player randomPick = random.generateRandomTarget(size, players);
		System.out.println("guardian protected " + randomPick.getName());
		Thread.sleep(2000);
		ability.action(randomPick);
	}
	

}
