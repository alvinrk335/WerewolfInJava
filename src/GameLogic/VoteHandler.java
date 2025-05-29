package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import PlayerPackage.Player;

public class VoteHandler {
	private Random rand = new Random();
	private HashMap<Player, Integer> voteMap = new HashMap<Player, Integer>();
	private ArrayList<Player>players;
	public void vote(Player player) {
		if (voteMap.containsKey(player)) {
			voteMap.put(player, voteMap.get(player) + 1);
		}
		else {
			voteMap.put(player, 1);
		}

	}
	public void generateVote() {
		for(Player p : players) {
			int randoms;
			Player randomPlayer;
			if(!(p.isAlive())) {
				continue;
			}
			else {
				while(true) {
					randoms = rand.nextInt(players.size()-1);
					randomPlayer = players.get(randoms);
					if(randomPlayer.isAlive()) {
						break;
					}
				}
			}
			vote(randomPlayer);
		}
	}
	
	public HashMap<Player, Integer> getVotemap(){
		return this.voteMap;
	}
	
	public void setPlayes(ArrayList<Player>players) {
		this.players = players;
	}
}
