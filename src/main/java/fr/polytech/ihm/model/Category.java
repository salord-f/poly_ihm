package fr.polytech.ihm.model;

public enum Category {

    DYSFUNCTION("Dysfonctionnement"),
    CLEANLINESS("Propreté"),
    SHORTAGE("Manque"),
    MISC("Autre");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
