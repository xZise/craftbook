package com.sk89q.craftbook.gates.world;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.DefaultIC;
import com.sk89q.craftbook.ic.RisingEdgeIC;

public abstract class AbstractRisingEdgeIC extends AbstractIC implements DefaultIC, RisingEdgeIC {

    protected boolean risingEdge;
    
    @Override
    public void init(Server server, Sign sign, boolean risingEdge) {
        this.init(server, sign);
        this.risingEdge = risingEdge;
    }
}
