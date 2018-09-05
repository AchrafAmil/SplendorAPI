package com.neogineer.splendorapi.api.data;

import java.util.HashMap;

public class CardsArray extends TokensArray {

    private HashMap<Color, Integer> mCards = new HashMap<>(6);

    public CardsArray() {
        super();
    }

    public CardsArray(int red, int green, int blue, int black, int white) {
        super(red, green, blue, black, white, 0);
    }

    public int getCount(Color color){
        return mCards.get(color);
    }

    @Override
    public TokensArray copy() {
        return super.copy();
    }
}
