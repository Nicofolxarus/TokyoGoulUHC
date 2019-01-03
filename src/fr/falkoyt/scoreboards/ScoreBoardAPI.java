package fr.falkoyt.scoreboards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardAPI {

	private Scoreboard scoreboard;
	private Random rand = new Random();
	private HashMap<Object, ObjectifSideBar> ObjectifsSideBar = new HashMap<>();

	public ScoreBoardAPI() {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
	}

	public ScoreBoardAPI(Player p) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		setScoreBoardPlayer(p);
	}

	public void setScoreBoardPlayer(Player p) {
		p.setScoreboard(scoreboard);
	}

	public void unsetScoreBoardPlayer(Player p) {
		new ArrayList<>(ObjectifsSideBar.keySet()).forEach(obj -> deleteObjectiveSidebar(obj));
		ObjectifsSideBar.clear();
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}

	public Objective addObjective(String Name, String type, DisplaySlot slot) {
		Objective objective = scoreboard.registerNewObjective(Name, type);
		objective.setDisplaySlot(slot);
		return objective;
	}

	public ObjectifSideBar createObjectifSidebar(String name, Object obj) {
		String objID = "sb." + rand.nextInt(999999999);
		Objective o = scoreboard.registerNewObjective(objID, "dummy");
		o.setDisplayName(name);
		ObjectifSideBar ob = new ObjectifSideBar(o);
		ObjectifsSideBar.put(obj, ob);
		return ob;
	}

	public void deleteObjectiveSidebar(Object obj) {
		ObjectifSideBar obs = ObjectifsSideBar.get(obj);
		if (obs != null) {
			Objective objective = obs.getObjective();
			if (objective != null) {
				ObjectifsSideBar.remove(obj);
				objective.unregister();
			}
		}
	}

	public ObjectifSideBar getObjectiveSidebar(Object obj) {
		return ObjectifsSideBar.get(obj);
	}

	public void deleteObjective(String Name) {
		Objective objective = scoreboard.getObjective(Name);
		if (objective != null) {
			objective.unregister();
		}
	}
	
	public void deleteObjective(DisplaySlot slot) {
		Objective objective = scoreboard.getObjective(slot);
		if (objective != null)
			objective.unregister();
	}

	public static void updateObjectifHealth() {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl.getHealth() < 20) {
				pl.setHealth(pl.getHealth() + 0.000001);
			} else {
				if (!pl.hasPotionEffect(PotionEffectType.ABSORPTION)) {
					pl.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2, 0, true, false));
				}
			}
		}
	}

	public class ObjectifSideBar {

		private Objective objective;
		private ArrayList<String> scoreboard = new ArrayList<>();

		public ObjectifSideBar(Objective objective) {
			this.objective = objective;
		}

		public void addLine(int nb, String ligne) {
			objective.getScore(ligne).setScore(nb);
			scoreboard.add(ligne);
		}

		public void setLine(int nb, String ligne) {
			objective.getScore(ligne).setScore(-1);
			removeLine(nb);
			objective.getScore(ligne).setScore(nb);
			scoreboard.add(ligne);
		}

		public void removeLine(int nb) {
			for (String lignes : new ArrayList<>(scoreboard)) {
				if (objective.getScore(lignes).getScore() == nb) {
					scoreboard.remove(lignes);
					objective.getScoreboard().resetScores(lignes);
					break;
				}
			}
		}

		public void removeLineEqual(String ligne) {
			for (String lignes : new ArrayList<>(scoreboard)) {
				if (lignes.equalsIgnoreCase(ligne)) {
					scoreboard.remove(lignes);
					objective.getScoreboard().resetScores(lignes);
					break;
				}
			}
		}

		public void removeLineContain(String ligne) {
			for (String lignes : new ArrayList<>(scoreboard)) {
				if (lignes.contains(ligne)) {
					scoreboard.remove(lignes);
					objective.getScoreboard().resetScores(lignes);
				}
			}
		}

		public void setName(String name) {
			objective.setDisplayName(name);
		}

		public void display() {
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		}

		public Objective getObjective() {
			return objective;
		}

	}
}
