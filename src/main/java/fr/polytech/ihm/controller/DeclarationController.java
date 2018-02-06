package fr.polytech.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class DeclarationController {

    @FXML
    private Text declarationTitle;

    @FXML
    private Text categorieLabel;

    @FXML
    private ComboBox<?> categorie;

    @FXML
    private Text titreLabel;

    @FXML
    private TextField titre;

    @FXML
    private TextArea description;

    @FXML
    private Button join;

    @FXML
    private ComboBox<?> localization;

    @FXML
    private TextField localizationDetail;

    @FXML
    private RadioButton urgenceAucune;

    @FXML
    private RadioButton urganceFaible;

    @FXML
    private RadioButton urgenceMoyenne;

    @FXML
    private RadioButton urgenceForte;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<?> emaileDomaine;

    @FXML
    private Button envoyer;

    @FXML
    private Button retour;

}
