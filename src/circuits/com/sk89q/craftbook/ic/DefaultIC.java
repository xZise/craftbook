package com.sk89q.craftbook.ic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

public interface DefaultIC extends GenericFactoriable {

    public void init(Server server, Sign sign);
    
}
