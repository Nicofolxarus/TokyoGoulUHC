package fr.falkoyt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commanduhcrevive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {
		
		if (args.length != 1)
			return false;

		Player p = Bukkit.getPlayer(args[0]);

		if (p == null)
			return false;

		Bukkit.broadcastMessage(ChatColor.GOLD + ">>UHC §4 " + p.getDisplayName() + " §3 A été REVIVE");
		new WorldSounds(((Player) p).getLocation()).playSound(Sound.AMBIENCE_THUNDER);
		UHCrevive.revive(p);
		return true;
	}

}
