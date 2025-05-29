package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.Attack;
import InterfacePackage.RandomTarget;

public class Werewolf extends Player{
	public Werewolf(String name) {
		super(name, new Attack(), "werewolf");
	}

	@Override
	public void action(Player target) throws InterruptedException {
		ability.action(target);
		Thread.sleep(2000);
	}
	public void actionForNpc(int size, ArrayList<Player>players) throws InterruptedException {
		RandomTarget random = new RandomTarget();
		Player randomPick = random.generateRandomTarget(size, players);
		ability.action(randomPick);
		Thread.sleep(2000);
	}
}
