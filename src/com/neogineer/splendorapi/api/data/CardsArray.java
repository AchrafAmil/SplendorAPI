package com.neogineer.splendorapi.api.data;

import java.util.HashMap;

public class CardsArray {

    private HashMap<Color, Integer> mCards = new HashMap<>(6);

    public CardsArray() {
        for (Color color :
                Color.values()) {
            mCards.put(color, 0);
        }
    }

    public CardsArray(int red, int green, int blue, int black, int white, int gold) {
        mCards.put(Color.RED, red);
        mCards.put(Color.GREEN, green);
        mCards.put(Color.BLUE, blue);
        mCards.put(Color.BLACK, black);
        mCards.put(Color.WHITE, white);
        mCards.put(Color.GOLD, gold);
    }

    public int getCount(Color color){
        return mCards.get(color);
    }
}
