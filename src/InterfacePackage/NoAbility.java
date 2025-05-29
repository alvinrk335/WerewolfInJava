package InterfacePackage;

import PlayerPackage.Player;

public class NoAbility implements IAbilities{

	@Override
	public void action(Player target) throws InterruptedException {
		System.out.println("-");
		Thread.sleep(2000);
	}
	
}
