package fr.falkoyt.Event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class UHCBlocksDrops implements Listener {

	@EventHandler
	public void breakBlock(BlockBreakEvent e) {

		// CREER UNE VARIABLE LOCATION
		Location breakLoc = e.getBlock().getLocation();

		switch (e.getBlock().getType()) {

		case IRON_ORE:
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			// CA VA DROP 1 LINGOTS DE FER
			breakLoc.getWorld().dropItemNaturally(breakLoc, new ItemStack(Material.IRON_INGOT, 1));
			break;

		case GOLD_ORE:
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			// CA VA DROP 1 LINGOTS DE FER
			breakLoc.getWorld().dropItemNaturally(breakLoc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		/*
		 * case GRAVEL: e.setCancelled(true); e.getBlock().setType(Material.AIR); // CA
		 * VA DROP 1 FLECHES breakLoc.getWorld().dropItemNaturally(breakLoc, new
		 * ItemStack(Material.ARROW, 6)); break;
		 */
		default:
			break;

		}
	}

}
