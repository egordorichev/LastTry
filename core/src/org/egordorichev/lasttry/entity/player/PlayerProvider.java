package org.egordorichev.lasttry.entity.player;

import com.badlogic.gdx.graphics.Color;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerProvider {
    /**
     * Current supported player version
     */
    public static int CURRENT_VERSION = 0;

    /**
     * Returns all players in the "players/" directory
     *
     * @return all players in the "players/" directory
     */
    public static PlayerInfo[] getPlayers() {
        File playersDirectory = new File("players/");

        if (!playersDirectory.mkdir()) {
            try {
                playersDirectory.createNewFile();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }else{
            LastTry.warning("There's no worlds directory so one will be created!");
        }

        File[] files = playersDirectory.listFiles();
	    
        List<PlayerInfo> players = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                players.add(getPlayerInfo("players/" + files[i].getName()));
            }
        }

        return players.toArray(new PlayerInfo[0]);
    }

    /**
     * Returns player info from given file name
     *
     * @param fileName player file
     * @return player info
     */
    public static PlayerInfo getPlayerInfo(String fileName) {
        String playerName = fileName.replace("players/", "").replace(".plr", "");

        int version;
        int maxHp;
        int maxMana;
        PlayerType type;

        try {
            FileReader stream = new FileReader(fileName);

            version = stream.readInt32();

            maxHp = stream.readInt16();
            maxMana = stream.readInt16();

            switch (stream.readByte()) {
                case 0:
                default:
                    type = PlayerType.SOFTCORE;
                    break;
                case 1:
                    type = PlayerType.MEDIUMCORE;
                    break;
                case 2:
                    type = PlayerType.HARDCORE;
                    break;
            }

            stream.close();
        } catch (Exception exception) {
            LastTry.handleException(exception);
            return null;
        }

        return new PlayerInfo(playerName, maxHp, maxMana, type, version, new PlayerRenderInfo(1, Color.GREEN, Color
                .GRAY, Color.CORAL, 1, true)); // TODO: render info
    }

    public static Player load() {
        try {
            FileReader stream = new FileReader(getPath(LastTry.playerInfo.name));

            int version = stream.readInt32();

            if (version != CURRENT_VERSION) {
                throw new RuntimeException("Loading an unknow player version");
            }

            LastTry.playerInfo.maxHp = stream.readInt16();
            LastTry.playerInfo.maxMana = stream.readInt16();

            switch (stream.readByte()) {
                case 0:
                default:
                    LastTry.playerInfo.type = PlayerType.SOFTCORE;
                    break;
                case 1:
                    LastTry.playerInfo.type = PlayerType.MEDIUMCORE;
                    break;
                case 2:
                    LastTry.playerInfo.type = PlayerType.HARDCORE;
                    break;
            }

            stream.close();
        } catch (Exception exception) {
            LastTry.handleException(exception);
        }

        return new Player(LastTry.playerInfo);
    }

    public static PlayerInfo create(PlayerInfo info) {
        LastTry.playerInfo = info;

        save();

        return LastTry.playerInfo;
    }

    public static void save() {
        try {
            FileWriter stream = new FileWriter(getPath(LastTry.playerInfo.name));

            stream.writeInt32(CURRENT_VERSION);

            stream.writeInt16((short) LastTry.playerInfo.maxHp);
            stream.writeInt16((short) LastTry.playerInfo.maxMana);

            switch (LastTry.playerInfo.type) {
                case SOFTCORE:
                default:
                    stream.writeByte((byte) 0);
                    break;
                case MEDIUMCORE:
                    stream.writeByte((byte) 1);
                    break;
                case HARDCORE:
                    stream.writeByte((byte) 3);
                    break;
            }

            stream.close();
        } catch (Exception exception) {
            LastTry.handleException(exception);
        }
    }

    public static String getPath(String playerName) {
        return "players/" + playerName + ".plr";
    }
}