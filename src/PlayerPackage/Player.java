package PlayerPackage;

import java.util.ArrayList;

import InterfacePackage.IAbilities;

public abstract class Player implements IAbilities{
	protected String name;
	protected boolean alive;
	protected IAbilities ability;
	protected boolean shield;
	protected String role;
	
	public Player(String name, IAbilities ability, String role){
		this.alive = true;
		this.name = name;
		this.ability  = ability;
		this.shield = false;
		this.role = role;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isShielded() {
		return shield;
	}
	public void setShield(boolean shield) {
		this.shield = shield;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAbilityName() {
		return this.ability.getClass().getSimpleName();
	}
	public abstract void action(Player target) throws InterruptedException; 
	public abstract void actionForNpc(int size, ArrayList<Player>players) throws InterruptedException;
}
