package no.trrlgn.consoleingame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CIGCommand implements CommandExecutor {

	CIG plugin;

	public CIGCommand(CIG pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().startsWith("consoleingame")) {
			if(sender.equals(Bukkit.getConsoleSender())) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("add")) {
						if(Bukkit.getPlayer(args[1]).equals(null)) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.playerNotFound")));
						} else {
							plugin.ch.addPlayerToWhitelist(Bukkit.getPlayer(args[1]).getUniqueId().toString());
						}
					} else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
						plugin.ch.removePlayerFromWhitelist(Bukkit.getPlayer(args[1]).getUniqueId().toString());
					}
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.noConsole")));
				}
			} else {
				if(args[0].equalsIgnoreCase("run")) {
					if(args.length == 1) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.enterCommand")));
					} else {
						plugin.ch.runCIGCommand(sender.getName(), args);
					}
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.noPlayer")));
				}
			}
		}
		return true;
	}

}
