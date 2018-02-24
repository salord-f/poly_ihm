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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class IncidentController {
	Incident incident;

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
	private Label detailLieu2;

	@FXML
	private Label email;

	@FXML
	private Button voirImage;

	@FXML
	public void initialize(Incident incident) {
		//urgenceIcon.setImage(orange);
		this.incident = incident;
		fill(this.incident);
		infoSupp.setManaged(!infoSupp.isManaged());
		infoSupp.setVisible(!infoSupp.isVisible());
		voirDescription.setOnAction(event -> {
			infoSupp.setManaged(!infoSupp.isManaged());
			infoSupp.setVisible(!infoSupp.isVisible());
		});

		this.voirImage.addEventHandler(MouseEvent.MOUSE_PRESSED,
				e -> openImage());
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


		String locationDetail = incident.getLocationDetail();
		if(locationDetail.equals("")){
			this.detailLieu2.setText("Pas de détail pour le lieu");
		}else this.detailLieu2.setText("Détails du lieu : " + locationDetail);

		this.email.setText(incident.getEmail() + incident.getEmailDomain());

		String image = incident.getImage();
		if (image.equals("")) {
			voirImage.setVisible(false);
		}

		switch (incident.getEmergency()) {
			case LOW:
				showUrgency("images" + File.separator + "green.png", urgenceIcon);
				break;
			case MEDIUM:
				showUrgency("images" + File.separator + "orange.png", urgenceIcon);
				break;
			case HIGH:
				showUrgency("images" + File.separator + "red.png", urgenceIcon);
				break;
			default:
				break;
		}
	}

	private void showUrgency(String url, ImageView view) {
		try {
			Image image = new Image(url);
			view.setImage(image);
			view.setCache(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void openImage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			Stage stage = new Stage();
			Stage stage2 = (Stage) voirImage.getScene().getWindow();
			Parent root = loader.load(getClass().getResourceAsStream("/fxml/image.fxml")); //TODO
			Scene s = new Scene(root);
			stage.setScene(s);
			s.getStylesheets().add("/styles/styles.css");
			stage.setTitle("Image");
			((ImageController) loader.getController()).initialize(incident.getImage());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stage2);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
