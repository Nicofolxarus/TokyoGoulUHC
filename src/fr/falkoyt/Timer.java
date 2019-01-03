package fr.falkoyt;

import org.bukkit.Bukkit;

public class Timer {

	private long seconde = 0;
	private int days = 0;

	public void startTimer() {
		Bukkit.getScheduler().runTaskTimer(UHCMain.pl, new Runnable() {
			@Override
			public void run() {
				seconde++;
				if (seconde % (20 * 60) == 0) {
					days++;
					if (days == 5)
						UHCMain.border.mouveborder();
				} else if (seconde % (10 * 60) == 0) {
					if (days == 2)
						Role.RolesAtributions();
					Role.GiveRoleEffect();
				}
			}
		}, 20, 20);
	}
}
