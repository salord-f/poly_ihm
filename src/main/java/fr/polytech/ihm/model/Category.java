package fr.polytech.ihm.model;

/**
 * Enum all the category of incident
 */
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

    /**
     * Check if a string exists in the enum Category
     *
     * @param name string to check
     * @return the category if its found else return false
     */
    public static Category find(String name) {
        Category[] values = Category.values();
        for (Category i : values) {
            if (i.getName().equals(name))
                return i;
        }
        return null;

    }

    public String getName() {
        return name;
    }
}
