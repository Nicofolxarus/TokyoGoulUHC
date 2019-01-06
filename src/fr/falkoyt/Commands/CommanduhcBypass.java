package fr.falkoyt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.falkoyt.Main.UHCMain;
import fr.falkoyt.Teams.Role;

public class CommanduhcBypass implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cdm, String msg, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 0) {
				Bukkit.broadcastMessage("§f>> /bypass role");
			}else if (args.length == 1) {
				if(args[0].equalsIgnoreCase("role")) {
					Bukkit.broadcastMessage("§f>> §4UHC §aLes Roles ont été attribué");
					Role.RolesAtributions();
					UHCMain.timer.roleGive = true;
					Role.GiveRoleEffect();
				}
			}
		}
		return false;
	}

}
