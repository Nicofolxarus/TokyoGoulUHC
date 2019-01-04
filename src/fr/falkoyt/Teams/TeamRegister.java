package fr.falkoyt.Teams;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TeamRegister {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Team> teams = new ArrayList();

	public static ArrayList<Team> getregisteredTeams() {
		return teams;
	}

	public static void Initialisation() {
		for (org.bukkit.scoreboard.Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
			team.unregister();
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getScoreboard().getTeams().forEach(t -> t.unregister());
		}
		teams.clear();
		teams.add(new Team(1, "Cyan", ChatColor.DARK_AQUA, DyeColor.CYAN));
		teams.add(new Team(2, "Rose", ChatColor.LIGHT_PURPLE, DyeColor.PINK));
		teams.add(new Team(3, "Orange", ChatColor.GOLD, DyeColor.ORANGE));
		teams.add(new Team(4, "Grise", ChatColor.GRAY, DyeColor.GRAY));
		teams.add(new Team(5, "Verte", ChatColor.GREEN, DyeColor.LIME));
		teams.add(new Team(6, "Jaune", ChatColor.YELLOW, DyeColor.YELLOW));
		teams.add(new Team(7, "Vert foncé", ChatColor.DARK_GREEN, DyeColor.GREEN));
		teams.add(new Team(8, "Bleu foncé", ChatColor.DARK_BLUE, DyeColor.BLUE));
	}

	public static void registerTeam(Team t) {
		teams.add(t);
	}

	public TeamRegister() {
	}

	public Team getTeamByID(int i) {
		for (Team team : teams) {
			if (team.getId() == i) {
				return team;
			}
		}
		return null;
	}

	public Team getTeamByName(String string) {
		for (Team team : teams) {
			if (team.getName().equalsIgnoreCase(string)) {
				return team;
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Team getTeamOfPlayer(OfflinePlayer p) {
		Iterator localIterator2;
		for (Iterator localIterator1 = teams.iterator(); localIterator1.hasNext(); localIterator2.hasNext()) {
			Team team = (Team) localIterator1.next();
			localIterator2 = team.getTeam().getPlayers().iterator();
			continue;
		}
		return null;
	}

	public void unregisterTeam(Team team) {
		teams.remove(team);
		if (team.getTeam() != null) {
			team.getTeam().unregister();
		}
	}

	public void update() {
		for (org.bukkit.scoreboard.Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
			team.unregister();
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getScoreboard().getTeams().forEach(t -> t.unregister());
		}
		teams.clear();
		teams.add(new Team(1, "Cyan", ChatColor.DARK_AQUA, DyeColor.CYAN));
		teams.add(new Team(2, "Rose", ChatColor.LIGHT_PURPLE, DyeColor.PINK));
		teams.add(new Team(3, "Orange", ChatColor.GOLD, DyeColor.ORANGE));
		teams.add(new Team(4, "Grise", ChatColor.GRAY, DyeColor.GRAY));
		teams.add(new Team(5, "Verte", ChatColor.GREEN, DyeColor.GREEN));
		teams.add(new Team(6, "Jaune", ChatColor.YELLOW, DyeColor.YELLOW));
		teams.add(new Team(7, "Vert fonc�", ChatColor.DARK_GREEN, DyeColor.GREEN));
		teams.add(new Team(8, "Bleu fonc�", ChatColor.DARK_BLUE, DyeColor.BLUE));
	}

}
