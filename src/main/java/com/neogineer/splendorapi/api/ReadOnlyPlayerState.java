package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.CardsArray;
import com.neogineer.splendorapi.api.data.TokensArray;

public class ReadOnlyPlayerState {

    private final PlayerState mPlayerState;

    public ReadOnlyPlayerState(PlayerState mPlayerState) {
        this.mPlayerState = mPlayerState;
    }

    public TokensArray getTokens(){
        return mPlayerState.mTokens.copy();
    }

    public CardsArray getCards(){
        return mPlayerState.mCards.copy();
    }
}
