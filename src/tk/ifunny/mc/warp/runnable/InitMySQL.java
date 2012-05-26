package tk.ifunny.mc.warp.runnable;

import java.sql.ResultSet;

import tk.ifunny.mc.warp.Warp;
import tk.ifunny.mc.warp.WarpPlugin;
import tk.ifunny.mc.warp.db.MySQL;

public class InitMySQL implements Runnable {
	
	private WarpPlugin plugin;
	
	public InitMySQL(WarpPlugin instance) {
		this.plugin = instance;
	}
	
	@Override
	public void run() {
		//FileConfiguration config = plugin.getConfig();
		
		final String MYSQL_DATABASE = plugin.getConfig().getString("mysql.database");
		final String MYSQL_USERNAME = plugin.getConfig().getString("mysql.username");
		final String MYSQL_PASSWORD = plugin.getConfig().getString("mysql.password");
		final String MYSQL_HOST = plugin.getConfig().getString("mysql.hostname");
		
		try {
			plugin.mysql = new MySQL(MYSQL_DATABASE, MYSQL_USERNAME, MYSQL_PASSWORD, MYSQL_HOST);
			plugin.log.info("Initialized MySQL");
			
			ResultSet r = plugin.mysql.query("SELECT * FROM `warps`");
			while(r.next()) {
				String name = r.getString("warp_name");
				String author = r.getString("warp_author");
				int id = r.getInt("warp_id");
				String world = r.getString("world_name");
				double x = r.getDouble("x");
				double y = r.getDouble("y");
				double z = r.getDouble("z");
				
				Warp warp = new Warp(plugin, name, author, id, world, x, y, z);
				plugin.warps.add(warp);
				//plugin.log.info(name + " by " + author + " (ID:" + r.getInt("warp_id") + ") " + x + " " + y + " " + z);
			}
			plugin.log.info("Loaded warps from MySQL");
			return;
		} catch (Exception e) {
			plugin.log.severe("Failed to initialize mysql");
			e.printStackTrace();
			plugin.log.severe("Disabling plugin");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
			return;
		}
	}
	
}
