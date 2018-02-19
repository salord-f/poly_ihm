package fr.polytech.ihm.controller;

import fr.polytech.ihm.JsonManager;
import fr.polytech.ihm.MainApp;
import fr.polytech.ihm.model.Category;
import fr.polytech.ihm.model.Incident;
import fr.polytech.ihm.model.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DeclarationController {

	private static final Logger log = LoggerFactory.getLogger(MainApp.class);

	Incident incident;

	String calendar;


	@FXML
	private Text declarationTitle;

	@FXML
	private Text categorieLabel;


	private String categorieConvert = "";
	@FXML
	private ComboBox<String> categorie;

	@FXML
	private Text titreLabel;


	private String titreConvert = "";
	@FXML
	private TextField titre;


	private String descriptionConvert = "";
	@FXML
	private TextArea description;


	private String joinConvert = ""; //URL
	@FXML
	private Button join;

	private String localizationConvert = "";
	@FXML
	private ComboBox<String> localization;


	private String localizationDetailConvert = "";
	@FXML
	private TextField localizationDetail;


	private int urgenceState;
	@FXML
	private RadioButton urgenceAucune;

	@FXML
	private RadioButton urgenceFaible;

	@FXML
	private RadioButton urgenceMoyenne;

	@FXML
	private RadioButton urgenceForte;


	private String emailConvert = "";
	@FXML
	private TextField email;


	private String emaileDomaineConvert = "";
	@FXML
	private ComboBox<String> emaileDomaine;

	@FXML
	private Button envoyer;

	@FXML
	private Button retour;

	@FXML
	void joinAction(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(join.getScene().getWindow());
			if(!file.getName().equals("")){
				Files.copy(file.toPath(), new File("src/main/resources/images" + file.getName()).toPath());
				joinConvert = "src/main/resources/images" + file.getName();
			}

			/*String file = "." + File.separator;
			Path currentDirectory = null;
			if (!URL.getName().equals("")) {
				currentDirectory = new File(file + URL.getName()).toPath();
			}

			try {

			} catch (IOException e) {
				e.printStackrace();
			}*/

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@FXML
	void RetourAction(ActionEvent event) {
		retrieveData();
		if (!categorieConvert.equals("") ||
				!titreConvert.equals("") ||
				!descriptionConvert.equals("") ||
				!joinConvert.equals("") ||
				!localizationConvert.equals("") ||
				!emailConvert.equals("") ||
				!emaileDomaineConvert.equals("")) {
			try {
				Stage stage = new Stage();

				Stage stage2 = (Stage) join.getScene().getWindow();

				Parent root = FXMLLoader.load(
						getClass().getResource("/fxml/confirmationAnnulation.fxml")); //TODO
				stage.setScene(new Scene(root));
				stage.setTitle("Error");
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(stage2);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				Stage stage = (Stage) join.getScene().getWindow();

				Parent root = FXMLLoader.load(
						getClass().getResource("/fxml/viewIncidents.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("ViewIncident");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void envoyerAction(ActionEvent event) {

		retrieveData();

		if (incident.checkInput()) {
			this.clear();
			JsonManager jsonManager = new JsonManager();
			jsonManager.writeJson(incident);
			try {
				Stage stage2 = (Stage) join.getScene().getWindow();

				Stage stage = new Stage();


				Parent root = FXMLLoader.load(
						getClass().getResource("/fxml/popupConfirmation.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("confirmation");

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(stage2);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Stage stage = new Stage();

				Stage stage2 = (Stage) join.getScene().getWindow();

				Parent root = FXMLLoader.load(
						getClass().getResource("/fxml/incorrectDeclaration.fxml")); //TODO
				stage.setScene(new Scene(root));
				stage.setTitle("Error");
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(stage2);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void retrieveData() {
		//categorie.getSelectionModel().selectedItemProperty();

		categorieConvert = categorie.getValue();
		if (categorieConvert == null) {
			categorieConvert = "";
		}

		titreConvert = titre.getText();
		descriptionConvert = description.getText();

		localizationConvert = localization.getValue();
		if (localizationConvert == null) {
			localizationConvert = "";
		}

		localizationDetailConvert = localizationDetail.getText(); //TODO

		if (urgenceAucune.isSelected()) {
			urgenceState = 0;
		} else if (urgenceFaible.isSelected()) {
			urgenceState = 1;
		} else if (urgenceMoyenne.isSelected()) {
			urgenceState = 2;
		} else if (urgenceForte.isSelected()) {
			urgenceState = 3;
		}
		emailConvert = email.getText();
		emaileDomaineConvert = emaileDomaine.getValue();
		if (emaileDomaineConvert == null) {
			emaileDomaineConvert = "";
		}

		DateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 1);

    @FXML
    public void initialize()
    {
        ObservableList<String> categorieList = FXCollections.observableArrayList();
        for (int i = 0; i < Category.values().length; i++) categorieList.add(Category.values()[i].getName());
        categorie.setItems(categorieList);

        ObservableList<String> localizationList = FXCollections.observableArrayList();
        for (int i = 0; i < Location.values().length; i++) localizationList.add(Location.values()[i].getName());
        localization.setItems(localizationList);

	@FXML
	public void initialize() {
		ObservableList<String> categorieList =
				FXCollections.observableArrayList(
						"Incident",
						"Requête",
						"J'ai pas d'idées",
						"Autre"
				);
		categorie.setItems(categorieList);

		ObservableList<String> localizationList =
				FXCollections.observableArrayList(
						"Salon",
						"Cuisine",
						"Salle de bain",
						"Autre"
				);
		localization.setItems(localizationList);

		ObservableList<String> emaileDomaineList =
				FXCollections.observableArrayList(
						"@unice.fr",
						"@etu.unice.fr"
				);
		emaileDomaine.setItems(emaileDomaineList);
	}

	public void clear() {
		titre.clear();
		categorie.getItems().clear();
		description.clear();
		localizationDetail.clear();
		urgenceForte.setSelected(true);
		email.clear();
		localization.getItems().clear();
	}

}
