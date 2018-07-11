package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.List;

class GameTable {

    List<Noble> mNoblesDeck;
    List<Noble> mNobles;

    List<Card> mFirstCardsDeck;
    List<Card> mFirstCards;

    List<Card> mSecondCardsDeck;
    List<Card> mSecondCards;

    List<Card> mThirdCardsDeck;
    List<Card> mThirdCards;

    TokensArray mTokens;
}
