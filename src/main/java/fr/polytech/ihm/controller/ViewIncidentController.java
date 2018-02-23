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
	boolean test = true;
	private ObservableList<Incident> incidentList = FXCollections.observableArrayList();
	private ObservableList<Incident> incidentListSearch = FXCollections.observableArrayList();
	String Text = null;

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
	private Comparator<Incident> comparatorIncident_byCat = Comparator.comparing(incident -> incident.getCategory().getName());
	private Comparator<Incident> comparatorIncident_byLieu = Comparator.comparing(incident -> incident.getLocation().getName());
	private Comparator<Incident> comparatorIncident_byDate = Comparator.comparing(Incident::getDate);
	private Comparator<Incident> comparatorIncident_byUrgence = Comparator.comparingInt(incident -> incident.getEmergency().ordinal());
	private Comparator<Incident> comparatorIncident_byCara = Comparator.comparing(incident -> incident.getTitle());


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
	void search(KeyEvent event) {
	}

	@FXML
	public void initialize() {
		this.orderedByCat = true;
		this.orderedByDate = true;
		this.orderedByLieu = true;
		this.orderedByUrgence = true;

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
									}
								else {
									this.setGraphic(null);
									this.setText(null);
								}
							}
						};
					}
				});

		this.trieCat.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> {
					if (orderedByCat) {
						incidentList.sort(comparatorIncident_byCat);
						orderedByCat = false;

					} else {
						incidentList.sort(comparatorIncident_byCat);
						Collections.reverse(incidentList);
						orderedByCat = true;
					}
				});

		this.trieDate.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> {
					if (orderedByDate) {
						incidentList.sort(comparatorIncident_byDate);
						orderedByDate = false;
					} else {
						incidentList.sort(comparatorIncident_byDate);
						Collections.reverse(incidentList);
						orderedByDate = true;
					}
				});

		this.trieLieu.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> {
					if (orderedByLieu) {
						incidentList.sort(comparatorIncident_byLieu);
						orderedByLieu = false;
					} else {
						incidentList.sort(comparatorIncident_byLieu);
						Collections.reverse(incidentList);
						orderedByLieu = true;
					}
				});

		this.trieUrgence.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> {
					if (orderedByUrgence) {
						incidentList.sort(comparatorIncident_byUrgence);
						orderedByUrgence = false;
					} else {
						incidentList.sort(comparatorIncident_byUrgence);
						Collections.reverse(incidentList);
						orderedByUrgence = true;
					}
				});

		//Found on http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
		this.rechercherIncident.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getTitle().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}else if (person.getEmailDomain().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}

				return false;
			});
		});
	}

}