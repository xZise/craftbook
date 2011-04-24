package com.sk89q.craftbook.gates.logic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.SelfTriggeredIC;

public class PeakGate extends AbstractIC implements SelfTriggeredIC {
    
    private final boolean rising;
    private final boolean falling;
    private final int ticks;

    private Boolean previous;
    private int remainingTick;
    
    public PeakGate(Server server, Sign block) {
        super(server, block);
        
        boolean rising = false;
        boolean falling = false;
        
        String edges = block.getLine(3);
        for (char c : edges.toCharArray()) {
            switch (c) {
            case 'r' :
            case 'R' :
                rising = true;
                break;
            case 'f' :
            case 'F' :
                falling = true;
                break;
            default:
                break;
            }
        }
        
        this.rising = rising;
        this.falling = falling;
        
        int ticks;
        try {
            ticks = Integer.getInteger(block.getLine(4));
        } catch (NumberFormatException e) {
            ticks = 1;
        }
        this.ticks = ticks;
        
        this.remainingTick = 0;
    }

    @Override
    public String getTitle() {
        return "Peak Gate";
    }

    @Override
    public String getSignTitle() {
        return "PEAK";
    }

    @Override
    public void trigger(ChipState chip) {
        if (this.previous != null) {
            boolean change = chip.getInput(0) ^ this.previous;
            if (change) {
                if (this.previous) {
                    if (this.falling) {
                        // Falling edge
                        chip.setOutput(0, true);
                        this.remainingTick = this.ticks;
                    }
                } else {
                    if (this.rising) {
                        // Rising edge
                        chip.setOutput(0, true);
                        this.remainingTick = this.ticks;
                    }
                }
                this.previous = chip.getInput(0);
            }
            if (this.remainingTick > 0) {
                this.remainingTick--;
            } else {
                chip.setOutput(0, false);
            }
        } else {
            this.previous = chip.getInput(0);
            chip.setOutput(0, false);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void think(ChipState state) {
        this.trigger(state);
    }

}
