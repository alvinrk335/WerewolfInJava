package Werewolf;

import GameLogic.Game;

public class MainProgram {
	static int numOfPlayer = 12;
	static String roles[] = {"lycan", "werewolf", "human", "guardian", "seer"};

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game(numOfPlayer, roles);
		game.play();
	}
		
}
