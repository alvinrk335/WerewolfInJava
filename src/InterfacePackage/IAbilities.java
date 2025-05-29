package InterfacePackage;

import PlayerPackage.Player;

public interface IAbilities {
	public abstract void action(Player target) throws InterruptedException;
}
