package fr.falkoyt.Event;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.falkoyt.Main.UHCEndGame;
import fr.falkoyt.Main.UHCGame;
import fr.falkoyt.Main.UHCMain;
import fr.falkoyt.menu.ChoiceTeam;

public class EventsManager {

	public static void registerEvents(UHCMain pl) {
		// ON CREER UNE VARIABLE PERMETTANT DE REGISTER LES EVENTS
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new UHCJoin(), pl);
		pm.registerEvents(new UHCNoFall(), pl);
		pm.registerEvents(new UHCGame(), pl);
		pm.registerEvents(new UHCSkullRegen(), pl);
		pm.registerEvents(new UHCBlocksDrops(), pl);
		pm.registerEvents(new UHCSpeedRecipes(), pl);
		pm.registerEvents(new UHCPvP(), pl);
		pm.registerEvents(new UHCEndGame(), pl);
		pm.registerEvents(new UHCListener(), pl);
		pm.registerEvents(new UHCAutoLeafDecay(), pl);
		pm.registerEvents(new ChoiceTeam(), pl);
		pm.registerEvents(new DeathEvent(), pl);
		
		
		pm.registerEvents(new WeatherManager(), pl);
		pm.registerEvents(new StrengthNerf(), pl);
	}

}