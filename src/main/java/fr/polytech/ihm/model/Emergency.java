package fr.polytech.ihm.model;

public enum Emergency {

    NONE("Aucune"),
    LOW("Faible"),
    MEDIUM("Moyenne"),
    HIGH("Forte");

    private String name;

    Emergency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
