package tk.ifunny.mc.warp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;

public class SetWarpCommand implements CommandExecutor {
	
	private WarpPlugin plugin;
	
	public SetWarpCommand(WarpPlugin instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission(plugin.getConfig().getString("permissions.setwarp")) || player.isOp()) {
				if(args.length == 1) {
					String warpname = args[0];
					String author = player.getName();
					int id = (plugin.warps.get(plugin.warps.size() - 1).getId() + 1);
					String world = player.getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					Warp newwarp = new Warp(plugin, warpname, author, id, world, x , y, z);
					plugin.warps.add(newwarp);
					
					int x2 = (int) x;
					int y2 = (int) y;
					int z2 = (int) z;
					String query = "INSERT INTO `warps` (`warp_id`, `warp_name`, `warp_author`, `world_name`, `x`, `y`, `z`) VALUES (NULL, '"+warpname+"', '"+author+"', '"+world+"', '"+x2+"', '"+y2+"', '"+z2+"');";
					plugin.mysql.manipulateData(query);
					
					player.sendMessage(ChatColor.GOLD + "Added warp " + ChatColor.GREEN + warpname + ChatColor.GOLD + "!");
					plugin.log.info("Player " + author + " set a new warp '" + warpname +  "' at coordinates (" + x2 + " " + y2 + " " + z2 + "/x y z)");
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /" + label + " <warpname>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You don't have permission to do this.");
			}
		} else {
			sender.sendMessage("This command is only available to players.");
		}
		
		return true;
	}

}
