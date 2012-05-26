package tk.ifunny.mc.warp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;

public class WarpListCommand implements CommandExecutor {
	
	private WarpPlugin plugin;
	
	public WarpListCommand(WarpPlugin instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission(plugin.getConfig().getString("permissions.warplist")) || sender.isOp()) {
			if(!(plugin.warps.size() == 0)) {
				StringBuilder b = new StringBuilder();
				b.append(ChatColor.GOLD + "Available warps: ");
				for(Warp w : plugin.warps) {
					b.append(ChatColor.GREEN + w.getName() + ChatColor.WHITE + ", ");
				}
				String done = b.toString();
				done = done.substring(0, done.length() - 2);
				sender.sendMessage(done);
			} else {
				sender.sendMessage(ChatColor.RED + "There are no warps available.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		}
		
		return true;
	}

}
