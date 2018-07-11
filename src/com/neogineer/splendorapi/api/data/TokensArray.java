package com.neogineer.splendorapi.api.data;

import java.util.HashMap;

public class TokensArray {

    private HashMap<Color, Integer> mTokens = new HashMap<>(6);

    public TokensArray() {
        for (Color color :
                Color.values()) {
            mTokens.put(color, 0);
        }
    }

    public TokensArray(int red, int green, int blue, int black, int white, int gold) {
        mTokens.put(Color.RED, red);
        mTokens.put(Color.GREEN, green);
        mTokens.put(Color.BLUE, blue);
        mTokens.put(Color.BLACK, black);
        mTokens.put(Color.WHITE, white);
        mTokens.put(Color.GOLD, gold);
    }

    public int getCount(Color color){
        return mTokens.get(color);
    }
}
