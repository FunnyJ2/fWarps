package tk.ifunny.mc.warp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;

public class WarpCommand implements CommandExecutor {
	
	private WarpPlugin plugin;
	
	public WarpCommand(WarpPlugin instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission(plugin.getConfig().getString("permissions.warp")) || player.isOp()) {
				if(args.length == 1) {
					Warp warp = null;
					for(Warp w : plugin.warps) {
						if(w.getName().equalsIgnoreCase(args[0])) {
							warp = w;
						}
					}
					if(warp != null) {
						player.teleport(warp.getLocation());
						player.sendMessage(ChatColor.GOLD + "Warped to: " + ChatColor.WHITE + warp.getName());
						plugin.log.info("Warping " + player.getName() + " to the warp '" + warp.getName() + "'");
					} else {
						player.sendMessage(ChatColor.RED + "There is no such warp: " + ChatColor.WHITE + args[0]);
						player.sendMessage(ChatColor.GOLD + "To view a list of warps, please type " + ChatColor.WHITE + "/warplist");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /" + label + " <warp>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only players can warp.");
		}
		return true;
	}
	
}
