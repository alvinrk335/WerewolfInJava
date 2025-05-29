package InterfacePackage;

import PlayerPackage.Player;

public class Sight implements IAbilities{
	public void action(Player target) throws InterruptedException{
		System.out.println("role for player: " + target.getName() +" is " + target.getRole());
		Thread.sleep(2000);
	}
}
