package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.Incident;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class IncidentController {

	@FXML
	private GridPane infoGlobals;

	@FXML
	private Label date;

	@FXML
	private Label categorie;

	@FXML
	private ImageView urgenceIcon;

	@FXML
	private ImageView lieuIcon;

	@FXML
	private Label titreIncident;

	@FXML
	private Button voirDescription;

	@FXML
	private GridPane infoSupp;

	@FXML
	private Label description;

	@FXML
	private Label detailLieu;

	@FXML
	private ImageView imageIncident;

	@FXML
	private Label emailTitle;

	@FXML
	private Label email;

	@FXML
	public void initialize(Incident incident) {
		//urgenceIcon.setImage(orange);

		fill(incident);
		infoSupp.setManaged(!infoSupp.isManaged());
		infoSupp.setVisible(!infoSupp.isVisible());
		voirDescription.setOnAction(event -> {
			infoSupp.setManaged(!infoSupp.isManaged());
			infoSupp.setVisible(!infoSupp.isVisible());
		});
	}

	private void fill(Incident incident) {
		this.date.setText(incident.getDate());

		String category = incident.getCategory().getName();
		if (category.equals("")) {
			this.categorie.setText("Pas de catégorie");
		} else this.categorie.setText(category);

		this.titreIncident.setText(incident.getTitle());

		String description = incident.getDescription();
		if (description.equals("")) {
			this.description.setText("Pas de description");
		} else this.description.setText(description);

		this.detailLieu.setText(incident.getLocation().getName());
		String detailLieu = incident.getLocation().getName();
		if (detailLieu.equals("")) {
			this.detailLieu.setText("Pas de details");
		} else this.detailLieu.setText(detailLieu);

		this.email.setText(incident.getEmail() + incident.getEmailDomain());

		String image = incident.getImage();
		if (!image.equals("")) {
			showImage(image, imageIncident);
		}

		switch (incident.getEmergency()) {
			case LOW:
				showImage("images/green.png", urgenceIcon);
				break;
			case MEDIUM:
				showImage("images/orange.png", urgenceIcon);
				break;
			case HIGH:
				showImage("images/red.png", urgenceIcon);
				break;
			default:
				break;
		}
	}

	private void showImage(String url, ImageView view) {
		try {
			Image image = new Image(url);
			view.setImage(image);
			view.setCache(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
