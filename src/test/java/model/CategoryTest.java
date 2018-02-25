package model;

import fr.polytech.ihm.model.Category;
import org.apache.commons.lang.ObjectUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CategoryTest {

    @Test
    public void findACategory(){
        assertEquals(Category.DYSFUNCTION,Category.find("Dysfonctionnement"));
        assertEquals(Category.MISC, Category.find("Autre"));
        assertEquals(Category.NONE, Category.find(""));
    }

    @Test
    public void cannotFindACategory(){
        assertEquals(null,Category.find("test"));
        assertEquals(null,Category.find("1"));
    }
}
