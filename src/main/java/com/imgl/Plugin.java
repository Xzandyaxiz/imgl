package com.imgl;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
  	private static final Logger LOGGER=Logger.getLogger("imgl");

  	public void onEnable()
  	{
    	LOGGER.info("\033[36mEnabled imgl\033[39m");
		getCommand("imgload").setExecutor(new ImgloadCmd());
  	}

  	public void onDisable()
  	{
    	LOGGER.info("imgl disabled");
  	}
}
