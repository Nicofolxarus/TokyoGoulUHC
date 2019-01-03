package fr.falkoyt;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class UHCrevive {

	public static void revive(Player p) {
		if (UHCState.GAME != null) {

			p.getPlayer().setGameMode(GameMode.SURVIVAL);
			p.sendMessage("Vous avez été Revive bonne partie.");
			p.getPlayer().updateInventory();
			p.getPlayer().setMaxHealth(20);
		}
	}
}
