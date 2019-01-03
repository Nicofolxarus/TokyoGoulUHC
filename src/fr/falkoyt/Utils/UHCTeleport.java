package fr.falkoyt.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

public class UHCTeleport {

	public static List<Location> getCircle(Location center, double radius, int amount) {
		List<Location> locations = new ArrayList<>();
		World world = center.getWorld();
		double increment = (2 * Math.PI) / amount;
		for (int i = 0; i < amount; i++) {
			double angle = i * increment;
			double x = center.getX() + (radius * Math.cos(angle));
			double z = center.getZ() + (radius * Math.sin(angle));
			int y = world.getHighestBlockYAt((int) x, (int) z);
			locations.add(new Location(world, x, y, z));
		}
		return locations;
	}
}
