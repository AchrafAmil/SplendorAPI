package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NoblesLoader {

    List<Noble> loadNobles(){
        try {
            return loadNobles("nobles.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Noble> loadNobles(String fileName) throws FileNotFoundException {
        List<Noble> nobles = new ArrayList<>();

        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile());
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().length()==0      // ignore empty and commented lines
                    || line.startsWith("#"))
                continue;
            nobles.add(parseNoble(line, scanner.nextLine()));
        }
        Collections.shuffle(nobles);
        return nobles;
    }

    static private Noble parseNoble(String line1, String line2) {
        String[] parts1 = line1.split(" "); // something like "54 2"
        int id = Integer.valueOf(parts1[0]);
        int points = Integer.valueOf(parts1[1]);
        TokensArray cost = TokensArray.fromString(line2); // something like "1 0 1 3 0"

        return new Noble(id, points, cost);
    }
}
