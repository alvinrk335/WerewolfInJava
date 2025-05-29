package InterfacePackage;

import java.util.ArrayList;
import java.util.Random;

import PlayerPackage.Player;

public class RandomTarget {
	Random rand = new Random();
	public Player generateRandomTarget(int size, ArrayList<Player>players) {
		int randoms = 0;
		do {
			randoms = rand.nextInt(size);
		}while(players.get(randoms).isAlive() == false);

		return players.get(randoms);
	}
}
