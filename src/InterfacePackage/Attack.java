package InterfacePackage;

import PlayerPackage.Player;

public class Attack implements IAbilities{

	@Override
	public void action(Player target) throws InterruptedException {
		if(target.isShielded()) {
			target.setShield(false);
			System.out.println("werewolf attacked player "+ target.getName() + ". Target is shielded, no harm done");
		}
		else {
			System.out.println("werewolf killed "+ target.getName());
			target.setAlive(false);
		}
		
	}
}
