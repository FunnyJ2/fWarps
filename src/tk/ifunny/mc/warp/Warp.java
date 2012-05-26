package tk.ifunny.mc.warp;

import org.bukkit.Location;
import org.bukkit.World;

public class Warp {
	
	private WarpPlugin plugin;
	private String warpname;
	private String warp_author;
	private int warp_id;
	private String world_name;
	private double x;
	private double y;
	private double z;
	
	private Location location;
	private World world;
	
	public Warp(WarpPlugin instance, String warpname, String warp_author, int warp_id, String world, double x, double y, double z) {
		this.plugin = instance;
		this.warpname = warpname;
		this.warp_author = warp_author;
		this.warp_id = warp_id;
		this.world_name = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.location = new Location(plugin.getServer().getWorld(this.world_name), this.x, this.y, this.z);
		this.world = plugin.getServer().getWorld(this.world_name);
	}
	
	public String getName() {
		return this.warpname;
	}
	
	public String getAuthor() {
		return this.warp_author;
	}
	
	public int getId() {
		return this.warp_id;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public Location getLocation() {
		return this.location;
	}
}
