package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.entity.player.skin.PlayerRenderer;

public class UiPlayerCard extends UiCard {
    /**
     * Where is the all player data is stored
     */
    private PlayerInfo info;

    public UiPlayerCard(Rectangle rectangle, Origin origin, PlayerInfo info) {
        super(rectangle, origin);

        this.info = info;
    }

    @Override
    public void addComponents() {
        if (this.info == null) {
            return;
        }

        int x = this.getX();
        int y = this.getClickY();

        add(new UiTextureRegion(new Rectangle(x, y + 24, 32, 48),
                new TextureRegion(PlayerRenderer.generateTexture(this.info.renderInfo), 4, 8, 32, 48))); // Player icon

        add(new UiTextButton(new Rectangle(x + 64, y, 0, 0), this.info.name)); // Player name

        add(new UiTextLabel(new Rectangle(x + 64, y + 32, 0, 0), "Max HP " + this.info.maxHp
                + " Max Mana " + this.info.maxMana)); // Stats
    }

    @Override
    protected void onStateChange() {
        super.onStateChange();

        if (this.state == State.MOUSE_IN) {
            // TODO
        } else if (this.state == State.NORMAL) {
            // TODO
        }
    }
}