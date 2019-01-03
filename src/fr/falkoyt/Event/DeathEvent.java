package fr.falkoyt.Event;

import java.util.HashMap;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class DeathEvent implements Listener {

	HashMap<Player, Integer> kills = new HashMap<>();

	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;

		if (!(e.getDamager() instanceof Player))
			return;

		Player killedplayer = (Player) e.getEntity();
		if (killedplayer.getHealth() - e.getDamage() > 0)
			return;

		Player playerkiller = (Player) e.getDamager();

		kills.put(playerkiller, (kills.get(playerkiller) == null ? 0 : kills.get(playerkiller)) + 1);
		
		String newligne = "§8●§4 Kills :§f " + playerkiller.getStatistic(Statistic.PLAYER_KILLS);
		
		Scoreboard board = playerkiller.getScoreboard();
		Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
		
		for (String ligne : board.getEntries()) {
			if (ligne.contains("kill")) {
				board.resetScores(ligne);
				objective.getScore(newligne).setScore(1);
			}
		}
		
	}
}