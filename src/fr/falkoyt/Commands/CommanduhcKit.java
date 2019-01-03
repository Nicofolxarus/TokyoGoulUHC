package fr.falkoyt.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.falkoyt.Main.UHCMain;

public class CommanduhcKit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {

		if (sender instanceof Player) {
			if (args.length != 0) {
				sender.sendMessage("What are you doing ??");
				return false;
			}

			if (UHCMain.roles.isEmpty()) {
				sender.sendMessage("You can't get your kit now");
				return false;
			}

			Player p = (Player) sender;

			p.getWorld().playSound(p.getLocation(), Sound.ANVIL_USE, 1F, 1F);

			if (UHCMain.roles.get(p).giveItem(p)) {
				sender.sendMessage(ChatColor.GOLD + ">>UHC §4 Vous avez recu votre KIT avec succés !!!");
			} else {
				sender.sendMessage(ChatColor.GOLD + ">>UHC §4 Vous avez déjà recu votre KIT !!!");
			}
		}
		return true;
	}
}
