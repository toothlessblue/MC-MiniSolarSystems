package net.fabricmc.MiniSolarSystems;

public class Ecosystem {
    private float forestry;
    private float shrubbery;
    private float wildlife;

    private Planet planet;

    public Ecosystem(Planet planet) {
        this.planet = planet;
    }
}
