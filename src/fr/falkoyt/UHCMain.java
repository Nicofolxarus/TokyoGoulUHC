package fr.falkoyt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class UHCMain extends JavaPlugin implements Plugin {

	public static JavaPlugin pl;

	public static HashMap<Player, Role> roles = new HashMap<>();
	public static Timer timer = new Timer();
	public static WorldBorderManager border = new WorldBorderManager();

	@Override
	public void onLoad() {
		pl = this;
		super.onLoad();
	}

	// IL SAGIT DE LA LISTE DES JOUEURS EN JEU
	public static ArrayList<UUID> playerInGame = new ArrayList<>();

	public static int getEpisode(int seconde) {
		return ((seconde / 60) / 20) + 1;
	}

	public static String getTimeEpisode(int seconde) {
		int Episode = (seconde / 60) / 20;
		int Minute = (seconde - ((20 * Episode) * 60)) / 60;
		int Seconde = (seconde - ((20 * Episode) * 60)) % 60;
		return Minute + "min " + Seconde + "s";
	}

	public int getPlayersPerTeam() {
		return 0;
	}

	// LORSQUE LE PLUGIN SALLUME
	public void onEnable() {
		// ON REGISTER TOUT LES LISTENERS
		EventsManager.registerEvents(this);
		getCommand("uhcstart").setExecutor(new CommanduhcStart());
		getCommand("kit").setExecutor(new CommanduhcKit());
		getCommand("revive").setExecutor(new Commanduhcrevive());
		TeamRegister.Initialisation();
		UHCState.setState(UHCState.WAIT);
		Flying_Spawn.create();
		for (Player p : Bukkit.getOnlinePlayers())
			Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(p, null));
		super.onEnable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

}
