package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;

/**
 * Full substractor representing MC4100.
 * 
 * @author Fabian Neundorf
 */
public class FullSubstractor extends AbstractIC {

    public FullSubstractor(Server server, Sign block) {
        super(server, block);
    }

    @Override
    public String getTitle() {
        return "Full substractor";
    }

    @Override
    public String getSignTitle() {
        return "FULL SUBSTRACT";
    }

    @Override
    public void trigger(ChipState chip) {
        boolean x = chip.getInput(0);
        boolean y = chip.getInput(1);
        boolean z = chip.getInput(2);
        boolean d = x ^ y ^ z;
        boolean b = z && !(x ^ y) || (!x && y);
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
            return new FullSubstractor(getServer(), sign);
        }
    }
    
}
