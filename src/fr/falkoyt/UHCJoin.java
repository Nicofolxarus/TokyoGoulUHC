package fr.falkoyt;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
//import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.scoreboard.DisplaySlot;
//import org.bukkit.scoreboard.DisplaySlot;
//import org.bukkit.scoreboard.Objective;
//import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.falkoyt.scoreboards.ScoreBoardAPI;
import fr.falkoyt.scoreboards.ScoreBoardAPI.ObjectifSideBar;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class UHCJoin implements Listener {

	static int Team;
	static int task;
	static int timer = 15;
public static HashMap<UUID, ScoreBoardAPI> sclist = new HashMap<>();
	
	

	public enum ScoreboardEnum {
		ScUHC;
	}
	public String ColorFonce = "§4";
	public String ColorClair = "§f";
	public String ColorFonce1 = "§c";
	public String ColorClair1 = "§f";

	public static void sendTablist(Player p, String header, String footer) {
		if (!p.isOnline())
			return;
		if (header == null)
			header = "";
		if (footer == null)
			footer = "";

		IChatBaseComponent tabHeader = ChatSerializer.a("{\"text\":\"" + header + "\"}");
		IChatBaseComponent tabFooter = ChatSerializer.a("{\"text\":\"" + footer + "\"}");

		PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabHeader);

		try {
			Field field = headerPacket.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(headerPacket, tabFooter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(headerPacket);
		}
	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player p1 : Bukkit.getOnlinePlayers()) {
			setScoreBoard(p1);
			sendTablist(p1,"§8[§cTokyoGhoul§8]\n§r    §f»§8§l§m---------------------------------------§r§f«    \n","\n§r    §f»§8§l§m---------------------------------------§r§f«    \n");
			ScoreBoardAPI s = new ScoreBoardAPI();
			s.addObjective("Vie", "health", DisplaySlot.PLAYER_LIST);
			s.addObjective("§4❤", "dummy", DisplaySlot.BELOW_NAME);
			ScoreBoardAPI.updateObjectifHealth();
			s.setScoreBoardPlayer(p1);
			sclist.put(p1.getUniqueId(), s);
		
			
		}
		for (Player p1 : Bukkit.getOnlinePlayers()) {
		ScoreBoardAPI s = sclist.get(p1.getUniqueId());
		ObjectifSideBar os = s.createObjectifSidebar(ColorFonce + "TokyoGhoul", ScoreboardEnum.ScUHC);
		os.display();
		if (os != null) {
			os.addLine(14, "§8§m----»§r     §7Temps        §8§m«----");
			os.addLine(13, "§3Pvp"  + UHCMain.timer.isPvp());
			os.addLine(12, "§3Bordure");
			os.addLine(13, "§3Roles");
			os.addLine(12, "§8§m----»§r     §7Stats        §8§m«----");
			os.addLine(11, "§3Team(s)" +":§f" + Team);
			os.addLine(10, "§3Kill(s)");
			os.addLine(9, "§8§m----»§r     §7Map        §8§m«----");
			os.addLine(8, "§3Centre");
			os.addLine(7, "§3Taille");
			
			os.display();
			s.setScoreBoardPlayer(p1);
			sclist.put(p1.getUniqueId(), s);
		}
	
		}
		
		// LORSQUE UN JOUEUR REJOIN LA GAM
		
		//sendTablist(p, "§f>>[§cTokyo Ghoul UHC§f]<< \n§r   §8§l§m-----------------------------------------§r    \n",
				//"\n§r    §8§l§m-----------------------------------------§r    \n");
		e.setJoinMessage("§f>> §4UHC §e" + p.getName() + " c'est connecté(e) !");
		ScoreboardManager sb = Bukkit.getScoreboardManager();
		Scoreboard board = sb.getNewScoreboard();
		//ScoreBoardAPI.updateObjectifHealth();
	//	ScoreBoardAPI s = new ScoreBoardAPI();
	
		
		//ObjectifSideBar o = s.createObjectifSidebar(ColorFonce + "UHC", ScoreboardEnum.ScUHC);
		//o.addLine(6, "§8§m----»§r              §8§m«----");
		//o.addLine(5, "Joueurs");
		
	

/*		Objective obj = board.registerNewObjective("Test", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§f>>[§4Tokyo Ghoul UHC§f]<<");
		
		Score Temps = obj.getScore("§8●§a Temps : §f" + timer);
		Temps.setScore(3);
		Score joueurs = obj.getScore("§8●§3 Joueurs : §f" + Bukkit.getOnlinePlayers().size());
		joueurs.setScore(5);

		Score équipes = obj.getScore("§8●§a Equipes : §f" + Team);
		équipes.setScore(4);

		Score kill = obj.getScore("§8●§4 Kills :§f " + p.getStatistic(Statistic.PLAYER_KILLS));
		kill.setScore(2);

		Objective objective = board.registerNewObjective("Vie", "health");
		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

		Objective objective1 = board.registerNewObjective("§4❤", "health");
		objective1.setDisplaySlot(DisplaySlot.BELOW_NAME);*/

		 //On set les teams sur le scoreboard du joueur
		for (org.bukkit.scoreboard.Team t : sb.getMainScoreboard().getTeams()) {
			if (board.getTeam(t.getName()) == null) {
				org.bukkit.scoreboard.Team newt = board.registerNewTeam(t.getName());
				newt.setPrefix(t.getPrefix());
				newt.setSuffix(t.getSuffix());
				for (String player : t.getEntries()) {
					newt.addEntry(player);
				}
			}
		}

		//p.setScoreboard(s);
		if (UHCState.isState(UHCState.WAIT)) {
			if (!UHCMain.playerInGame.contains(p.getUniqueId())) {
				UHCMain.playerInGame.add(p.getUniqueId());
			}
			p.teleport(new Location(p.getWorld(), 0, 201, 0));

			Team t = fr.falkoyt.Team.getTeam(p);

			ItemStack i = new ItemStack(Material.BANNER);
			BannerMeta im = (BannerMeta) i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Choisir son équipe");
			if (t != null)
				im.setBaseColor(t.getDyecolor());
			i.setItemMeta(im);
			p.getInventory().setItem(4, i);
		} else if (UHCMain.playerInGame.contains(p.getUniqueId())) {
			p.setGameMode(GameMode.SPECTATOR);
		}
		
	}

	private void setScoreBoard(Player p1) {
		ScoreBoardAPI s = new ScoreBoardAPI();
		s.addObjective("Vie", "health", DisplaySlot.PLAYER_LIST);
		s.addObjective("§4❤", "dummy", DisplaySlot.BELOW_NAME);
		ScoreBoardAPI.updateObjectifHealth();
		s.setScoreBoardPlayer(p1);
		sclist.put(p1.getUniqueId(), s);
		ObjectifSideBar o = s.createObjectifSidebar(ColorFonce + "TokyoGhoul", ScoreboardEnum.ScUHC);
		o.addLine(6, "§8§m----»§r              §8§m«----");
		o.addLine(5, "ssss");
		o.addLine(4, "ssss");
		o.addLine(3, "ssss");
		o.addLine(2, "ssss");
		o.addLine(1, "ssss");
		s.setScoreBoardPlayer(p1);
		sclist.put(p1.getUniqueId(), s);
	
	
	}
	

	@EventHandler
	public void quit(PlayerQuitEvent e) {

		// ON RECUP LE JOUEUR QUI SEST DECO
		Player p = e.getPlayer();
		e.setQuitMessage("§f>> §4UHC §e " + p.getName() + " c'est déconnecté(e) !");

		UHCMain.playerInGame.remove(p.getUniqueId());
	}

	// ON VA METTRE DES NIVEAUX DANS LA BAR DXP DES JOUEURS DE LA GAME EN FONCTION
	// DU TIMER
	public void setLevel(int timer) {

		// ON RECUP L UUID DES JOUEURS DE LA GAME
		for (UUID uuid : UHCMain.playerInGame) {
			Player pl = Bukkit.getPlayer(uuid);
			pl.setLevel(timer);
		}
	}

}
