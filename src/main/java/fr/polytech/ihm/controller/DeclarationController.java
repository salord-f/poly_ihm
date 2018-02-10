package fr.polytech.ihm.controller;
import fr.polytech.ihm.MainApp;
import fr.polytech.ihm.model.Declaration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import  java.io.File;
import java.net.URL;


public class DeclarationController {

    //String filePath = "C:\\Users\\Polytech\\Desktop\\test.json";    //A MODIFIER //TODO
    File filePath= new File ("./jsonFile.txt");

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    Declaration declaration;


    @FXML
    private Text declarationTitle;

    @FXML
    private Text categorieLabel;


    private String categorieConvert;
    @FXML
    private ComboBox<String> categorie;

    @FXML
    private Text titreLabel;


    private String titreConvert;
    @FXML
    private TextField titre;


    private String descriptionConvert;
    @FXML
    private TextArea description;


    private String joinConvert; //URL
    @FXML
    private Button join;

    private String localizationConvert;
    @FXML
    private ComboBox<String> localization;


    private String localizationDetailConvert;
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


    private String emailConvert;
    @FXML
    private TextField email;


    private String emaileDomaineConvert;
    @FXML
    private ComboBox<String> emaileDomaine;

    @FXML
    private Button envoyer;

    @FXML
    private Button retour;


    void writeJson()
    {
        File f = filePath;
        if(f.exists() && !f.isDirectory()) {
            modifyJson();
        }
        else
        {
            createJson();
        }
    }

    void modifyJson()
    {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray list = (JSONArray) jsonObject.get("messages");

            JSONArray list2 = new JSONArray();
            list2.add(categorieConvert);
            list2.add(titreConvert);
            list2.add(descriptionConvert);
            list2.add("Emplacement futur URL");//list2.add(joinConvert); //TODO
            list2.add(localizationConvert);
            list2.add(localizationDetailConvert);
            list2.add(urgenceState);
            list2.add(emailConvert);
            list2.add(emaileDomaineConvert);



            list.add(list2);

            JSONObject obj2 = new JSONObject();
            obj2.put("messages", list);


            try (FileWriter file = new FileWriter(filePath)) {
                file.write(obj2.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void createJson()
    {
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();

        list2.add(categorieConvert);
        list2.add(titreConvert);
        list2.add(descriptionConvert);
        list2.add("Emplacement futur URL");//list2.add(joinConvert); //TODO
        list2.add(localizationConvert);
        list2.add(localizationDetailConvert);
        list2.add(urgenceState);
        list2.add(emailConvert);
        list2.add(emaileDomaineConvert);

        list.add(list2);

        JSONObject obj = new JSONObject();
        obj.put("messages", list);


        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void envoyerAction(ActionEvent event) {

        //if(checkInput())
        {
            retrieveData();
        }

        writeJson();
    }

    private void retrieveData()
    {
        //categorie.getSelectionModel().selectedItemProperty();

        categorieConvert = categorie.getValue();

        titreConvert = titre.getText();
        descriptionConvert = description.getText();
        //join. //TODO
        localizationConvert = localization.getValue();
        localizationDetailConvert = localizationDetail.getText();

        if(urgenceAucune.isSelected())
        {
            urgenceState = 0;
        }
        else if(urgenceFaible.isSelected())
        {
            urgenceState = 1;
        }
        else if(urgenceMoyenne.isSelected())
        {
            urgenceState = 2;
        }
        else if(urgenceForte.isSelected())
        {
            urgenceState = 3;
        }
        emailConvert = email.getText();
        emaileDomaineConvert = emaileDomaine.getValue();

        declaration = new Declaration(categorieConvert,titreConvert,descriptionConvert,joinConvert,localizationConvert,urgenceState,emailConvert,emaileDomaineConvert);
    }

    @FXML
    public void initialize()
    {
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
}
