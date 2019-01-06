package fr.falkoyt.Timer;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.falkoyt.Main.UHCMain;
import fr.falkoyt.Teams.Role;

public class Timer {

	private int seconde = 0;
	private int days = 0;
	private boolean pvp = false;
	public boolean roleGive = false;
	
	public void startTimer() {
		Bukkit.getScheduler().runTaskTimer(UHCMain.pl, new Runnable() {
			@Override
			public void run() {
				seconde++;
				if(!roleGive) {
					Bukkit.getOnlinePlayers().forEach(p -> {
						String newligne = "§8●§a Temps Role : §f" + (((30*60)-seconde <= 0) ? "§aOn" : timeToString((30*60)-seconde));
						
						Scoreboard board = p.getScoreboard();
						Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
						
						for (String ligne : board.getEntries()) {
							if (ligne.contains("Temps Role")) {
								board.resetScores(ligne);
								objective.getScore(newligne).setScore(1);
								break;
							}
						}
					});
				}
				
				if (seconde % (20 * 60) == 0) {
					days++;
					if (days == 2) {
						UHCState.setState(UHCState.GAMEPVP);
						pvp = true;
						Bukkit.broadcastMessage("§f>> §4UHC §aPvP Activé");
					}
					if (days == 5) {
						UHCMain.border.mouveborder();
						Bukkit.broadcastMessage("§f>> §4UHC §aBorder en Mouvement");
					}
				} else if (seconde % (10 * 60) == 0 && days == 1 && !roleGive) {
					Bukkit.broadcastMessage("§f>> §4UHC §aLes Roles ont été attribué");
					Role.RolesAtributions();
					roleGive = true;
					Role.GiveRoleEffect();
				}
				
				Bukkit.getOnlinePlayers().forEach(p -> {
					String newligne = "§8●§a Temps : §f" + timeToString(seconde);
					
					Scoreboard board = p.getScoreboard();
					Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
					
					for (String ligne : board.getEntries()) {
						if (ligne.contains("Temps :")) {
							board.resetScores(ligne);
							objective.getScore(newligne).setScore(1);
							break;
						}
					}
				});
			}
		}, 20, 20);
	}
	public static String timeToString(int seconde){
		int Heure = seconde / 3600;
		int Minute = seconde % 3600 / 60;
		int Seconde = seconde % 60;
		if(Heure < 1 && Minute < 1){
			return Seconde +"s";
		}else if(Heure < 1){
			return Minute+"min "+ Seconde +"s";
		}else{
			return Heure +"h "+Minute+"min";
		}
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
