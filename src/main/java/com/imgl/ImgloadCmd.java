package com.imgl;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ImgloadCmd implements CommandExecutor {
    static final Material YELLOW_WOOL = Material.YELLOW_WOOL;

    HashMap<Material, int[]> colormaps = new HashMap<>();

    public ImgloadCmd() {
        colormaps.put(Material.STONE, new int[] { 142, 116, 84 }); // Stone
        colormaps.put(Material.GRASS_BLOCK, new int[] { 255, 255, 255 }); // Grass Block
        colormaps.put(Material.DIRT, new int[] { 191, 191, 191 }); // Dirt
        colormaps.put(Material.COBBLESTONE, new int[] { 124, 124, 124 }); // Cobblestone
        colormaps.put(Material.OAK_PLANKS, new int[] { 165, 165, 165 }); // Oak Planks
        colormaps.put(Material.BEDROCK, new int[] { 255, 255, 255 }); // Bedrock
        colormaps.put(Material.SAND, new int[] { 226, 224, 38 }); // Sand
        colormaps.put(Material.GRAVEL, new int[] { 166, 126, 82 }); // Gravel
        colormaps.put(Material.GOLD_ORE, new int[] { 104, 83, 50 }); // Gold Ore
        colormaps.put(Material.IRON_ORE, new int[] { 119, 119, 119 }); // Iron Ore
        colormaps.put(Material.COAL_ORE, new int[] { 102, 102, 102 }); // Coal Ore
        colormaps.put(Material.OAK_WOOD, new int[] { 213, 213, 213 }); // Oak Wood
        colormaps.put(Material.OAK_LEAVES, new int[] { 195, 182, 157 }); // Leaves
        colormaps.put(Material.SPONGE, new int[] { 245, 220, 50 }); // Sponge
        colormaps.put(Material.GLASS, new int[] { 255, 0, 0 }); // Glass
        colormaps.put(Material.LAPIS_ORE, new int[] { 118, 118, 118 }); // Lapis Lazuli Ore
        colormaps.put(Material.LAPIS_BLOCK, new int[] { 0, 0, 255 }); // Lapis Lazuli Block
        colormaps.put(Material.DISPENSER, new int[] { 210, 180, 140 }); // Dispenser
        colormaps.put(Material.SANDSTONE, new int[] { 112, 112, 112 }); // Sandstone
        colormaps.put(Material.NOTE_BLOCK, new int[] { 255, 0, 0 }); // Note Block
        colormaps.put(Material.STICKY_PISTON, new int[] { 255, 255, 255 }); // Sticky Piston
        colormaps.put(Material.COBWEB, new int[] { 140, 140, 140 }); // Cobweb
    }

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
        ArrayList<int[]> imageinfo = img.GetImageProportions(image, Integer.parseInt(args[1]), Integer.parseInt(args[2]));


        for (int[] pixel : imageinfo) {
            int x = pixel[0];
            int y = pixel[1];
            int red = pixel[2];
            int green = pixel[3];
            int blue = pixel[4];
            
            LOGGER.info(String.format("(%d, %d, %d) at (%d, %d)", pixel[2], pixel[3], pixel[4], pixel[0], pixel[1]));
            for (Map.Entry<Material, int[]> entry : colormaps.entrySet()) {
                int[] rgbValues = entry.getValue();
                double distance = Math.sqrt(Math.pow(rgbValues[0] - red, 2) + Math.pow(rgbValues[1] - green, 2) + Math.pow(rgbValues[2] - blue, 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestBlock = entry.getKey();
                }
            }
                 
            Block block = playerLocation.getWorld().getBlockAt(((int)playerLocation.getX() + pixel[0]), ((int)playerLocation.getY()-1), ((int)playerLocation.getZ() + pixel[1]));
            block.setType(closestBlock);
        }

        return true;
    }
}
