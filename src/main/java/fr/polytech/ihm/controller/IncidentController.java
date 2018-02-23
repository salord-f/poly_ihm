package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.Incident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class IncidentController {

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
	private Label email;

	@FXML
	private Button voirImage;

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
		if (image.equals("")) {
			voirImage.setVisible(false);
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

		this.voirImage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
				e -> {
					openImage();
				});
	}


	private void showImage(String url, ImageView view) {
		try {
			File file = new File(url);
			Image image = new Image(file.toURI().toString());
			view.setImage(image);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void openImage() {
		try {
			Stage stage = new Stage();

			Stage stage2 = (Stage) voirImage.getScene().getWindow();

			Parent root = FXMLLoader.load(
					getClass().getResource("/fxml/image.fxml")); //TODO
			Scene s = new Scene(root);
			stage.setScene(s);
			s.getStylesheets().add("/styles/styles.css");
			stage.setTitle("Image");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stage2);
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
