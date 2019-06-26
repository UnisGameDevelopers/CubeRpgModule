package com.cube.rpg.simplemon.command;

import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import net.minecraft.server.v1_5_R3.EntityLiving;
import net.minecraft.server.v1_5_R3.Packet24MobSpawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftSpider;
import org.bukkit.entity.*;

public class SimpleMonsterCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("spm") && sender.isOp()) {
            if(args.length == 3 && args[0].equals("create")) {
                EntityType type = EntityType.valueOf(args[1]);
                String name = args[2];
                SimpleMonsterBuilder builder = new SimpleMonsterBuilder(name);
                builder.setEntityType(type);

            }else if(args.length == 1 && args[0].equals("test")) {
                Entity entity = ((Player) sender).getLocation().getWorld().spawnEntity(
                        ((Player) sender).getLocation(), EntityType.SPIDER
                );

                EntityLiving entityLiving = ((CraftLivingEntity) entity).getHandle();

                Packet24MobSpawn packet24MobSpawn = new Packet24MobSpawn(entityLiving);
                packet24MobSpawn.b = EntityType.PIG.getTypeId();

                Player player = (Player) sender;
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet24MobSpawn);
            }
        }

        return false;
    }

}
