package net.fabricmc.MiniSolarSystems;

public enum PlanetFace {
    Top(0),
    Bottom(1),
    Left(2),
    Right(3),
    Front(4),
    Back(5);

    private final int value;
    private PlanetFace(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
