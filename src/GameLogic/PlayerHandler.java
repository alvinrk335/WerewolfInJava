package GameLogic;

import java.util.ArrayList;
import java.util.Random;

import PlayerPackage.Guardian;
import PlayerPackage.Human;
import PlayerPackage.Lycan;
import PlayerPackage.Player;
import PlayerPackage.Seer;
import PlayerPackage.Werewolf;

public class PlayerHandler {
	private int werewolf_count;
	private int lycan_count;
	private int guardian_count;
	private int seer_count;
	private String roles[];
	private Random rand = new Random();
	private int numOfPlayers;
	private ArrayList<Player>players = new ArrayList<Player>();
	
	public PlayerHandler(String roles[], int numOfPlayers) {
		this.werewolf_count = 0;
		this.guardian_count = 0;
		this.lycan_count = 0;
		this.seer_count = 0;
		this.roles = roles;
		this.numOfPlayers = numOfPlayers;
	}
	
	public String randomRole() {
		String str;
		while(true) {
			int randoms = rand.nextInt(roles.length);
			str = roles[randoms];
			if((str.equals("werewolf") && werewolf_count >= 2) || (str.equals("lycan") && lycan_count >= 1) || (str.equals("guardian") && guardian_count >= 1) || (str.equals("seer") && seer_count >= 1)) {
				continue;
			}
			else {
				if (str.equals("werewolf")) {
					werewolf_count++;
				} else if (str.equals("lycan")) {
					lycan_count++;
				} else if (str.equals("guardian")) {
					guardian_count++;
				} else if (str.equals("seer")) {
					seer_count++;
				}
				break;  
			}
		}

		return str;
	}
	public void assignRole(String nameS) {
		String name;
		for(int i=1; i<=numOfPlayers; i++) {
			String role = randomRole();
			if(i <= 1) {
				name = nameS;
			}
			else {
				name = "Player " + (i);
			}

			players.add(createPlayer(role, name));

		}

	}	
	public Player createPlayer(String role, String name) {
		switch (role.toLowerCase()) {
		case "seer":
			return new Seer(name); 
		case "werewolf":
			return new Werewolf(name);  
		case "lycan":
			return new Lycan(name);  
		case "guardian":
			return new Guardian(name); 
		case "human":
			return new Human(name); 
		}
		return null;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public Player searchByName(String name) {
		for(Player p : players) {
			if(name.toLowerCase().equals(p.getName().toLowerCase())){
				return p;
			}
		}
		System.out.println("player is not found!");
		return null;
	}
}
