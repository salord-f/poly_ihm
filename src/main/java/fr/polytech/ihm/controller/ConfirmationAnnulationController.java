package fr.polytech.ihm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationAnnulationController {

	@FXML
	private Button YesButton;

	@FXML
	private Button NoButton;

	@FXML
	public void NoButtonAction(ActionEvent event) {
		Stage stage = (Stage) YesButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void YesButtonAction(ActionEvent event) { //TODO
        /*try {
            Stage stage = (Stage) YesButton.getScene().getWindow();

            Stage stage2 = (Stage) stage.getOwner();
            stage2.close();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/viewIncidents.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des incidents déclarés");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

		try {
			Stage stage2 = (Stage) YesButton.getScene().getWindow();

			Stage stage = new Stage();


			Parent root = FXMLLoader.load(
					getClass().getResource("/fxml/viewIncidents.fxml"));
			Scene s = new Scene(root);
			stage.setScene(s);
			s.getStylesheets().add("/styles/styles.css");
			stage.setTitle("Liste des incidents déclarés");

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stage2);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
