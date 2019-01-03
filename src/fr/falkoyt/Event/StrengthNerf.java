package fr.falkoyt.Event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class StrengthNerf implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player))
			return;

		Player p = (Player) event.getDamager();

		if (!p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
			return;
		Bukkit.getConsoleSender().sendMessage("Default damage: " + event.getDamage());
		event.setDamage(event.getDamage() / 2.3 * 1.5);
		Bukkit.getConsoleSender().sendMessage("Modify damage: " + event.getDamage());
		
	}
}
