package fr.falkoyt.Main;

//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
//import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.falkoyt.Commands.CommanduhcKit;
import fr.falkoyt.Commands.CommanduhcStart;
import fr.falkoyt.Commands.Commanduhcrevive;
import fr.falkoyt.Event.EventsManager;
import fr.falkoyt.Teams.Role;
import fr.falkoyt.Teams.TeamRegister;
import fr.falkoyt.Timer.Timer;
import fr.falkoyt.Timer.UHCState;
import fr.falkoyt.Utils.Flying_Spawn;
import fr.falkoyt.Utils.WorldBorderManager;


//import org.bukkit.scoreboard.DisplaySlot;

//import fr.falkoyt.scoreboards.ScoreBoardAPI;
//import net.minecraft.server.v1_8_R3.IChatBaseComponent;
//import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
//import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class UHCMain extends JavaPlugin implements Plugin {

	public static JavaPlugin pl;
	public static UHCMain Instance;
	

	public static HashMap<Player, Role> roles = new HashMap<>();
	public static Timer timer = new Timer();
	public static WorldBorderManager border = new WorldBorderManager();

	@Override
	public void onLoad() {
		pl = this;
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
	
	// LORSQUE LE PLUGIN SALLUME
	public void onEnable() {
		Bukkit.getWorlds().forEach(w -> w.setGameRuleValue("naturalRegeneration", "false"));
		// ON REGISTER TOUT LES LISTENERS
		TeamRegister.Initialisation();
		Role.initBook();
		EventsManager.registerEvents(this);
		getCommand("uhcstart").setExecutor(new CommanduhcStart());
		getCommand("kit").setExecutor(new CommanduhcKit());
		getCommand("revive").setExecutor(new Commanduhcrevive());
		UHCState.setState(UHCState.WAIT);
		Flying_Spawn.create();
		for (Player p : Bukkit.getOnlinePlayers())
			Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(p, null));
	}

	@Override
	public void onDisable() {
		Bukkit.getOnlinePlayers().forEach(p -> p.setPlayerListName(p.getName()));
	}
}
		
		


