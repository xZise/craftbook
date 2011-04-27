package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;

/**
 * Half adder representing MC4010.
 * 
 * @author Fabian Neundorf
 */
public class HalfAdder extends AbstractIC {

    public HalfAdder(Server server, Sign block) {
        super(server, block);
    }

    @Override
    public String getTitle() {
        return "Half adder";
    }

    @Override
    public String getSignTitle() {
        return "HALF ADDER";
    }

    @Override
    public void trigger(ChipState chip) {
        boolean x = chip.getInput(1);
        boolean y = chip.getInput(2);
        boolean s = x ^ y;
        boolean cOut = x && y;
        chip.setOutput(0, s);
        chip.setOutput(1, cOut);
        chip.setOutput(2, cOut);
    }

    public static class Factory extends AbstractICFactory {

        public Factory(Server server) {
            super(server);
        }

        @Override
        public IC create(Sign sign) {
            return new HalfAdder(getServer(), sign);
        }
    }
    
}
