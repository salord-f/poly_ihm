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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class DeclarationController {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Incident incident;
    private String calendar;
    private String categorieConvert = "";
    private String titreConvert = "";
    private String descriptionConvert = "";
    private String joinConvert = ""; //URL
    private String localizationConvert = "";
    private String localizationDetailConvert = "";
    private int urgenceState;
    private String emailConvert = "";
    private String emailDomaineConvert = "";


    @FXML
    private ComboBox<String> categorie;

    @FXML
    private TextField titre;

    @FXML
    private TextArea description;

    @FXML
    private Button join;

    @FXML
    private ComboBox<String> localization;

    @FXML
    private TextField localizationDetail;

    @FXML
    private RadioButton urgenceAucune;

    @FXML
    private RadioButton urgenceFaible;

    @FXML
    private RadioButton urgenceMoyenne;

    @FXML
    private RadioButton urgenceForte;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> emailDomaine;

    @FXML
    void joinAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        File URL = fileChooser.showOpenDialog(join.getScene().getWindow());

        String file = "." + File.separator;
        if (URL != null) {
            Path currentDirectory = new File(file + URL.getName()).toPath();
            try {
                Files.copy(URL.toPath(), currentDirectory, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            joinConvert = currentDirectory.toString();
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
                !emailDomaineConvert.equals("")) {
            try {
                Stage stage = new Stage();
                Stage stage2 = (Stage) join.getScene().getWindow();
                Parent root = FXMLLoader.load(
                        getClass().getResource("/fxml/confirmationAnnulation.fxml")); //TODO
                Scene s = new Scene(root);
                stage.setScene(s);
                s.getStylesheets().add("/styles/styles.css");
                stage.setTitle("Quitter la déclaration");
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
                Scene s = new Scene(root);
                stage.setScene(s);
                s.getStylesheets().add("/styles/styles.css");
                stage.setTitle("Liste des incidents");
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
                Scene s = new Scene(root);
                stage.setScene(s);
                s.getStylesheets().add("/styles/styles.css");
                stage.setTitle("Confirmation de la déclaration");

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
                        getClass().getResource("/fxml/incorrectDeclaration.fxml"));
                Scene s = new Scene(root);
                stage.setScene(s);
                s.getStylesheets().add("/styles/styles.css");
                stage.setTitle("Erreur de remplissage");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(stage2);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void retrieveData() {
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
        emailDomaineConvert = emailDomaine.getValue();
        if (emailDomaineConvert == null) {
            emailDomaineConvert = "";
        }

        DateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 1);

        calendar = dateFormat.format(cal.getTime());

        incident = new Incident(categorieConvert, titreConvert, descriptionConvert, joinConvert, localizationConvert, localizationDetailConvert, urgenceState, emailConvert, emailDomaineConvert, calendar);
    }

    @FXML
    public void initialize() {
        ObservableList<String> categorieList = FXCollections.observableArrayList();
        Arrays.stream(Category.values()).map(Category::getName).forEach(categorieList::add);
        categorieList.remove("");
        categorie.setItems(categorieList);

        ObservableList<String> localizationList = FXCollections.observableArrayList();
        Arrays.stream(Location.values()).map(Location::getName).forEach(localizationList::add);
        localizationList.remove("");
        localization.setItems(localizationList);

        ObservableList<String> emaileDomaineList =
                FXCollections.observableArrayList(
                        "@unice.fr",
                        "@etu.unice.fr"
                );
        emailDomaine.setItems(emaileDomaineList);
    }

    private void clear() {
        titre.clear();
        categorie.getItems().clear();
        description.clear();
        localizationDetail.clear();
        urgenceAucune.setSelected(true);
        email.clear();
        emailDomaine.getItems().clear();
        localization.getItems().clear();
        joinConvert = "";
        initialize();
    }
}
