package com.imgl;

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
    HashMap<Material, int[]> colormaps = new HashMap<>();

    public ImgloadCmd() {
        colormaps.put(Material.STONE, new int[] { 142, 116, 84 });
        colormaps.put(Material.GRASS_BLOCK, new int[] { 255, 255, 255 });
        colormaps.put(Material.DIRT, new int[] { 191, 191, 191 });
        colormaps.put(Material.COBBLESTONE, new int[] { 124, 124, 124 }); 
        colormaps.put(Material.OAK_PLANKS, new int[] { 165, 165, 165 });
        colormaps.put(Material.BEDROCK, new int[] { 255, 255, 255 }); 
        colormaps.put(Material.SAND, new int[] { 226, 224, 38 }); 
        colormaps.put(Material.GRAVEL, new int[] { 166, 126, 82 }); 
        colormaps.put(Material.GOLD_ORE, new int[] { 104, 83, 50 });
        colormaps.put(Material.IRON_ORE, new int[] { 119, 119, 119 });
        colormaps.put(Material.COAL_ORE, new int[] { 102, 102, 102 });
        colormaps.put(Material.OAK_WOOD, new int[] { 213, 213, 213 }); 
        colormaps.put(Material.OAK_LEAVES, new int[] { 195, 182, 157 }); 
        colormaps.put(Material.SPONGE, new int[] { 245, 220, 50 }); 
        colormaps.put(Material.GLASS, new int[] { 255, 0, 0 });
        colormaps.put(Material.LAPIS_ORE, new int[] { 118, 118, 118 });
        colormaps.put(Material.LAPIS_BLOCK, new int[] { 0, 0, 255 });
        colormaps.put(Material.DISPENSER, new int[] { 210, 180, 140 }); 
        colormaps.put(Material.SANDSTONE, new int[] { 112, 112, 112 }); 
        colormaps.put(Material.NOTE_BLOCK, new int[] { 255, 0, 0 });
        colormaps.put(Material.STICKY_PISTON, new int[] { 255, 255, 255 }); 
        colormaps.put(Material.COBWEB, new int[] { 140, 140, 140 }); 
        colormaps.put(Material.WHITE_WOOL, new int[] { 221, 221, 221 });
        colormaps.put(Material.ORANGE_WOOL, new int[] { 233, 126, 55 });
        colormaps.put(Material.MAGENTA_WOOL, new int[] { 179, 75, 182 });
        colormaps.put(Material.LIGHT_BLUE_WOOL, new int[] { 103, 137, 211 });
        colormaps.put(Material.YELLOW_WOOL, new int[] { 214, 197, 51 });
        colormaps.put(Material.LIME_WOOL, new int[] { 96, 166, 24 });
        colormaps.put(Material.PINK_WOOL, new int[] { 214, 101, 143 });
        colormaps.put(Material.GRAY_WOOL, new int[] { 65, 68, 73 });
        colormaps.put(Material.LIGHT_GRAY_WOOL, new int[] { 153, 153, 153 });
        colormaps.put(Material.CYAN_WOOL, new int[] { 21, 119, 136 });
        colormaps.put(Material.PURPLE_WOOL, new int[] { 126, 61, 181 });
        colormaps.put(Material.BLUE_WOOL, new int[] { 46, 56, 141 });
        colormaps.put(Material.BROWN_WOOL, new int[] { 79, 50, 31 });
        colormaps.put(Material.GREEN_WOOL, new int[] { 53, 70, 27 });
        colormaps.put(Material.RED_WOOL, new int[] { 150, 52, 48 });
        colormaps.put(Material.BLACK_WOOL, new int[] { 25, 22, 22 });
        colormaps.put(Material.WHITE_CONCRETE, new int[] { 208, 212, 214 });
        colormaps.put(Material.ORANGE_CONCRETE, new int[] { 232, 118, 30 });
        colormaps.put(Material.MAGENTA_CONCRETE, new int[] { 160, 58, 160 });
        colormaps.put(Material.LIGHT_BLUE_CONCRETE, new int[] { 90, 118, 144 });
        colormaps.put(Material.YELLOW_CONCRETE, new int[] { 225, 189, 44 });
        colormaps.put(Material.LIME_CONCRETE, new int[] { 106, 137, 44 });
        colormaps.put(Material.PINK_CONCRETE, new int[] { 218, 132, 153 });
        colormaps.put(Material.GRAY_CONCRETE, new int[] { 60, 67, 71 });
        colormaps.put(Material.LIGHT_GRAY_CONCRETE, new int[] { 151, 151, 151 });
        colormaps.put(Material.CYAN_CONCRETE, new int[] { 22, 127, 125 });
        colormaps.put(Material.PURPLE_CONCRETE, new int[] { 122, 42, 123 });
        colormaps.put(Material.BLUE_CONCRETE, new int[] { 50, 60, 143 });
        colormaps.put(Material.BROWN_CONCRETE, new int[] { 96, 60, 32 });
        colormaps.put(Material.GREEN_CONCRETE, new int[] { 55, 76, 24 });
        colormaps.put(Material.RED_CONCRETE, new int[] { 143, 32, 33 });
        colormaps.put(Material.BLACK_CONCRETE, new int[] {8, 10, 15});
    }

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
            int z = pixel[1];
            int red = pixel[2];
            int green = pixel[3];
            int blue = pixel[4];

            double minDistance = Double.MAX_VALUE;
            Material closestBlock = null;
            for (HashMap.Entry<Material, int[]> entry : colormaps.entrySet()) {
                int[] rgbValues = entry.getValue();
                double distance = Math.sqrt(Math.pow(rgbValues[0] - red, 2) + Math.pow(rgbValues[1] - green, 2) + Math.pow(rgbValues[2] - blue, 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestBlock = entry.getKey();
                }
            }

            if (closestBlock != null) {
                Block block = playerLocation.getWorld().getBlockAt((int)playerLocation.getX() + (x / 2), (int)playerLocation.getY() - 1, ((int)playerLocation.getZ() + (z / 2)));
                block.setType(closestBlock);
            }
        }

        return true;
    }
}
