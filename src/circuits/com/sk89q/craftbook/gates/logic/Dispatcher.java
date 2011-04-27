package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;

/**
 * Dispatcher representing MC4200.
 * 
 * @author Fabian Neundorf
 */
public class Dispatcher extends AbstractIC {

    public Dispatcher(Server server, Sign block) {
        super(server, block);
    }

    @Override
    public String getTitle() {
        return "Dispatcher";
    }

    @Override
    public String getSignTitle() {
        return "DISPATCHER";
    }

    @Override
    public void trigger(ChipState chip) {
        if (chip.get(1)) {
            chip.setOutput(1, chip.get(0));
        }
        
        if (chip.get(2)) {
            chip.setOutput(2, chip.get(0));
        }
    }

    public static class Factory extends AbstractICFactory {
        public Factory(Server server) {
            super(server);
        }
        
        @Override
        public IC create(Sign sign) {
            return new Dispatcher(getServer(), sign);
        }
    }
}
