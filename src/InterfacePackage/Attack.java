package InterfacePackage;

import PlayerPackage.Player;

public class Attack implements IAbilities{

	@Override
	public void action(Player target) throws InterruptedException {
		if(target.isShielded()) {
			System.out.println("werewolf attacked "+ target.getName() + ". Target is shielded, no harm done");
			target.setShield(false);
		}
		else {
			System.out.println("werewolf killed "+ target.getName());
			target.setAlive(false);
		}
		
	}
}
