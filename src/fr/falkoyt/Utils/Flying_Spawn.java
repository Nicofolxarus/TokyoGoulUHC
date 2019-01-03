package fr.falkoyt.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Flying_Spawn {

	@SuppressWarnings("deprecation")
	public static void create() {
		for (int x = -19; x <= 19; x++) {
			for (int z = -19; z <= 19; z++) {
				Location block = new Location(Bukkit.getWorlds().get(0), x, 199, z);
				block.getBlock().setType(Material.BARRIER);
			}
		}
		for (int x = -20; x <= 20; x++) {
			for (int z = -20; z <= 20; z++) {
				if (z == 20 || z == -20 || x == 20 || x == -20) {
					Location block = new Location(Bukkit.getWorlds().get(0), x, 199, z);
					block.getBlock().setType(Material.QUARTZ_BLOCK);
				}
			}
		}
		for (int y = 200; y <= 204; y++) {
			for (int x = -20; x <= 20; x++) {
				for (int z = -20; z <= 20; z++) {
					if (z == 20 || z == -20 || x == 20 || x == -20) {
						Location block = new Location(Bukkit.getWorlds().get(0), x, y, z);
						block.getBlock().setType(Material.STAINED_GLASS_PANE);
						block.getBlock().setData((byte) 14);
					}
				}
			}
		}
	}

	public static void delete() {
		for (int y = 199; y <= 204; y++) {
			for (int x = -20; x <= 20; x++) {
				for (int z = -20; z <= 20; z++) {
					Location block = new Location(Bukkit.getWorlds().get(0), x, y, z);
					block.getBlock().setType(Material.AIR);
				}
			}
		}
	}	
}
