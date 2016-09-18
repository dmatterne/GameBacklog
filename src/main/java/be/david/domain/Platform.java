package be.david.domain;

/**
 * Created by David on 16/09/2016.
 */
public enum Platform {
    PC("PC"),
    STEAM("STEAM"),
    PLAYSTATION_3("PS 3"),
    PLAYSTATION_4("PS 4"),
    XBOX_360("XBOX 360"),
    XBOX_ONE("XBOX ONE"),
    PS_VITA("PS VITA"),
    DS3("3DS XL"),
    DS("DS"),
    WII("WII"),
    WIIU("WII U");

    private final String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
