package org.egordorichev.lasttry.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ItemManagerImpl implements ItemManager{

    private static final Logger logger = LoggerFactory.getLogger(ItemManagerImpl.class);
    /**
     * Items storage
     */
    public HashMap<String, Item> ITEM_CACHE = new HashMap<>();

    /**
     * Loads items
     */
    @Override
    public void load(){
        try {
            JsonReader jsonReader = new JsonReader();
            JsonValue root = jsonReader.parse(Gdx.files.internal("data/items.json"));

            for (JsonValue item : root) {
                try {
                    ITEM_CACHE.put(item.name(), Item.load(item));
                } catch (Exception exception) {
                    logger.error(exception.getMessage());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Failed to load items");
        }
    }

    @Override
    public boolean hasItem(String id) {
        return ITEM_CACHE.containsKey(id);
    }

    @Override
    public Item getItem(String id) {
        return ITEM_CACHE.get(id);
    }

    @Override
    public Item addItem(String id, Item item) {
        return ITEM_CACHE.put(id,item);
    }
}
