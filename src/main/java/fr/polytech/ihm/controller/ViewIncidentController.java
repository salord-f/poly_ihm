package fr.polytech.ihm.controller;

import fr.polytech.ihm.JsonManager;
import fr.polytech.ihm.model.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class ViewIncidentController {
    private ObservableList<Incident> incidentList = FXCollections.observableArrayList();
    private boolean orderedByCat;
    private boolean orderedByLieu;
    private boolean orderedByDate;
    private boolean orderedByUrgence;

    @FXML
    private TextField rechercherIncident;

    @FXML
    private Button declarerIncident;

    @FXML
    private Button trieUrgence;

    @FXML
    private Button trieDate;

    @FXML
    private Button trieLieu;

    @FXML
    private Button trieCat;

    @FXML
    private ImageView urgenceImageV;

    @FXML
    private ImageView dateImageV;

    @FXML
    private ImageView lieuImageV;

    @FXML
    private ImageView catImageV;


    @FXML
    private ListView<Incident> listeViewIncidents;
    private Comparator<Incident> comparatorIncident_byCat = Comparator.comparing(incident -> incident.getCategory().getName());
    private Comparator<Incident> comparatorIncident_byLieu = Comparator.comparing(incident -> incident.getLocation().getName());
    private Comparator<Incident> comparatorIncident_byDate = Comparator.comparing(Incident::getDate);
    private Comparator<Incident> comparatorIncident_byUrgence = Comparator.comparingInt(incident -> incident.getEmergency().ordinal());

    @FXML
    void openNewDeclaration(MouseEvent event) {
        try {
            Stage stage = (Stage) declarerIncident.getScene().getWindow();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/declaration.fxml"));
            Scene s = new Scene(root);
            stage.setScene(s);
            s.getStylesheets().add("/styles/styles.css");
            stage.setTitle("Déclaration d'un nouvel incident");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        this.orderedByCat = true;
        this.orderedByDate = true;
        this.orderedByLieu = true;
        this.orderedByUrgence = true;
        showArrow(this.catImageV);
        showArrow(this.dateImageV);
        showArrow(this.lieuImageV);
        showArrow(this.urgenceImageV);


        incidentList.addAll(new JsonManager().getIncidents());

        FilteredList<Incident> filteredData = new FilteredList<>(incidentList, p -> true);

        SortedList<Incident> sortedData = new SortedList<>(filteredData);

        listeViewIncidents.setItems(sortedData);

        this.listeViewIncidents.setCellFactory(
                new Callback<ListView<Incident>, ListCell<Incident>>() {
                    @Override
                    public ListCell<Incident> call(ListView<Incident> listView) {
                        return new ListCell<Incident>() {
                            @Override
                            protected void updateItem(Incident item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    try {
                                        String fxmlFile = "/fxml/incidents.fxml";
                                        FXMLLoader loader = new FXMLLoader();
                                        Parent listElement = loader.load(getClass().getResourceAsStream(fxmlFile));
                                        ((IncidentController) loader.getController()).initialize(item); //TODO
                                        // Display content of the fxml file
                                        this.setGraphic(listElement);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    this.setGraphic(null);
                                    this.setText(null);
                                }
                            }
                        };
                    }
                });

        this.trieCat.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    catImageV.getTransforms().add(new Rotate(180, 5, 5));
                    if (orderedByCat) {
                        incidentList.sort(comparatorIncident_byCat);
                        orderedByCat = false;

                    } else {
                        incidentList.sort(comparatorIncident_byCat);
                        Collections.reverse(incidentList);
                        orderedByCat = true;
                    }
                    disabledArrow(catImageV);
                });

        this.trieDate.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    dateImageV.getTransforms().add(new Rotate(180, 5, 5));
                    if (orderedByDate) {
                        incidentList.sort(comparatorIncident_byDate);
                        orderedByDate = false;
                    } else {
                        incidentList.sort(comparatorIncident_byDate);
                        Collections.reverse(incidentList);
                        orderedByDate = true;
                    }
                    disabledArrow(dateImageV);
                });

        this.trieLieu.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    lieuImageV.getTransforms().add(new Rotate(180, 5, 5));
                    if (orderedByLieu) {
                        incidentList.sort(comparatorIncident_byLieu);
                        orderedByLieu = false;
                    } else {
                        incidentList.sort(comparatorIncident_byLieu);
                        Collections.reverse(incidentList);
                        orderedByLieu = true;
                    }
                    disabledArrow(lieuImageV);
                });

        this.trieUrgence.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    urgenceImageV.getTransforms().add(new Rotate(180, 5, 5));
                    if (orderedByUrgence) {
                        incidentList.sort(comparatorIncident_byUrgence);
                        orderedByUrgence = false;
                    } else {
                        incidentList.sort(comparatorIncident_byUrgence);
                        Collections.reverse(incidentList);
                        orderedByUrgence = true;
                    }
                    disabledArrow(urgenceImageV);

                });

        //Found on http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        this.rechercherIncident.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(incident -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowCase = newValue.toLowerCase();
            return incident.getTitle().contains(lowCase) || incident.getEmail().contains(lowCase) || incident.getEmailDomain().contains(lowCase);
        }));
    }

    private void disabledArrow(ImageView image) {
        if (!image.equals(catImageV) && !orderedByCat) {
            catImageV.getTransforms().add(new Rotate(180, 5, 5));
            orderedByCat = true;
        }
        if (!image.equals(dateImageV) && !orderedByDate) {
            dateImageV.getTransforms().add(new Rotate(180, 5, 5));
            orderedByDate = true;
        }
        if (!image.equals(lieuImageV) && !orderedByLieu) {
            lieuImageV.getTransforms().add(new Rotate(180, 5, 5));
            orderedByLieu = true;
        }
        if (!image.equals(urgenceImageV) && !orderedByUrgence) {
            urgenceImageV.getTransforms().add(new Rotate(180, 5, 5));
            orderedByUrgence = true;
        }
    }

    private void showArrow(ImageView view) {
        try {
            Image image = new Image("images" + File.separator + "arrow.png");
            view.setImage(image);
            view.setCache(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}