package fr.polytech.ihm.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IncidentTest {


    @Test
    public void createAnEnumLocationAtItsCreation() {
        Incident i = new Incident("Dysfonctionnement", "Titre", "Description", "image", "Toilettes", "locationDetail", 1, "email", "@emai.fr", "25/01/2886");

        assertEquals(Location.TOILETS, i.getLocation());
    }

    @Test
    public void createEnumCategoryAtItsCreation() {
        Incident i = new Incident("Dysfonctionnement", "Titre", "Description", "image", "Toilettes", "locationDetail", 1, "email", "@emai.fr", "25/01/2886");

        assertEquals(Category.DYSFUNCTION, i.getCategory());
    }
}
