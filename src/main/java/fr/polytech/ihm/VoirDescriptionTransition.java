package fr.polytech.ihm;

import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class VoirDescriptionTransition extends Transition {

	private Region region;
	private double startHeight;
	private double newHeight;
	private double heightDiff;
	private double maxHeigh;

	public VoirDescriptionTransition(Duration duration, Region region) {
		setCycleDuration(duration);
		this.region = region;
		this.maxHeigh = region.getPrefHeight();
	}

	public void setUp() {
		this.startHeight = region.getPrefHeight();
		if (startHeight == 0) this.newHeight = maxHeigh;
		else this.newHeight = 0;
		this.heightDiff = newHeight - startHeight;
	}

	@Override
	protected void interpolate(double fraction) {
		region.setPrefHeight(startHeight + (heightDiff * fraction));
	}
}

