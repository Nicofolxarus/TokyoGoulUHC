package fr.falkoyt.Teams;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.falkoyt.Main.UHCMain;

public class Role {

	public enum RoleType {
		Goul_Borgne, Goul, Policier;
	}

	private static ItemStack booksharp;
	private static ItemStack bookprotec;

	public static void initBook() {
		booksharp  = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta mbooksharp = (EnchantmentStorageMeta) booksharp.getItemMeta();
		mbooksharp.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		booksharp.setItemMeta(mbooksharp);
		
		bookprotec = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta mbookprotec = (EnchantmentStorageMeta) bookprotec.getItemMeta();
		mbookprotec.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bookprotec.setItemMeta(mbookprotec);
	}

	RoleType rt;
	private boolean givekit = false;

	public Role(RoleType rt) {
		this.rt = rt;
	}

	public void displayMessage(Player player) {
		switch (rt) {
		case Goul_Borgne:
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			player.sendMessage(
					"§e Bonne Nouvelle tu et une Ghoul Borgne tu obptiendras ton kit en tappant /kit !!!! Ton but et de gagner avec La Ghoul de ton équipe. ");
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			break;

		case Goul:
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			player.sendMessage(
					"§e Attention tu et Une Ghoul, obtient ton kit en tappant /kit !!! Ton but et de win avecc la ghoul borgne de ton équipe");
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			break;

		case Policier:
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			player.sendMessage(
					"§e Bravo tu est devenue POLICIER. en faisant /kit tu obtient ton kit !! Tu doit trouver les Ghouls de ton équipe et les tuers !!!  ");
			player.sendMessage(
					"§8§l§m§4=====================================================================================================================================");
			break;
		default:
			break;
		}
	}

	public boolean giveItem(Player player) {
		if (givekit)
			return false;
		switch (rt) {
		case Goul_Borgne:
			player.getWorld().dropItem(player.getLocation(), booksharp);
			break;

		case Goul:
			player.getWorld().dropItem(player.getLocation(), booksharp);
			break;
		case Policier:
			player.getWorld().dropItem(player.getLocation(), bookprotec);
			break;
		default:
			break;
		}
		givekit = true;
		return true;
	}

	public void giveEffect(Player player) {
		switch (rt) {
		case Goul_Borgne:
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 12000, 0, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 12000, 0, false, false));
			break;
		case Goul:
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 12000, 0, false, false));
			break;
		case Policier:
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12000, 0, false, false));
			break;
		default:
			break;
		}
	}

	public static void RolesAtributions() {
		Random rand = new Random();
		for (Team team : TeamRegister.getregisteredTeams()) {
			ArrayList<Player> plist = new ArrayList<>();
			for (String name : team.getTeam().getEntries()) {
				Player p = Bukkit.getPlayer(name);
				if (p != null) {
					plist.add(p);
				}
			}
			int size = plist.size();
			int nbgoul = size / 4;
			int nbgoul_borgne = size / 4;
			int nbpolicier = size / 2;

			int noteam = size - nbgoul - nbgoul_borgne - nbpolicier;
			switch (noteam) {
			case 1:
				nbgoul++;
				break;

			case 2:
				nbgoul++;
				nbgoul_borgne++;
				break;
			default:
				break;
			}

			for (int i = 0; i < nbgoul; i++) {
				int r = rand.nextInt(plist.size());
				UHCMain.roles.put(plist.get(r), new Role(RoleType.Goul));
				plist.remove(r);
			}

			for (int i = 0; i < nbgoul_borgne; i++) {
				int r = rand.nextInt(plist.size());
				UHCMain.roles.put(plist.get(r), new Role(RoleType.Goul_Borgne));
				plist.remove(r);
			}

			for (int i = 0; i < nbpolicier; i++) {
				int r = rand.nextInt(plist.size());
				UHCMain.roles.put(plist.get(r), new Role(RoleType.Policier));
				plist.remove(r);
			}

		}
		for (Entry<Player, Role> e : UHCMain.roles.entrySet()) {
			Bukkit.getConsoleSender().sendMessage(e.getKey().getDisplayName() + " => " + e.getValue().rt);
			e.getValue().displayMessage(e.getKey());
		}
	}

	public static void GiveRoleEffect() {
		for (Entry<Player, Role> e : UHCMain.roles.entrySet()) {
			e.getValue().giveEffect(e.getKey());
			Bukkit.getConsoleSender().sendMessage(e.getKey().getDisplayName() + " => effect of : " + e.getValue().rt);
		}
	}
}
