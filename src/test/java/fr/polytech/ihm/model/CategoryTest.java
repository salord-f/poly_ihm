package fr.polytech.ihm.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CategoryTest {

    @Test
    public void findACategory() {
        assertEquals(Category.DYSFUNCTION, Category.find("Dysfonctionnement"));
        assertEquals(Category.MISC, Category.find("Autre"));
        assertEquals(Category.NONE, Category.find(""));
    }

    @Test
    public void cannotFindACategory() {
        assertEquals(null, Category.find("test"));
        assertEquals(null, Category.find("1"));
    }
}
