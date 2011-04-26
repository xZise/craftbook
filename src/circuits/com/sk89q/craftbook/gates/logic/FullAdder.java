package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;

public class FullAdder extends AbstractIC {

    public FullAdder(Server server, Sign block) {
        super(server, block);
    }

    @Override
    public String getTitle() {
        return "Full adder";
    }

    @Override
    public String getSignTitle() {
        return "FULL ADDER";
    }

    @Override
    public void trigger(ChipState chip) {
        boolean x = chip.getInput(0);
        boolean y = chip.getInput(1);
        boolean cIn = chip.getInput(2);
        boolean s = x ^ y ^ cIn;
        boolean cOut = (y && cIn) || (x && cIn) || (x && y);
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
            return new FullAdder(getServer(), sign);
        }
    }
    
}
