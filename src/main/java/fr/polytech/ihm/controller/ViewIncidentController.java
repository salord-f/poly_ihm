package fr.polytech.ihm.controller;

import fr.polytech.ihm.JsonManager;
import fr.polytech.ihm.model.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private Comparator<Incident> comparatorIncident_byCat = (inc1, inc2) -> inc1.getCategorie().compareToIgnoreCase(inc2.getCategorie());
	private Comparator<Incident> comparatorIncident_byLieu = (inc1, inc2) -> inc1.getLocalisation().compareToIgnoreCase(inc2.getLocalisation());
	private Comparator<Incident> comparatorIncident_byDate = (inc1, inc2) -> inc1.getDate().compareToIgnoreCase(inc2.getDate());
	private Comparator<Incident> comparatorIncident_byUrgence = Comparator.comparingInt(Incident::getUrgence);

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

	@FXML
	void orderByCat(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if (orderedByCat) {
				incidentList.sort(comparatorIncident_byCat);
				orderedByCat = true;
			} else {
				Collections.reverse(incidentList);
				orderedByCat = false;
			}
		}
	}

	@FXML
	void orderByLieu(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if (orderedByLieu) {
				incidentList.sort(comparatorIncident_byLieu);
				orderedByLieu = true;
			} else {
				Collections.reverse(incidentList);
				orderedByLieu = false;
			}
		}
	}

	@FXML
	void orderByDate(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if (orderedByDate) {
				incidentList.sort(comparatorIncident_byDate);
				orderedByDate = true;
			} else {
				Collections.reverse(incidentList);
				orderedByDate = false;
			}
		}
	}

	@FXML
	void orderByUrgence(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if (orderedByUrgence) {
				incidentList.sort(comparatorIncident_byUrgence);
				orderedByUrgence = true;
			} else {
				Collections.reverse(incidentList);
				orderedByUrgence = false;
			}
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
		this.orderedByCat = false;
		this.orderedByDate = false;
		this.orderedByLieu = false;
		this.orderedByUrgence = false;

		incidentList.addAll(new JsonManager().getIncidents());
		listeViewIncidents.setItems(incidentList);

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