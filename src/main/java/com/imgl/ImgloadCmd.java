package com.imgl;

import java.util.logging.Logger;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ImgloadCmd implements CommandExecutor {
    static final Material YELLOW_WOOL = Material.YELLOW_WOOL;
    
    private static final Logger LOGGER=Logger.getLogger("imgl");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        Location playerLocation = player.getLocation();
         /*
          * Error detection.
          */
        if (!(args.length >= 3)) {
            String message = "&1&4Important arguments are missing!";
            String coloredMessage = ChatColor.translateAlternateColorCodes('&', message);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', coloredMessage));
            return false;
        }

        String image = args[0].toString();
        player.sendMessage("Loaded image '" + image + "'.");

        DecodeImg img = new DecodeImg();
        ArrayList<int[]> imageinfo = img.GetImageProportions(image);


        for (int[] pixel : imageinfo) {
            LOGGER.info(String.format("(%d) at (%d, %d)", pixel[2], pixel[0], pixel[1]));
        }

        return true;
    }
}
