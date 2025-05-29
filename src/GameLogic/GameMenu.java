package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import PlayerPackage.Player;

public class GameMenu {
	private ArrayList<Player> players;
	static String red = "\033[31m";   // Red text
	static String green = "\033[32m"; // Green text
	static String reset = "\033[0m";  // Reset to default color
	private HashMap<Player, Integer> voteMap;

	
	public void displayPlayer() throws InterruptedException {
		int i = 1;
		for (Player player : players) {
			if(i % 2 != 0) {
				System.out.print(i + ". " + (player.isAlive() ? green : red )+ player.getName() + (player.isAlive() ? " (alive)" : "( dead)") + reset  +  "\t\t");
			}
			else {
				System.out.println(i + ". " + (player.isAlive() ? green : red )+ player.getName() + (player.isAlive() ? " (alive)" : "( dead)") + reset +"\t\t");
			}
			i++;

		}
		Thread.sleep(2000);
	}
	
	public void printMenu() {
		System.out.println("1. play");
		System.out.println("2. exit");
		System.out.print(">> ");
	}
	
	public void setPlayer (ArrayList<Player> players) {
		this.players = players;
	}
	public void setVoteMap(HashMap<Player, Integer> voteMap) {
		this.voteMap = voteMap;
	}
	
	public void displayVote() throws InterruptedException {
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
	
	public void printMessage() throws InterruptedException {
		System.out.print("you watch as the night goes by");
		for(int i=0; i<5; i++) {
			System.out.print('.');
			Thread.sleep(500);
		}
		System.out.println();
	}
	
	
}
