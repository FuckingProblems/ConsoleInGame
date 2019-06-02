package no.trrlgn.consoleingame;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CIGHandler {

	public CIG plugin;

	public CIGHandler(CIG pl) {
		plugin = pl;
	}

	public ArrayList<String> playerID = new ArrayList<String>();

	public boolean isPlayerWhitelisted(String p) {
		if(playerID.contains(p)) {
			return true;
		}
		return false;
	}

	public void reloadWhitelist() {
		playerID.clear();
		int num = plugin.getConfig().getStringList("whitelist").size();
		for(int i = 0; i < num; i++) {
			playerID.add(plugin.getConfig().getStringList("whitelist").get(i));
		}
	}

	public void addPlayerToWhitelist(String p) {
		if(isPlayerWhitelisted(p)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.alreadyCIG")));
		} else {
			List<String> pList = plugin.getConfig().getStringList("whitelist");
			pList.add(p);
			plugin.getConfig().set("whitelist", pList);
			plugin.saveConfig();
			reloadWhitelist();
		}
	}

	public void removePlayerFromWhitelist(String p) {
		List<String> pList = plugin.getConfig().getStringList("whitelist");
		pList.remove(p);
		plugin.getConfig().set("whitelist", pList);
		plugin.saveConfig();
		reloadWhitelist();
	}

	public void runCIGCommand(String p, String[] args) {
		if(isPlayerWhitelisted(p)) {
			String[] split = args.toString().split("run");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), split.toString());
			Bukkit.getPlayer(p).sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commandRun")));
		} else {
			Bukkit.getPlayer(p).sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.noPermission")));
		}
	}

}
