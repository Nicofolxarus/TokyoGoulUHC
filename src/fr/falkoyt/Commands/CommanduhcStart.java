package fr.falkoyt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.falkoyt.Main.UHCGame;

public class CommanduhcStart implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {
		if(args.length != 0)
			return false;
		
		if(sender instanceof Player)
			if(!((Player) sender).isOp())
				return false;
		
		Bukkit.broadcastMessage(ChatColor.AQUA + ">>UHC §4 Vous avez été téléporté avec succès !!!");
		UHCGame.start();
		return true;
	}

}
