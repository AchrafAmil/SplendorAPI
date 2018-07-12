package com.neogineer.splendorapi.api.data;

import java.util.HashMap;
import java.util.Map;

public class TokensArray {

    // was an Array, now it's a Map, but things are abstract enough that it doesn't matter :-)
    private Map<Color, Integer> mTokens = new HashMap<>(6);

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

    /**
     * This is mainly used to parse cards' cost from a file
     * Please notice that gold is optional.
     *
     * @param input should match the pattern:
     *             "red green blue black white[ gold]"
     *             so something like "2 0 2 1 1"
     */
    public static TokensArray fromString(String input){
        String[] parts = input.split(" ");
        boolean hasGold = (parts.length>5 && parts[5].length()>0);
        return new TokensArray(Integer.valueOf(parts[0]),
                Integer.valueOf(parts[1]),
                Integer.valueOf(parts[2]),
                Integer.valueOf(parts[3]),
                Integer.valueOf(parts[4]),
                hasGold?
                        Integer.valueOf(parts[5]) : 0 ); // gold is optional
    }

    public int getCount(Color color){
        return mTokens.get(color);
    }

    @Override
    public String toString() {
        String result = "";
        for(Color color: Color.values()){
            result += color.name()+":"+mTokens.get(color)+" ";
        }
        return result;
    }
}
