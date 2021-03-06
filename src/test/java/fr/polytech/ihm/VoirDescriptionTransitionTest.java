package fr.polytech.ihm;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VoirDescriptionTransitionTest {

    VoirDescriptionTransition transition;
    AnchorPane pane;

    @Before
    public void setUp() throws Exception {
        pane = new AnchorPane();
        pane.setPrefHeight(500);
        transition = new VoirDescriptionTransition(Duration.millis(500), pane);
    }

    @Test
    public void transitionTest() throws Exception {
        transition.setUp();
        transition.interpolate(1);
        assertTrue(pane.getPrefHeight() == 0);
        transition.setUp();
        transition.interpolate(1);
        assertTrue(pane.getPrefHeight() == 500);

    }

}