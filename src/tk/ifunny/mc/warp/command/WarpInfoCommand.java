package tk.ifunny.mc.warp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;

public class WarpInfoCommand implements CommandExecutor {
	
	private WarpPlugin plugin;
	
	public WarpInfoCommand(WarpPlugin instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission(plugin.getConfig().getString("permissions.warpinfo")) || sender.isOp()) {
			if(args.length == 1) {
				Warp warp = null;
				for(Warp w : plugin.warps) {
					if(w.getName().equalsIgnoreCase(args[0])) {
						warp = w;
					}
				}
				if(warp != null) {
					sender.sendMessage(ChatColor.BLACK + "~ " + ChatColor.GOLD + "Warp info for warp " + ChatColor.GREEN + warp.getName() + ChatColor.BLACK + " ~");
					sender.sendMessage(ChatColor.GOLD + "Name: " + ChatColor.GRAY + warp.getName());
					sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.GRAY + warp.getAuthor());
					sender.sendMessage(ChatColor.GOLD + "World: " + ChatColor.GRAY + warp.getWorld().getName());
					ChatColor GRAY = ChatColor.GRAY;
					ChatColor WHITE = ChatColor.WHITE;
					String loc = GRAY + "" + warp.getLocation().getBlockX() + WHITE + ", " + GRAY + warp.getLocation().getBlockY() + WHITE + ", " + GRAY + warp.getLocation().getBlockZ();
					sender.sendMessage(ChatColor.GOLD + "Location: " + loc);
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
