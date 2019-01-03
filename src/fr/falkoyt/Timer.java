package fr.falkoyt;

import org.bukkit.Bukkit;

public class Timer {

	private long seconde = 0;
	private int days = 0;
	private boolean pvp = false;

	public void startTimer() {
		Bukkit.getScheduler().runTaskTimer(UHCMain.pl, new Runnable() {
			@Override
			public void run() {
				seconde++;
				if (seconde % (20 * 60) == 0) {
					days++;
					if (days == 2) {
						UHCState.setState(UHCState.GAMEPVP);
						pvp = true;
						Bukkit.broadcastMessage("pvp activer");
					}
					if (days == 5) {
						UHCMain.border.mouveborder();
						Bukkit.broadcastMessage("border move");
					}
				} else if (seconde % (10 * 60) == 0) {
					if (days == 1) {
						Bukkit.broadcastMessage("roles set");
						Role.RolesAtributions();	
					}
					Role.GiveRoleEffect();
				}
			}
		}, 20, 20);
	}

	public long getSeconde() {
		return seconde;
	}

	public int getDays() {
		return days;
	}

	public boolean isPvp() {
		return pvp;
	}

}
