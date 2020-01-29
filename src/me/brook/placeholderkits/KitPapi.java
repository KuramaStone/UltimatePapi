package me.brook.placeholderkits;

import org.bukkit.entity.Player;

import com.songoda.ultimatekits.UltimateKits;
import com.songoda.ultimatekits.kit.Kit;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class KitPapi extends PlaceholderExpansion {

	private UltimateKits instance;
	private final UltimatePapi plugin;

	public KitPapi(UltimatePapi main) {
		this.plugin = main;
		instance = UltimateKits.getInstance();
	}

	@Override
	public String onPlaceholderRequest(Player player, String identifier) {

		// %example_placeholder1%
		if(identifier.startsWith("delay")) {

			String[] split = identifier.split("-");

			if(split.length == 0) {
				return "kit not found";
			}

			Kit kit = instance.getKitManager().getKit(split[1]);

			if(kit == null) {
				return "kit not found";
			}

			long delay = kit.getNextUse(player).longValue();

			if(delay == 0 || delay == -1) {
				return plugin.config.getString("Papi.delay.available");
			}

			String formatted = formatMilliseconds(delay);

			return formatted;
		}
		else if(identifier.startsWith("available")) {

			String[] split = identifier.split("-");

			if(split.length == 0) {
				return "kit not found";
			}

			Kit kit = instance.getKitManager().getKit(split[1]);

			if(kit == null) {
				return "kit not found";
			}
			long delay = kit.getNextUse(player);

			return delay > 0 ? plugin.config.getString("Papi.available.false")
					: plugin.config.getString("Papi.available.true");
		}

		return "null";
	}

	private String formatMilliseconds(long delay) {

		long days = delay / (1000l * 60 * 60 * 24);
		delay -= days * 1000l * 60 * 60 * 24;

		long hours = delay / (1000l * 60 * 60);
		delay -= hours * 1000l * 60 * 60;

		long minutes = delay / (1000l * 60);
		delay -= minutes * 1000l * 60;

		long seconds = delay / 1000l;

		StringBuilder sb = new StringBuilder();
		if(days != 0) {
			sb.append(days + "d ");
		}
		if(hours != 0) {
			sb.append(hours + "h ");
		}
		if(minutes != 0) {
			sb.append(minutes + "m ");
		}
		sb.append(seconds + "s");

		return sb.toString();
	}

	@Override
	public boolean register() {
		return PlaceholderAPI.registerPlaceholderHook(getIdentifier(), this);
	}

	@Override
	public String getPlugin() {
		return plugin.getName();
	}

	@Override
	public String getAuthor() {
		return "Brook Stone";
	}

	@Override
	public String getIdentifier() {
		return "bkits";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

}
