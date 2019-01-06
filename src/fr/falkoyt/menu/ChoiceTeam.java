package fr.falkoyt.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.falkoyt.Teams.Team;
import fr.falkoyt.Teams.TeamRegister;
import fr.falkoyt.Timer.UHCState;

public class ChoiceTeam implements Listener {

	public static String NomInventaire = ChatColor.GOLD + "    Choisir son équipe";

	@SuppressWarnings("deprecation")
	public static void addItem(Inventory inv, Team team, int slot) {
		ItemStack banner = new ItemStack(Material.BANNER);
		BannerMeta meta = (BannerMeta) banner.getItemMeta();
		meta.setDisplayName(team.getChatcolor() + team.getName() + team.getTeam().getSuffix());
		meta.setBaseColor(team.getDyecolor());
		List<String> lore = new ArrayList<>();
		for (OfflinePlayer pl : team.getTeam().getPlayers()) {
			lore.add(team.getChatcolor() + ">> " + pl.getName());
		}
		meta.setLore(lore);
		banner.setItemMeta(meta);
		inv.setItem(slot - 1, banner);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ChoiceTeamEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory() == null)
			return;

		if (e.getInventory().getName().equals(NomInventaire)) {

			if (e.getCurrentItem() == null)
				return;
				
			if (e.getCurrentItem().getType() == Material.BANNER) {
				BannerMeta banner = (BannerMeta) e.getCurrentItem().getItemMeta();
				for (Team team : TeamRegister.getregisteredTeams()) {
					if (banner.getBaseColor() == team.getDyecolor()) {
						if (team.getTeam().getSize() < 5) {
							p.sendMessage("§8Vous avez rejoint l'équipe " + team.getChatcolor() + team.getName());
							team.getTeam().addPlayer(p);
							for (Player pl : Bukkit.getOnlinePlayers()) {
								Scoreboard sc = pl.getScoreboard();
								org.bukkit.scoreboard.Team teamplayer = sc.getTeam(team.getTeam().getName());
								if (teamplayer != null)
									teamplayer.addPlayer(p);
							}
							p.setPlayerListName(team.getTeam().getPrefix() + p.getName() + ChatColor.RESET);
							p.setDisplayName(team.getTeam().getPrefix() + p.getName() + ChatColor.RESET);
							
							String newligne = "§8●§a Equipes : §f" + team.getTeam().getPrefix()+team.getName();
							Scoreboard board = p.getScoreboard();
							Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
							
							for (String ligne : board.getEntries()) {
								if (ligne.contains("Equipes")) {
									board.resetScores(ligne);
									objective.getScore(newligne).setScore(1);
									break;
								}
							}
							
							ItemStack i = new ItemStack(Material.BANNER);
							BannerMeta im = (BannerMeta) i.getItemMeta();
							im.setDisplayName(ChatColor.GOLD + "Choisir son équipe");
							im.setBaseColor(banner.getBaseColor());
							i.setItemMeta(im);
							p.getInventory().setItem(4, i);
						} else {
							p.sendMessage(ChatColor.RED + "Cette équipe est complète !");
						}
						break;
					}
				}
			} else if (e.getCurrentItem().getType() == Material.BARRIER) {
				if (Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(p) != null) {
					Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(p).removeEntry(e.getWhoClicked().getName());
					for (Player pl : Bukkit.getOnlinePlayers()) {
						Scoreboard sc = pl.getScoreboard();
						org.bukkit.scoreboard.Team teamplayer = sc.getPlayerTeam(p);
						if (teamplayer != null)
							teamplayer.removeEntry(e.getWhoClicked().getName());
					}
				}
				e.getWhoClicked().sendMessage(ChatColor.RED + "Vous avez quitté votre équipe !");
				((Player) e.getWhoClicked()).setDisplayName(e.getWhoClicked().getName());
			}
			e.setCancelled(true);
			openTeamInv(p);
		}
	}

	private void openTeamInv(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9, NomInventaire);
		for (Team team : TeamRegister.getregisteredTeams()) {
			addItem(inv, team, team.getId());
		}
		ItemStack stack = new ItemStack(Material.BARRIER);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Random");
		stack.setItemMeta(meta);

		inv.setItem(8, stack);
		
		p.openInventory(inv);
	}

	@EventHandler
	public void Options(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		if ((UHCState.isState(UHCState.WAIT))
				&& ((a.equals(Action.RIGHT_CLICK_AIR)) || (a.equals(Action.RIGHT_CLICK_BLOCK)))
				&& (p.getItemInHand().getType() == Material.BANNER)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Choisir son équipe")) {
				e.setCancelled(true);
				openTeamInv(p);
			}
		}
	}
}
