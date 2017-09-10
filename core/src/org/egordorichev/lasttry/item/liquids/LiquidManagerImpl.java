package org.egordorichev.lasttry.item.liquids;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;

public class LiquidManagerImpl implements LiquidManager {
    /**
     * Liquids textures
     */
    public TextureRegion[][][] textures;

    public void load() {
        textures = new TextureRegion[4][][];
        textures[0] = Assets.getTexture("lt_water").split(Block.SIZE, Block.SIZE);
        textures[1] = Assets.getTexture("lt_lava").split(Block.SIZE, Block.SIZE);
        textures[2] = Assets.getTexture("lt_honey").split(Block.SIZE, Block.SIZE);
        textures[3] = Assets.getTexture("lt_acid").split(Block.SIZE, Block.SIZE);
    }

    public void renderLiquid(int x, int y) {
        byte hp = Globals.getWorld().blocks.getHP(x, y);
        byte liquidLevel = BlockHelper.empty.getLiquidLevel(hp);

        if (liquidLevel > 0) {
            float light = Globals.getWorld().light.get(x, y);
            byte type = BlockHelper.empty.getLiquidType(hp);

            Graphics.batch.setColor(light, light, light, 1f);
            Graphics.batch.draw(textures[type][0][liquidLevel - 1], x * Block.SIZE, y * Block.SIZE);

            Block bottom = Globals.getWorld().blocks.get(x, y - 1);

            if (bottom == null) {
                byte bottomHp = Globals.getWorld().blocks.getHP(x, y - 1);
                byte bottomLiquidLevel = BlockHelper.empty.getLiquidLevel(bottomHp);

                if (bottomLiquidLevel < 15) { // FIXME: fit 16 somewhere?
                    liquidLevel -= 1;
                    hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
                    Globals.getWorld().blocks.setHP(hp, x, y);
                    bottomLiquidLevel += 1;
                    bottomHp = BlockHelper.empty.setLiquidLevel(bottomHp, bottomLiquidLevel);
                    Globals.getWorld().blocks.setHP(bottomHp, x, y - 1);
                    return;
                }
            }

            Block left = Globals.getWorld().blocks.get(x - 1, y);
            Block right = Globals.getWorld().blocks.get(x + 1, y);

            byte leftHp = Globals.getWorld().blocks.getHP(x - 1, y);
            byte leftLiquidLevel = BlockHelper.empty.getLiquidLevel(leftHp);
            byte rightHp = Globals.getWorld().blocks.getHP(x + 1, y);
            byte rightLiquidLevel = BlockHelper.empty.getLiquidLevel(rightHp);

            boolean toLeft = left == null && leftLiquidLevel < liquidLevel;
            boolean toRight = right == null && rightLiquidLevel < liquidLevel;

            if (toLeft && toRight) {
                toLeft = Math.random() > 0.5;
                toRight = !toLeft;
            }

            if (left == null && toLeft) {
                if (leftLiquidLevel < 15) { // FIXME: fit 16 somewhere?
                    liquidLevel -= 1;
                    hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
                    Globals.getWorld().blocks.setHP(hp, x, y);
                    leftLiquidLevel += 1;
                    leftHp = BlockHelper.empty.setLiquidLevel(leftHp, leftLiquidLevel);
                    Globals.getWorld().blocks.setHP(leftHp, x - 1, y);
                }
            }

            if (right == null && toRight) {
                if (rightLiquidLevel < 15) { // FIXME: fit 16 somewhere?
                    liquidLevel -= 1;

                    if (liquidLevel < 0) {
                        liquidLevel = 0;
                    }

                    hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
                    Globals.getWorld().blocks.setHP(hp, x, y);
                    rightLiquidLevel += 1;
                    rightHp = BlockHelper.empty.setLiquidLevel(rightHp, rightLiquidLevel);
                    Globals.getWorld().blocks.setHP(rightHp, x + 1, y);
                }
            }
        }
    }
}
