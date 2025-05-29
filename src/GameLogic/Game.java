package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import PlayerPackage.Player;
import PlayerPackage.Werewolf;

public class Game {
	private int numOfPlayers;
	private String roles[];
	private PlayerHandler playerHandler; 
	private VoteHandler voteHandler = new VoteHandler();
	private GameMenu menu = new GameMenu();
	private int playerCount = 0;
	private Scanner sc = new Scanner(System.in);
	
	
	public Game(int numOfPlayers, String roles[]) {
		this.numOfPlayers = numOfPlayers;
		this.roles = roles;
		this.playerHandler = new PlayerHandler(roles, numOfPlayers);
	}
	
	
	public Player getMax() {
		int max = 0;
		HashMap<Player, Integer> voteMap = getVoteMap();
		Player maxVote = null;
		for(Entry<Player, Integer> entry : voteMap.entrySet()){
			if (entry.getValue() > max) {
				max = entry.getValue();
				maxVote = entry.getKey();
			}
		}
		return maxVote;

	}
	
	public void resetShield() {
		ArrayList<Player> players = getPlayers();
		for(Player p : players) {
			if(p.isShielded()) {
				p.setShield(false);
			}
		}
	}
	
	private HashMap<Player, Integer> getVoteMap(){
		return voteHandler.getVotemap();
	}
	
	private ArrayList<Player> getPlayers(){
		return playerHandler.getPlayers();
	}
	
	public void getWerewolf() {
		ArrayList<Player> players = getPlayers();
		System.out.println("Werewolf : ");
		for(Player p : players) {
			if(p instanceof Werewolf) {
				System.out.println(p.getName());
			}
		}
		System.out.println();
	}
	
	public int searchWerewolf() {
		ArrayList<Player> players = getPlayers();
		int count = 0;
		for(Player p : players) {
			if (p instanceof Werewolf && p.isAlive()) {
				count++;
			}
		}
		return count;
	}
	
	private void refreshMenu() {
		menu.setPlayer(getPlayers());
		menu.setVoteMap(getVoteMap());
	}
	
	private void refreshGame() {
		refreshMenu();
		voteHandler.setPlayes(getPlayers());
		
	}
	
	
	public void play() throws InterruptedException {
		boolean end = false;

		do {
			refreshGame();
			menu.printMenu();
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1: 

				System.out.println("input your name: ");
				String myName = sc.nextLine();
				
				
				playerHandler.assignRole(myName);//important
				playerCount = getPlayers().size();
				while(true) {

					System.out.println("players : " + playerCount);
					menu.displayPlayer();

					Player myPlayer = getPlayers().get(0);
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
							do {
								System.out.println("choose a target : ");
								String target = sc.nextLine();
								targetPlayer = playerHandler.searchByName(target);
							}while(targetPlayer == null);
							
							
							myPlayer.action(targetPlayer);
						}

					}

					menu.printMessage();
					
					for(Player p : getPlayers()) {
						if(p == myPlayer) {
							continue;
						}
						else {
							if(!p.isAlive() || p.getAbilityName().toLowerCase().equals("noability")) {
								continue;
							}
							else {
								p.actionForNpc(playerCount, getPlayers());
							}
						}
					}



					System.out.println("the sun rises");
					Thread.sleep(2000);
					
					
					Thread.sleep(2000);
					menu.displayPlayer();
					Player votedPlayer = null;
					System.out.println("\n"+"voting start ");

					if (myPlayer.isAlive()) {
						do {
							System.out.print("pick player to vote: ");
							String myVote = sc.nextLine();
							
							votedPlayer = playerHandler.searchByName(myVote);
							if(votedPlayer == null) {
								continue;
							}
						}while(votedPlayer.isAlive() == false);
						voteHandler.vote(votedPlayer);
					}

					
					voteHandler.generateVote();

					menu.displayVote();

					Player currDead = getMax();
					currDead.setAlive(false);
					System.out.println("\n"+ currDead.getName() + GameMenu.red + " is dead " + GameMenu.reset );
					Thread.sleep(2000);
					System.out.println(currDead.getName() + " is a " + currDead.getClass().getSimpleName());
					Thread.sleep(2000);
					
					int count = 0;
					for(Player p : getPlayers()) {
						if (p.isAlive() == false) {
							count++;;
						}
					}
					playerCount = getPlayers().size() - count;
					if(searchWerewolf() <= 0) {
						System.out.println("VILLAGER WINS");
						getWerewolf();
						break;
					}
					else if(playerCount < 7 && searchWerewolf() > 0) {
						System.out.println("WEREWOLF WINS");
						getWerewolf();
						break;
					}
					resetShield();
					getVoteMap().clear();
				}

				break;

			case 2:
				end = true;
			}
		}while (!end);

	}
	
	
}
