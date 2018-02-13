package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewIncidentController {
    private ObservableList<Incident> incidentList = FXCollections.observableArrayList();

    @FXML
    private TextField rechercherIncident;

    @FXML
    private Button declarerIncident;

    @FXML
    private GridPane trierPar;

    @FXML
    private Button trieUrgence;

    @FXML
    private Button trieDate;

    @FXML
    private Button trieLieu;

    @FXML
    private Button trieCat;

    @FXML
    private ListView<Incident> listeViewIncidents;

    @FXML
    void openNewDeclaration(MouseEvent event) {
        try {
            Stage stage = (Stage) declarerIncident.getScene().getWindow();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/declaration.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Déclaration");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Comparator<Incident> comparatorIncident_byCat = (inc1, inc2) -> inc1.getCategorie().compareToIgnoreCase(inc2.getCategorie());

    @FXML
    void orderByCat(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            incidentList.sort(comparatorIncident_byCat);
        }
    }

    private Comparator<Incident> comparatorIncident_byLieu = (inc1, inc2) -> inc1.getLocalisation().compareToIgnoreCase(inc2.getLocalisation());

    @FXML
    void orderByLieu(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            incidentList.sort(comparatorIncident_byLieu);
        }
    }

    private Comparator<Incident> comparatorIncident_byDate = (inc1, inc2) -> inc1.getLocalisation().compareToIgnoreCase(inc2.getLocalisation());

    @FXML
    void orderByDate(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            incidentList.sort(comparatorIncident_byDate);
        }
    }

    private Comparator<Incident> comparatorIncident_byUrgence = (inc1, inc2) -> inc1.getLocalisation().compareToIgnoreCase(inc2.getLocalisation());

    @FXML
    void orderByUrgence(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            incidentList.sort(comparatorIncident_byUrgence);
        }
    }

    @FXML
    void search(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            //TODO order
            rechercherIncident.setText("");
        }
        /*}
        rechercherIncident.setOnKeyPressed(() -> {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)  {
                    String text = rechercherIncident.getText();

                    // do your thing...

                    // clear text
                    rechercherIncident.setText("");
                }
            }
        });*/


    }

    @FXML
    public void initialize() {
        incidentList.add(new Incident("Incident", "Test1", "fuck noé", null, "Baignoire", 1, "noémangeducaca", "@etu.unice.fr", "date"));
        incidentList.add(new Incident("Incident", "Test2", "fuck quentin", null, "Baignoire", 1, "quentinmangeducaca", "@etu.unice.fr", "date"));

        /*.setCellValueFactory(cellData -> cellData.getValue().tagNameProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        room.setCellValueFactory(cellData -> cellData.getValue().meetingRoomProperty());
*/
        listeViewIncidents.setItems(incidentList);


        /*listeViewIncidents.setRowFactory(tv -> {
            TableRow<Incident> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {
                    String fxmlFile = "/fxml/incidents.fxml";
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        Stage stage=(Stage) listeViewIncidents.getScene().getWindow();
                        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

                        Scene scene = new Scene(rootNode);
                        stage.setScene(scene);
                        ((IncidentController)loader.getController()).initialize();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });*/
        this.listeViewIncidents.setCellFactory(
            new Callback<ListView<Incident>, ListCell<Incident>>() {
                @Override
                public ListCell<Incident> call(ListView<Incident> listView) {
                    return new ListCell<Incident>() {
                        @Override
                        protected void updateItem(Incident item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                // Load fxml file for this internship
                                try {
                                    String fxmlFile = "/fxml/incidents.fxml";
                                    FXMLLoader loader = new FXMLLoader();
                                    Parent listElement = loader.load(getClass().getResourceAsStream(fxmlFile));
                                    ((IncidentController) loader.getController()).initialize(item);
                                    // Display content of the fxml file
                                    this.setGraphic(listElement);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                }
            });
}

}