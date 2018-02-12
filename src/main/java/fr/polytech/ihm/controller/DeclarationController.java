package fr.polytech.ihm.controller;
import fr.polytech.ihm.MainApp;
import fr.polytech.ihm.model.Declaration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class DeclarationController {

    //String filePath = "C:\\Users\\Polytech\\Desktop\\test.json";    //A MODIFIER //TODO
    File filePath= new File ("./jsonFile.json");

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
            list2.add(joinConvert);
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
        list2.add(joinConvert); //TODO
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
    void joinAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File URL = fileChooser.showOpenDialog(join.getScene().getWindow());

        String file = "./";

        Path currentDirectory = new File(file + URL.getName()).toPath();

        try {
            Files.copy(URL.toPath(),currentDirectory, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        joinConvert = currentDirectory.toString();
    }

    @FXML
    void RetourAction(ActionEvent event) {
        try {
            Stage stage = (Stage) join.getScene().getWindow();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/viewIncidents.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("ViewIncident");
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void envoyerAction(ActionEvent event) {

        //if(checkInput())

        retrieveData();

        if (checkInput())
        {
            writeJson();
        }
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

    public boolean checkInput()
    {
        if (emaileDomaineConvert == null)
        {
            emaileDomaineConvert = ("");
        }
        if (titreConvert.equals("") || emaileDomaineConvert.equals("") ||  emailConvert.equals(""))
        {
            try {
                Stage stage = new Stage();

                Stage stage2 = (Stage) join.getScene().getWindow();

                Parent root = FXMLLoader.load(
                        getClass().getResource("/fxml/incorrectDeclaration.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Error");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(stage2);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            /*try {
                String fxmlFile = "/fxml/incorrectDeclaration.fxml";
                FXMLLoader loader = new FXMLLoader();

                Stage stage = (Stage) join.getScene().getWindow();
                Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
                Scene scene = new Scene(rootNode);
                stage.setTitle("MARMOUD");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return false;}
        else
        {
            return true;
        }
    }
}
