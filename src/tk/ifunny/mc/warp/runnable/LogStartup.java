package tk.ifunny.mc.warp.runnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import tk.ifunny.mc.warp.WarpPlugin;

public class LogStartup implements Runnable {
	
	private WarpPlugin plugin;
	
	public LogStartup(WarpPlugin instance) {
		this.plugin = instance;
	}

	@Override
	public void run() {
		try {
			String plgname = plugin.pdf.getName();
			String ver = plugin.pdf.getVersion();
			URL url = new URL("http://ifunny.tk/plugins/startuplogger.php?p=" + plgname + "&v=" + ver);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if (line.equals("yep")) {
					//yeh
				} else {
					//nope
				}
			}
		} catch (Exception e) {
			// Meh
		}
	}

}
