package fr.falkoyt;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.falkoyt.UHCJoin.ScoreboardEnum;
import fr.falkoyt.scoreboards.ScoreBoardAPI;
import fr.falkoyt.scoreboards.ScoreBoardAPI.ObjectifSideBar;

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

		kills.put(playerkiller, kills.get(playerkiller) + 1);

		ScoreBoardAPI s = UHCJoin.sclist.get(playerkiller.getUniqueId());
		ObjectifSideBar os = s.createObjectifSidebar("§4" + "TokyoGhoul", ScoreboardEnum.ScUHC);

		if (os == null)
			return;

		os.setLine(10, "§4Kill(s): " + kills.get(playerkiller));

	}
}