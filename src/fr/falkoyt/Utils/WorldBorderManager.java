package fr.falkoyt.Utils;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class WorldBorderManager {
	
	public void start() {
		WorldBorder wb = Bukkit.getWorlds().get(0).getWorldBorder();
		wb.setCenter(0, 0);
		wb.setSize(2000);
		
	}
	
	public void mouveborder() {
		WorldBorder wb = Bukkit.getWorlds().get(0).getWorldBorder();
		wb.setSize(150, 3700);
	}
	
}
