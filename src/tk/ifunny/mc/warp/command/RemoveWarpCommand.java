package tk.ifunny.mc.warp.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;

public class RemoveWarpCommand implements CommandExecutor {
	
	private WarpPlugin plugin;
	
	public RemoveWarpCommand(WarpPlugin instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission(plugin.getConfig().getString("permissions.removewarp")) || sender.isOp()) {
			if(args.length == 1) {
				Warp warp = null;
				for(Warp w : plugin.warps) {
					if(w.getName().equalsIgnoreCase(args[0])) {
						warp = w;
					}
				}
				if(warp != null) {
					plugin.warps.remove(warp);
					String query = "DELETE FROM `warps` WHERE `warp_name` = '" + warp.getName() + "'";
					plugin.mysql.manipulateData(query);
					
					sender.sendMessage(ChatColor.GOLD + "Removed warp: " + ChatColor.GREEN + warp.getName());
					sender.sendMessage(ChatColor.GRAY + "Note: this cannot be undone");
					
					Location loc = warp.getLocation();
					plugin.log.info(sender.getName() + " removed warp '" + warp.getName() + "' ("+loc.getBlockX()+" "+loc.getBlockY()+" "+loc.getBlockZ()+")");
				} else {
					sender.sendMessage(ChatColor.RED + "There is no such warp: " + ChatColor.WHITE + args[0]);
					sender.sendMessage(ChatColor.GOLD + "To view a list of warps, please type " + ChatColor.WHITE + "/warplist");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <warp>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		}
		return true;
	}

}
