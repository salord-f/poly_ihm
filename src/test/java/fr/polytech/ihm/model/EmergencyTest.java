package fr.polytech.ihm.model;

import fr.polytech.ihm.model.Emergency;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EmergencyTest {

    @Test
    public void returnTheGoodEmergency(){
        assertEquals(Emergency.HIGH, Emergency.values()[3]);
        assertEquals(Emergency.MEDIUM, Emergency.values()[2]);
        assertEquals(Emergency.LOW, Emergency.values()[1]);
        assertEquals(Emergency.NONE, Emergency.values()[0]);
    }
}
