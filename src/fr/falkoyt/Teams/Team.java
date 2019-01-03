package fr.falkoyt.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class Team {
	
	public static Team getTeam(Player p) {
		for (Team team : TeamRegister.getregisteredTeams()) {
			if (team.getTeam().getEntries().contains(p.getName())) {
				return team;
			}
		}
		return null;
	}

	int id;
	String name;
	org.bukkit.scoreboard.Team team;

	Location loc;

	ChatColor chatcolor;

	DyeColor dyecolor;

	public Team(int id, String name, ChatColor chatcolor, DyeColor dyecolor) {
		this.id = id;
		this.name = name;
		this.chatcolor = chatcolor;
		this.dyecolor = dyecolor;
		Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
		this.team = s.registerNewTeam(name);

		this.team.setPrefix(chatcolor.toString());
		this.team.setSuffix(ChatColor.WHITE.toString());
		this.team.setDisplayName(this.team.getPrefix() + this.team.getDisplayName() + this.team.getSuffix());
	}

	public Team(int id, String name, ChatColor chatcolor, DyeColor dyecolor, String suffix) {
		this.id = id;
		this.name = name;
		this.chatcolor = chatcolor;
		this.dyecolor = dyecolor;
		Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
		this.team = s.registerNewTeam(name);

		this.team.setPrefix(chatcolor.toString());
		this.team.setSuffix(suffix + ChatColor.WHITE.toString());
		this.team.setDisplayName(this.team.getPrefix() + this.team.getDisplayName() + this.team.getSuffix());
		while (this.loc == null) {

		}
	}

	public ChatColor getChatcolor() {
		return this.chatcolor;
	}

	public DyeColor getDyecolor() {
		return this.dyecolor;
	}

	public int getId() {
		return this.id;
	}

	public Location getLoc() {
		return this.loc;
	}

	public String getName() {
		return this.name;
	}

	public org.bukkit.scoreboard.Team getTeam() {
		return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(this.name);
	}

}