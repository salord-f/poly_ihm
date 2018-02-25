package fr.polytech.ihm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmationAnnulationController {

	@FXML
	private Button YesButton;

	@FXML
	private Button NoButton;

	@FXML
	public void NoButtonAction(ActionEvent event) {
		Stage stage = (Stage) NoButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void YesButtonAction(ActionEvent event) {
		String fxmlFile = "/fxml/viewIncidents.fxml";
		FXMLLoader loader = new FXMLLoader();
		try {
			Stage stage = (Stage) YesButton.getScene().getWindow();

			Stage stage2 = (Stage) stage.getOwner();

			Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

			Scene scene = new Scene(rootNode);
			stage2.setScene(scene);
			scene.getStylesheets().add("/styles/styles.css");
			stage.close();
			stage2.setTitle("Liste des déclarations");
			stage2.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
