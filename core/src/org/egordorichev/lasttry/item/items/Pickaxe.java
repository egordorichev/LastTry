package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;

public class Pickaxe extends Tool {
    public Pickaxe(short id, String name, Rarity rarity, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, pickaxePower, 0, 0, useSpeed, texture);
    }

    public Pickaxe(short id, String name, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
        this(id, name, Rarity.WHITE, baseDamage, pickaxePower, useSpeed, texture);
    }

    @Override
    public boolean onUse() {
        LastTry.world.setBlock((short) 0, LastTry.getMouseXInWorld() / Block.TEX_SIZE, LastTry.getMouseYInWorld() / Block.TEX_SIZE);

        return false;
    }
}