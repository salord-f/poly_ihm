package fr.polytech.ihm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpController {
	@FXML
	private Button ok_button;

	@FXML
	private Button new_declaration_button;

	@FXML
	void new_declaration_button_clicked(MouseEvent event) {
		Stage stage = (Stage) new_declaration_button.getScene().getWindow();
		stage.close();
	}

	@FXML
	void ok_button_clicked(MouseEvent event) {
        String fxmlFile = "/fxml/viewIncidents.fxml";
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage stage = (Stage) ok_button.getScene().getWindow();

            Stage stage2 = (Stage) stage.getOwner();

            Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

            Scene scene = new Scene(rootNode);
            stage2.setScene(scene);
            stage.close();
            stage2.setTitle("Liste des déclarations");
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }/*

		try {
			Stage stage2 = (Stage) ok_button.getScene().getWindow();

			Stage stage = new Stage();


			Parent root = FXMLLoader.load(
					getClass().getResource("/fxml/viewIncidents.fxml"));
			Scene s = new Scene(root);
			stage.setScene(s);
			s.getStylesheets().add("/styles/styles.css");
			stage.setTitle("Confirmation de la déclaration");

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stage2);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
