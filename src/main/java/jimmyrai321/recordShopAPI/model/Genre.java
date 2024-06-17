package jimmyrai321.recordShopAPI.model;

import java.util.Arrays;

public enum Genre {
    COUNTRY,
    ELECTRONIC,
    RNB,
    HOUSE,
    DISCO,
    EDM,
    JAZZ,
    METAL,
    HIPHOP,
    LATIN,
    DUBSTEP,
    REGGAE,
    ROCK,
    POP,
    INDIE;

    public static boolean isValid(String name) {
        return Arrays.stream(values()).anyMatch(e -> e.name().equals(name));
    }
}
