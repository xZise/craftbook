package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;

/**
 * Half substractor representing MC4110.
 * 
 * @author Fabian Neundorf
 */
public class HalfSubstractor extends AbstractIC {

    public HalfSubstractor(Server server, Sign block) {
        super(server, block);
    }

    @Override
    public String getTitle() {
        return "Half substractor";
    }

    @Override
    public String getSignTitle() {
        return "HALF SUBSTRACT";
    }

    @Override
    public void trigger(ChipState chip) {
        boolean m = chip.getInput(1);
        boolean s = chip.getInput(2);
        boolean d = m ^ s;
        boolean b = !m && s;
        chip.setOutput(0, d);
        chip.setOutput(1, b);
        chip.setOutput(2, b);
    }
    
    public static class Factory extends AbstractICFactory {

        public Factory(Server server) {
            super(server);
        }

        @Override
        public IC create(Sign sign) {
            return new HalfSubstractor(getServer(), sign);
        }
    }
    
}
