package fr.falkoyt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommanduhcStart implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {
		if(args.length != 0)
			return false;
		
		Bukkit.broadcastMessage(ChatColor.AQUA + ">>UHC §4 Vous avez été téléporté avec succès !!!");
		UHCGame.start();
		return true;
	}

}
