package fr.falkoyt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.falkoyt.Utils.UHCrevive;

public class Commanduhcrevive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {
		
		if (args.length != 1)
			return false;

		Player p = Bukkit.getPlayer(args[0]);

		if (p == null)
			return false;

		Bukkit.broadcastMessage(ChatColor.GOLD + ">>UHC §4 " + p.getDisplayName() + " §3 A été REVIVE");
		p.getWorld().playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1F, 1F);
		UHCrevive.revive(p);
		return true;
	}

}
