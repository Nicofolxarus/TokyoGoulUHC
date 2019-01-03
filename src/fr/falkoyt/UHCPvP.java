package fr.falkoyt;

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

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class UHCPvP implements Listener {

	public static HashMap<Player, Integer> kills = new HashMap<>();

	@EventHandler
	public void fakeDamageDeath(EntityDamageEvent e) {

		if (UHCState.isState(UHCState.WAIT) || UHCState.isState(UHCState.WAIT)) {
			e.setCancelled(true);
		}

	}

	private void fakeDeath(Player p) {

		respawnInstant(p);
		p.setGameMode(GameMode.SPECTATOR);
		Bukkit.broadcastMessage("§7>> §bUHC§4 " + p.getName() + " est éliminé !");
		UHCMain.playerInGame.remove(p.getUniqueId());
		new WorldSounds(p.getLocation()).playSound(Sound.WITHER_SPAWN);
		UHCSkullRegen.dropSkull(p);
		p.getFlySpeed();
		p.getOpenInventory();

	}

	@EventHandler
	public void fakeDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		fakeDeath(p);
		UHCEndGame.checkWin();
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