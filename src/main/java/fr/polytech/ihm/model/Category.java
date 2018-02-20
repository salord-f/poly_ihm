package fr.polytech.ihm.model;

public enum Category {

    DYSFUNCTION("Dysfonctionnement"),
    CLEANLINESS("Propreté"),
    SHORTAGE("Manque"),
    MISC("Autre"),
    NONE("");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category find(String name) {
        Category[] values = Category.values();
        for (Category i : values) {
            if (i.getName().equals(name))
                return i;
        }
        return null;

    }
}
