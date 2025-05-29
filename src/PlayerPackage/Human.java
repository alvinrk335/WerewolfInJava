package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.NoAbility;

public class Human extends Player{
	public Human(String name) {
		super(name, new NoAbility(), "human");
	}
	@Override
	public void action(Player target) throws InterruptedException {
		ability.action(target);
	}
	@Override
	public void actionForNpc(int size, ArrayList<Player> players) throws InterruptedException {
		ability.action(null);
		
	}


}
