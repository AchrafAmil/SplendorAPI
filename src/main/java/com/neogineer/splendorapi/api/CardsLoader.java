package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.Color;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CardsLoader {

    List<Card> loadFirstLevelCards(){
        try {
            return loadCards("cards_first_level.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Card> loadSecondLevelCards(){
        try {
            return loadCards("cards_second_level.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Card> loadThirdLevelCards(){
        try {
            return loadCards("cards_third_level.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Card> loadCards(String fileName) throws FileNotFoundException {
        List<Card> cards = new ArrayList<>();

        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile());
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().length()==0      // ignore empty and commented lines
                    || line.startsWith("#"))
                continue;
            cards.add(parseCard(line, scanner.nextLine()));
        }

        return cards;
    }

    static private Card parseCard(String line1, String line2) {
        String[] parts1 = line1.split(" "); // something like "54 RED 2"
        int id = Integer.valueOf(parts1[0]);
        Color color = Color.valueOf(parts1[1]);
        int points = Integer.valueOf(parts1[2]);
        TokensArray cost = TokensArray.fromString(line2); // something like "1 0 1 3 0"

        return new Card(id, color, points, cost);
    }
}
