package me.brook.placeholderkits;

import org.bukkit.plugin.java.JavaPlugin;

public class UltimatePapi extends JavaPlugin {

	public Configuration config;

	@Override
	public void onEnable() {
		new KitPapi(this).register();

		config = new Configuration(this, "config.yml");
	}

//	@EventHandler
//	public void onCommand(PlayerCommandPreprocessEvent event) {
//		System.out.println(PlaceholderAPI.setPlaceholders(event.getPlayer(), "%bkits_available-tools%"));
//	}

}
