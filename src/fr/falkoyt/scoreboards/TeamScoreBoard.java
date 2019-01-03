package fr.falkoyt.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class TeamScoreBoard {

	public void refresh(Player p) {
		Scoreboard board = p.getScoreboard();
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl != null) {
				Team t = pl.getScoreboard().getPlayerTeam(pl);
				if (t != null) {
					if (board.getTeam(t.getName()) == null) {
						Team team = board.registerNewTeam(t.getName());
						team.setNameTagVisibility(NameTagVisibility.ALWAYS);
						String Prefix = t.getPrefix();
						String Suffix = "§r ";
						team.setPrefix(Prefix);
						team.setSuffix(Suffix);
						team.addPlayer(pl);
					} else {
						Team team = board.getTeam(t.getName());
						team.addPlayer(pl);
					}
				}
			}
		}
	}

	public void register(Player p, String Name, String Prefix, String Suffix) {
		Scoreboard board1 = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team1 = board1.getTeam(Name);
		if (team1 == null) {
			team1 = board1.registerNewTeam(Name);
			team1.setNameTagVisibility(NameTagVisibility.ALWAYS);
			team1.setPrefix(Prefix);
			team1.setSuffix(Suffix);
		}
		Scoreboard board = p.getScoreboard();
		if (Name != null) {
			if (board.getTeam(Name) == null) {
				Team team = board.registerNewTeam(Name);
				team.setNameTagVisibility(NameTagVisibility.ALWAYS);
				team.setPrefix(Prefix);
				team.setSuffix(Suffix);
			}
		}
	}

	public void join(Player pl, String Name) {
		if (Name != null) {
			Scoreboard board1 = Bukkit.getScoreboardManager().getMainScoreboard();
			Team team1 = board1.getTeam(Name);
			if (team1 != null) {
				team1.addPlayer(pl);
			}
			for (Player p : Bukkit.getOnlinePlayers()) {
				Scoreboard board = p.getScoreboard();
				Team team = board.getTeam(Name);
				if (team != null) {
					team.addPlayer(pl);
				} else {
					Team t = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(pl);
					if (t != null) {
						register(p, t.getName(), t.getPrefix(), t.getSuffix());
						Team team2 = board.getTeam(Name);
						if (team2 != null) {
							team2.addPlayer(pl);
						}
					}
				}
			}
		}
	}

	public void leave(Player pl, String Name) {
		Scoreboard board1 = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team1 = board1.getTeam(Name);
		if (team1.getPlayers().contains(pl)) {
			team1.removePlayer(pl);
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();
			Team team = board.getTeam(Name);
			if (team != null) {
				if (team.getPlayers().contains(pl)) {
					team.removePlayer(pl);
				}
			}
		}
	}

	public void register(String Name, String Prefix, String Suffix) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();
			if (board.getTeam(Name) == null) {
				Team team = board.registerNewTeam(Name);
				team.setNameTagVisibility(NameTagVisibility.ALWAYS);
				team.setPrefix(Prefix);
				team.setSuffix(Suffix);
			}
		}
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		if (board.getTeam(Name) == null) {
			Team team = board.registerNewTeam(Name);
			team.setNameTagVisibility(NameTagVisibility.ALWAYS);
			team.setPrefix(Prefix);
			team.setSuffix(Suffix);
		}
	}

	public void unregister(String Name) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();
			Team team = board.getTeam(Name);
			if (team != null) {
				team.unregister();
			}
		}
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = board.getTeam(Name);
		if (team != null) {
			team.unregister();
		}
	}

	public void clear(String Name) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();
			Team team = board.getTeam(Name);
			if (team != null) {
				for (String pl : team.getEntries()) {
					Player w = Bukkit.getPlayer(pl);
					team.removePlayer(w);
				}
			}
		}
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = board.getTeam(Name);
		if (team != null) {
			for (String pl : team.getEntries()) {
				Player w = Bukkit.getPlayer(pl);
				team.removePlayer(w);
			}
		}
	}

	public void unregisterall() {
		for (Team d : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
			d.unregister();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (Team u : p.getScoreboard().getTeams()) {
				u.unregister();
			}
		}
	}

	public void setPrefix(String Prefix, String Name) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = p.getScoreboard();
			Team team = board.getTeam(Name);
			if (team != null) {
				team.setPrefix(Prefix);
			}
		}
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = board.getTeam(Name);
		if (team != null) {
			team.setPrefix(Prefix);
		}
	}
}
