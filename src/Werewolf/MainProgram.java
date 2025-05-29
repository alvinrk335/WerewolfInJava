package Werewolf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import PlayerPackage.*;

public class MainProgram {
	static ArrayList<Player>players = new ArrayList<Player>();
	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	static int werewolf_count = 0;
	static int lycan_count = 0;
	static int guardian_count = 0;
	static int seer_count = 0;
	static String roles[] = {"lycan", "werewolf", "human", "guardian", "seer"};
	static String red = "\033[31m";   // Red text
	static String green = "\033[32m"; // Green text
	static String reset = "\033[0m";  // Reset to default color
	static HashMap<Player, Integer> voteMap = new HashMap<Player, Integer>();
	static int playerCount = 0;

	public static void printMenu() {
		System.out.println("1. play");
		System.out.println("2. exit");
		System.out.print(">> ");
	}
	public static String randomRole() {
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

	public static Player createPlayer(String role, String name) {
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
	public static void assignRole(String nameS) {
		String name;
		for(int i=1; i<=12; i++) {
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

	public static void displayPlayer() throws InterruptedException {
		int i = 1;
		for (Player player : players) {
			if(i % 2 != 0) {
				System.out.print(i + ". " + (player.isAlive() ? green : red )+ player.getName() + (player.isAlive() ? " (alive)" : "( dead)") + reset + "\t\t");
			}
			else {
				System.out.println(i + ". " + (player.isAlive() ? green : red )+ player.getName() + (player.isAlive() ? " (alive)" : "( dead)") + reset + "\t\t");
			}
			i++;

		}
		Thread.sleep(2000);
	}
	public static Player searchByName(String name) {
		for(Player p : players) {
			if(name.equals(p.getName().toLowerCase())) {
				return p;
			}
		}
		return null;
	}

	public static void vote(Player player) {
		if (voteMap.containsKey(player)) {
			voteMap.put(player, voteMap.get(player) + 1);
		}
		else {
			voteMap.put(player, 1);
		}

	}

	public static void generateVote(int size) {
		for(int i=1; i<=size; i++) {
			int randoms;
			Player randomPlayer;
			do {
				randoms = rand.nextInt(size);
				randomPlayer = players.get(randoms);
			}while(randomPlayer.isAlive() == false);
			vote(randomPlayer);

		}
	}

	public static Player getMax() {
		int max = 0;
		Player maxVote = null;
		for(Entry<Player, Integer> entry : voteMap.entrySet()){
			if (entry.getValue() > max) {
				max = entry.getValue();
				maxVote = entry.getKey();
			}
		}
		return maxVote;

	}
	public static void displayVote() throws InterruptedException {
		int i = 1;
		for(Entry<Player, Integer> entry : voteMap.entrySet()) {
			if(i % 2 != 0) {
				System.out.print(i + ". " + (entry.getKey().isAlive() ? green : red )+ entry.getKey().getName() + (entry.getKey().isAlive() ? " (alive)" : "( dead)") + reset +": " + entry.getValue() + "vote(s)"+ "\t\t");
			}
			else {
				System.out.println(i + ". " + (entry.getKey().isAlive() ? green : red )+ entry.getKey().getName() + (entry.getKey().isAlive() ? " (alive)" : "( dead)") + reset + ": " + entry.getValue() + "vote(s)"+ "\t\t");
			}
			i++;

		}
		Thread.sleep(2000);

	}
	public static int searchWerewolf() {
		int count = 0;
		for(Player p : players) {
			if (p instanceof Werewolf) {
				count++;
			}
		}
		return count;
	}
	public static void printMessage() throws InterruptedException {
		System.out.print("you watch as the night goes by");
		for(int i=0; i<5; i++) {
			System.out.print('.');
			Thread.sleep(500);
		}
		System.out.println();
	}
	public static void main(String[] args) throws InterruptedException {
		boolean end = false;

		do {
			printMenu();
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1: 

				System.out.println("input your name: ");
				String myName = sc.nextLine();
				assignRole(myName);//important
				playerCount = players.size();
				while(true) {
					System.out.println("players : " + playerCount);
					if(searchWerewolf() <= 0) {
						System.out.println("villager wins");
						break;
					}
					else if(playerCount < 7 && searchWerewolf() > 0) {
						System.out.println("werewolf wins");
						break;
					}

					displayPlayer();

					Player myPlayer = players.get(0);
					Player targetPlayer;


					System.out.println("night has arrived....");
					Thread.sleep(2000);

					System.out.println("you are a " + myPlayer.getClass().getSimpleName());
					Thread.sleep(2000);

					if(myPlayer.getClass().getSimpleName().toLowerCase().equals("human") || myPlayer.getClass().getSimpleName().toLowerCase().equals("lycan")) {
						if (!myPlayer.isAlive()) {
							System.out.println("you are dead");
						}
						else {
							myPlayer.action(myPlayer);
						}

					}
					else {
						if (!myPlayer.isAlive()) {
							System.out.println("you are dead");
						}
						else {
							System.out.println("choose a target : ");
							String target = sc.nextLine();
							targetPlayer = searchByName(target);
							myPlayer.action(targetPlayer);
						}

					}

					printMessage();
					
					for(Player p : players) {
						if(p == myPlayer) {
							continue;
						}
						else {
							if(p.getAbilityName().toLowerCase().equals("noability")) {
								continue;
							}
							else {
								p.actionForNpc(playerCount, players);
							}
						}
					}



					System.out.println("the sun rises");
					Thread.sleep(2000);
					
					
					Thread.sleep(2000);
					displayPlayer();
					Player votedPlayer = null;
					System.out.println("voting start ");

					if (myPlayer.isAlive()) {
						do {
							System.out.print("pick player to vote: ");
							String myVote = sc.nextLine();

							votedPlayer = searchByName(myVote);
						}while(votedPlayer.isAlive() == false);
					}

					if (myPlayer.isAlive()) {
						vote(votedPlayer);
					}
					generateVote(playerCount);

					displayVote();

					Player currDead = getMax();
					currDead.setAlive(false);
					System.out.println("\n"+ currDead.getName() + " is dead " );
					Thread.sleep(2000);
					System.out.println(currDead.getName() + " is a " + currDead.getClass().getSimpleName());
					Thread.sleep(2000);
					
					int count = 0;
					for(Player p : players) {
						if(p == myPlayer) {
							continue;
						}
						if (p.isAlive() == false) {
							count++;;
						}
					}
					playerCount = players.size() - count;
				}

				break;

			case 2:
				end = true;
			}
		}while (!end);

	}
}
