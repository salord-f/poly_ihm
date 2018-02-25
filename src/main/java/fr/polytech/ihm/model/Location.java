package fr.polytech.ihm.model;
/**
 * Enum all the location possible for an incident
 */
public enum Location {

    PARKING("Parking"),
    TOILETS("Toilettes"),
    STUDENT_COMMON_ROOM("Foyer"),
    O_BUILDING("Bâtiment O"),
    E_BUILDING("Bâtiment E"),
    OTHER("Autre"),
    NONE("");

    private String name;

    Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Check if a string exists in the enum Location
     * @param name string to check
     * @return the location if its found else return false
     */
    public static Location find(String name) {
        Location[] values = Location.values();
        for (Location i : values) {
            if (i.getName().equals(name))
                return i;
        }
        return null;

    }
}
