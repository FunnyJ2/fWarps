package tk.ifunny.mc.warp;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import tk.ifunny.mc.warp.command.RemoveWarpCommand;
import tk.ifunny.mc.warp.command.SetWarpCommand;
import tk.ifunny.mc.warp.command.WarpCommand;
import tk.ifunny.mc.warp.command.WarpInfoCommand;
import tk.ifunny.mc.warp.command.WarpListCommand;
import tk.ifunny.mc.warp.db.MySQL;
import tk.ifunny.mc.warp.runnable.InitMySQL;
import tk.ifunny.mc.warp.runnable.LogStartup;

public class WarpPlugin extends JavaPlugin {
	
	public ArrayList<Warp> warps = new ArrayList<Warp>();
	
	public Logger log;
	public PluginDescriptionFile pdf;
	public MySQL mysql; /* init'd in runnable */
	
	private InitMySQL runnable_initmysql;
	private LogStartup runnable_logstartup;
	
	@Override
	public void onEnable() {
		this.log = this.getLogger();
		this.pdf = this.getDescription();
		
		this.runnable_initmysql = new InitMySQL(this);
		this.runnable_logstartup = new LogStartup(this);
		
		this.log.info("Loading " + pdf.getName() + " version " + pdf.getVersion() + " by " + pdf.getAuthors());
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		this.getCommand("warp").setExecutor(new WarpCommand(this));
		this.getCommand("warplist").setExecutor(new WarpListCommand(this));
		this.getCommand("warpinfo").setExecutor(new WarpInfoCommand(this));
		this.getCommand("setwarp").setExecutor(new SetWarpCommand(this));
		this.getCommand("removewarp").setExecutor(new RemoveWarpCommand(this));
		
		this.log.info("Loaded");
		
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, runnable_initmysql, 1);
		if(this.getConfig().getBoolean("logstartup")) {
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, runnable_logstartup);
		}
	}
	
}
