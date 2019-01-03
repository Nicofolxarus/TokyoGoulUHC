package fr.falkoyt.Event;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.falkoyt.Main.UHCEndGame;
import fr.falkoyt.Main.UHCMain;
import fr.falkoyt.Timer.UHCState;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class UHCPvP implements Listener {

	public static HashMap<Player, Integer> kills = new HashMap<>();

	@EventHandler
	public void fakeDamageDeath(EntityDamageEvent e) {
		if (UHCState.isState(UHCState.WAIT)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		e.setDeathMessage("§7>> §bUHC§4 " + p.getName() + " est éliminé !");
		fakeDeath(p);
		UHCEndGame.checkWin();
	}

	private void fakeDeath(Player p) {
		respawnInstant(p);
		p.setGameMode(GameMode.SPECTATOR);
		UHCMain.playerInGame.remove(p.getUniqueId());
		p.getWorld().playSound(p.getLocation(), Sound.WITHER_SPAWN, 1F, 1F);
		UHCSkullRegen.dropSkull(p);
		p.getFlySpeed();
		p.getOpenInventory();

	}

	private void respawnInstant(final Player player) {
		Bukkit.getScheduler().runTaskLater(UHCMain.pl, new Runnable() {
			@Override
			public void run() {
				PacketPlayInClientCommand paquet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
				((CraftPlayer) player).getHandle().playerConnection.a(paquet);
			}
		}, 5);
	}

}