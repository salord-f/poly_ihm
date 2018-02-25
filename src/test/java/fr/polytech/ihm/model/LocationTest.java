package fr.polytech.ihm.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void findLocation() {
        assertEquals(Location.PARKING, Location.find("Parking"));
        assertEquals(Location.NONE, Location.find(""));
        assertEquals(Location.O_BUILDING, Location.find("Bâtiment O"));
    }

    @Test
    public void cannotFindLocation() {
        assertEquals(null, Location.find("test"));
        assertEquals(null, Location.find("1"));
    }
}
