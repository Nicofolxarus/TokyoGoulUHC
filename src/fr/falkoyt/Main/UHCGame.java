package fr.falkoyt.Main;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.falkoyt.Teams.Team;
import fr.falkoyt.Teams.TeamRegister;
import fr.falkoyt.Timer.UHCState;
import fr.falkoyt.Utils.Flying_Spawn;
import fr.falkoyt.Utils.UHCTeleport;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class UHCGame implements Listener {

	public static void start() {
		// ON SOCCUPE DU LANCEMENT DU JEU

		UHCState.setState(UHCState.GAME);
		Bukkit.getWorlds().get(0).setFullTime(0);

		for (UUID uuid : UHCMain.playerInGame) {
			Player pl = Bukkit.getPlayer(uuid);
			pl.setGameMode(GameMode.ADVENTURE);
			pl.getInventory().setArmorContents(null);
			pl.getInventory().clear();
			pl.setLevel(0);
			pl.setExp(0);
			pl.setFireTicks(0);
			pl.setMaxHealth(20);
			pl.setFoodLevel(20);
			pl.setHealth(20);
			pl.getHealth();
			EntityPlayer el = ((CraftPlayer) pl).getHandle();
			el.setAbsorptionHearts(0);
			pl.getActivePotionEffects().forEach(eff -> pl.removePotionEffect(eff.getType()));
			// ON DONNE UN BOOST DE VITESSE AUX JOUEURS DE LA GAME PENDANT QUELQUES SECONDES
			pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 1, true));
			pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30, 2, true));
			pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, 2, true));
			pl.setGameMode(GameMode.SURVIVAL);
			// KITS
			pl.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 10));
		}

		List<Location> list = UHCTeleport.getCircle(new Location(Bukkit.getWorld("world"), 0, 200, 0), 800,
				TeamRegister.getregisteredTeams().size());

		int i = 0;
		// ON TP LES JOUEURS ALEATOIREMENT SUR LA MAP
		for (Team team : TeamRegister.getregisteredTeams()) {
			for (String name : team.getTeam().getEntries()) {
				Player p = Bukkit.getPlayer(name);
				if (p != null) {
					p.teleport(list.get(i));
				}
			}
			i++;
		}
		Flying_Spawn.delete();
		UHCMain.timer.startTimer();
		UHCMain.border.start();
	}

}
