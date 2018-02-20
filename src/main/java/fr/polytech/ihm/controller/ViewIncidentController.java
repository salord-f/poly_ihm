package fr.polytech.ihm.controller;

import fr.polytech.ihm.JsonManager;
import fr.polytech.ihm.model.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
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
	ObservableList<Incident> incidentListSearch = FXCollections.observableArrayList();

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
	private Comparator<Incident> comparatorIncident_byCat = Comparator.comparing(Incident::getCategory);
	private Comparator<Incident> comparatorIncident_byLieu = Comparator.comparing(Incident::getLocation);
	private Comparator<Incident> comparatorIncident_byDate = Comparator.comparing(Incident::getDate);
	private Comparator<Incident> comparatorIncident_byUrgence = Comparator.comparingInt(Incident::getEmergency);

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
	void search(KeyEvent event) {
		if (event.getEventType()== KeyEvent.KEY_RELEASED) {
			//TODO order
			String Text = event.getText(); //TODO

			if (Text.length() == 0 ) {
				incidentList.clear();
				incidentList.addAll(incidentListSearch);
			}

			/*Iterator<String> iter = myArrayList.iterator();

			while (iter.hasNext()) {
				String str = iter.next();

				if (someCondition)
					iter.remove();*/

			for (Incident incident : incidentListSearch) {
				if (incident.getEmail().toLowerCase().contains(Text) ||
						/*incident.getLocation().toLowerCase().contains(Text) ||
						incident.getCategory().toLowerCase().contains(Text) ||*/
						incident.getTitle().toLowerCase().contains(Text)) {
				} else {
					incidentList.remove(incident);
				}
			}
		}

	}

	@FXML
	public void initialize() {
		this.orderedByCat = true;
		this.orderedByDate = true;
		this.orderedByLieu = true;
		this.orderedByUrgence = true;

		incidentList.addAll(new JsonManager().getIncidents());
		listeViewIncidents.setItems(incidentList);

		incidentListSearch.clear();
		incidentListSearch.addAll(incidentList);

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
	}

}