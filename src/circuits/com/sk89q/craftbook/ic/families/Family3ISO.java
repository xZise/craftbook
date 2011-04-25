// $Id$
/*
 * Copyright (C) 2010, 2011 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.craftbook.ic.families;

import com.sk89q.craftbook.ic.AbstractConstantChipState;
import com.sk89q.craftbook.ic.AbstractICFamily;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.util.BlockWorldVector;
import com.sk89q.craftbook.util.SignUtil;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

/**
 * Handles detection for the triple-input single-output family.
 * 
 * @author robhol
 */
public class Family3ISO extends AbstractICFamily {

    @Override
    public ChipState detect(BlockWorldVector source, Sign sign) {
        return new ChipState3ISO(source, sign);
    }

    public static class ChipState3ISO extends AbstractConstantChipState {

        public ChipState3ISO(BlockWorldVector source, Sign sign) {
            super(source, sign, 3, 1);
        }

        protected Block getBlock(int pin) {
            switch (pin) {
                case 0:
                    return SignUtil.getFrontBlock(sign.getBlock());
                case 1:
                    return SignUtil.getLeftBlock(sign.getBlock());
                case 2:
                    return SignUtil.getRightBlock(sign.getBlock());
                case 3:
                    BlockFace face = SignUtil.getBack(sign.getBlock());
                    return sign.getBlock().getRelative(face).getRelative(face);
                default:
                    return null;
            }
        }
    }
}
