package fr.falkoyt.Utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.falkoyt.Main.UHCMain;
import fr.falkoyt.Timer.UHCState;

public class UHCrevive {

	public static void revive(Player p) {
		if (UHCState.GAME != null) {
			p.getPlayer().setGameMode(GameMode.SURVIVAL);
			p.sendMessage("Vous avez été Revive bonne partie.");
			p.getPlayer().updateInventory();
			p.getPlayer().setMaxHealth(20);
			UHCMain.playerInGame.add(p.getUniqueId());
		}
	}
}
