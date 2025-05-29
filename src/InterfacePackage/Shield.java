package InterfacePackage;

import PlayerPackage.Player;

public class Shield implements IAbilities{

	@Override
	public void action(Player target) throws InterruptedException {
		target.setShield(true);
		System.out.println("Target is shielded, target cannot die in the next turn");
		Thread.sleep(2000);
	}
	
}
