package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.NoAbility;

public class Lycan extends Player{
	public Lycan(String name) {
		super(name, new NoAbility(), "werewolf");
	}

	@Override
	public void action(Player target) throws InterruptedException {
		ability.action(null);
	}

	@Override
	public void actionForNpc(int size, ArrayList<Player> players) throws InterruptedException {
		ability.action(null);
		
	}
	
}
