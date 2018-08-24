package com.cube.rpg.simplemon.command;

import com.cube.rpg.simplemon.util.SimpleMonsterBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class SimpleMonsterCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equals("spm") && sender.isOp()) {
            if(args.length == 3 && args[0].equals("create")) {
                EntityType type = EntityType.valueOf(args[1]);
                String name = args[2];
                SimpleMonsterBuilder builder = new SimpleMonsterBuilder(name);
                builder.setEntityType(type);

            }
        }

        return false;
    }

}
