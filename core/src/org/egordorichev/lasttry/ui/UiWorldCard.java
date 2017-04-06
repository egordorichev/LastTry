package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldInfo;

public class UiWorldCard extends UiCard {
    private WorldInfo info;

    public UiWorldCard(Rectangle rectangle, Origin origin, WorldInfo info) {
        super(rectangle, origin);

        this.info = info;
        this.addComponents();
    }

    public UiWorldCard(Rectangle rectangle, WorldInfo info) {
        this(rectangle, Origin.TOP_LEFT, info);
    }

    @Override
    public void addComponents() {
        if (this.info == null) {
            return;
        }

        clear();

        int x = this.getX();
        int y = this.getClickY();

        Texture texture;
        boolean hardmode = (this.info.flags & World.HARDMODE) == World.HARDMODE;

        if ((this.info.flags & World.CRIMSON) == World.CRIMSON) {
            if (hardmode) {
                texture = Assets.getTexture(Textures.crimsonHardmodeWorld);
            } else {
                texture = Assets.getTexture(Textures.crimsonWorld);
            }
        } else {
            if (hardmode) {
                texture = Assets.getTexture(Textures.corruptionHardmodeWorld);
            } else {
                texture = Assets.getTexture(Textures.corruptionWorld);
            }
        }

        add(new UiImage(new Rectangle(x, y, 60, 58), texture));
        add(new UiTextButton(new Rectangle(x + 64, y - 20, 0, 0), this.info.name));
        add(new UiTextLabel(new Rectangle(x + 64, y, 0, 0), this.info.size.toString()));
    }
}