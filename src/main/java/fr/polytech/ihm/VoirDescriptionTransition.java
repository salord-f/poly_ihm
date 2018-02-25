package fr.polytech.ihm;

import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * This class defined the transition when we press the "VoirDescription" button
 */
    public class VoirDescriptionTransition extends Transition {

        private Region region;
        private double startHeight;
        private double newHeight;
        private double heightDiff;
        private double maxHeigh;

    /**
     * Constructs a new VoirDescriptionTransition
     * @param duration The duration of the transiion
     * @param region The region to modify
     */
        public VoirDescriptionTransition(Duration duration, Region region) {
            setCycleDuration(duration);
            this.region = region;
            this.maxHeigh=region.getPrefHeight();
        }

    /**
     * Set up the parameters for the transition
     */
        public void setUp(){
            this.startHeight=region.getPrefHeight();
            if(startHeight==0){
                this.newHeight=maxHeigh;
            }
            else{
                this.newHeight=0;
            }
            this.heightDiff = newHeight - startHeight;
        }

    @Override
        protected void interpolate(double fraction) {
            region.setPrefHeight( startHeight + ( heightDiff * fraction ) );
        }
    }

