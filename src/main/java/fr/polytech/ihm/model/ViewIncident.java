package fr.polytech.ihm.model;

import java.util.ArrayList;
import java.util.List;

public class ViewIncident {
    private String rechercherIncident;
    private String trieUrgence;
    private String trieDate;
    private String trieLieu;
    private String trieCat;
    private List<Incident> incidentList = new ArrayList<>();

    public ViewIncident(){

    }

    public String getRechercherIncident() {
        return rechercherIncident;
    }

    public void setRechercherIncident(String rechercherIncident) {
        this.rechercherIncident = rechercherIncident;
    }

    public String getTrieUrgence() {
        return trieUrgence;
    }

    public void setTrieUrgence(String trieUrgence) {
        this.trieUrgence = trieUrgence;
    }

    public String getTrieDate() {
        return trieDate;
    }

    public void setTrieDate(String trieDate) {
        this.trieDate = trieDate;
    }

    public String getTrieLieu() {
        return trieLieu;
    }

    public void setTrieLieu(String trieLieu) {
        this.trieLieu = trieLieu;
    }

    public String getTrieCat() {
        return trieCat;
    }

    public void setTrieCat(String trieCat) {
        this.trieCat = trieCat;
    }
}
