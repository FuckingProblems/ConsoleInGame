package no.trrlgn.consoleingame;

import org.bukkit.plugin.java.JavaPlugin;

public class CIG extends JavaPlugin {

	public CIGHandler ch = new CIGHandler(this);
	
	public void onEnable() {
		saveDefaultConfig();
		getCommand("consoleingame").setExecutor(new CIGCommand(this));
	}
	
	public void onDisable() {
		
	}
	
}
