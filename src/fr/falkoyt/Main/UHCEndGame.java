package fr.falkoyt.Main;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class UHCEndGame implements Listener {

	public static void checkWin() {
		int online = UHCMain.playerInGame.size();

		if (online == 0) {
			// fin du jeu
			stopGame(10);
		}

		if (online == 1) {
			// fin du jeu
			for (UUID pl : UHCMain.playerInGame) {
				Player winner = Bukkit.getPlayer(pl);
				Bukkit.broadcastMessage("Le joueur : " + winner.getName() + " a gagnée le jeu ! Bravo à lui !");
				stopGame(6);
			}
		}
	}

	public static void stopGame(int timeUntilStop) {
		Bukkit.getScheduler().runTaskLater(UHCMain.pl, new Runnable() {

			@Override
			public void run() {
				for (@SuppressWarnings("unused")
				Player pl : Bukkit.getOnlinePlayers()) {
					// pl.kickPlayer("§4 La Partie et Fini Merci d'avoir Jouer avec Nous ^^ !");
				}
//				Bukkit.shutdown();
			}

		}, timeUntilStop * 20);

	}

}