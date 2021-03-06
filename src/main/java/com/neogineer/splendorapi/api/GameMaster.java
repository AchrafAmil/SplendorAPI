package com.neogineer.splendorapi.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Main entry point for all players.
 *
 * Takes care of calling players and handling their transactions.
 *
 * Usage: create an instance, register players one by one, then call start().
 */
public class GameMaster {

    private Map<String, Player> mPlayers = new HashMap<>();
    private Map<String, PlayerState> mPlayerStates = new HashMap<>();
    private GameTable mGameTable;

    /**
     * should be called at the very beginning to register players.
     * @param player an instance that overrides {@link Player}
     * @throws IllegalArgumentException
     *          there might be a problem with the player's name uniqueness.
     * @throws IllegalStateException
     *          tried to register more than 4 players (remember, 2 to 4 players).
     */
    synchronized public void register(Player player){
        if(mPlayers.containsKey(player.playerName))
            throw new IllegalArgumentException("tried to register two players " +
                    "with the same name (or re-register the same player a second time)");
        if(mPlayers.size()>=4)
            throw new IllegalStateException("tried to register more than 4 players (remember, 2 to 4 players)");

        mPlayers.put(player.playerName, player);
        mPlayerStates.put(player.playerName, new PlayerState(player.playerName));

        player.setGameTable(new ReadOnlyGameTable(mGameTable));
        player.setMyState(new ReadOnlyPlayerState(
                mPlayerStates.get(player.playerName)));
    }

    synchronized public void start(){
        initializeGame();
        introducePlayersToEachOther();

    }

    private void initializeGame() {
        mGameTable = new GameTable(mPlayers.size());
        System.out.println("** GAME TABLE CREATED **\n"+mGameTable.toString());
    }

    private void introducePlayersToEachOther() {
        System.out.println("\n** INTRODUCING PLAYERS TO EACH OTHER **");
        for(Player player: mPlayers.values()){
            System.out.print("I'am "+player.playerName+" and I'm happy to play against: ");
            for(PlayerState opponent: mPlayerStates.values()){
                if(!opponent.playerName.equals(player.playerName)){
                    player.introduceOpponent(new ReadOnlyPlayerState(opponent));
                    System.out.print(opponent.playerName+" ");
                }
            }
            System.out.println();
        }
    }
}
