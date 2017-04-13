package org.egordorichev.lasttry.state;

import org.egordorichev.lasttry.entity.player.PlayerProvider;
import org.egordorichev.lasttry.world.WorldProvider;

/**
 * User will input either a new 'PlayerName' or a new 'WorldName', when starting the game.
 * Static methods to check user input against certain rules and returns a matching enum state.
 *
 * Created by Logotie on 13/04/2017.
 */
public class MenuInputStateHelper
{
    /**
     * NAMEALREADYEXISTS - Name already exists, either as a previously created World or Player name. Depending on context of input.
     * NAMEISBLANK - Name entered, contains no characters.
     * NAMEINPUTVALID - Name entered is valid, on the fact that it does satisfy any of the above rules.
     */
    public enum NameInputStates{ NAMEINPUTVALID, NAMEALREADYEXISTS, NAMEISBLANK }

    /**
     * Returns an appropriate state based on whether the player name matches any of the
     * 'NameInputState' enums.
     *
     * @param playerName String denoting player Name inputted by the user.
     * @return NameInputState enum denoting the state of the player name.
     */
    public static NameInputStates getStateOfInputPlayerName(String playerName)
    {
        //First check to see whether the inputted String is not empty
        if(playerName.isEmpty())
        {
            return NameInputStates.NAMEISBLANK;
        }

        //Check whether a player Info on the system exists with the inputted name
        if(PlayerProvider.doesPlayerInfoWithNameExist(playerName))
        {
            return NameInputStates.NAMEALREADYEXISTS;
        }

        return NameInputStates.NAMEINPUTVALID;
    }

    /**
     * Returns an appropriate state based on whether the world name matches any of the
     * 'NameInputState' enums.
     *
     * @param worldName String denoting world Name inputted by the user.
     * @return NameInputState enum denoting the state of the world name.
     */
    public static NameInputStates getStateOfInputWorldName(String worldName)
    {
        //Check to see if the inputted world name, is not empty.
        if(worldName.isEmpty()) {
            return NameInputStates.NAMEISBLANK;
        }

        //Check whether a World Info on the system exists with the inputted name
        if(WorldProvider.doesWorldWithNameExist(worldName)) {
            return NameInputStates.NAMEALREADYEXISTS;
        }

        return NameInputStates.NAMEINPUTVALID;
    }


}
