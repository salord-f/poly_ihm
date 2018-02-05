package fr.polytech.ihm.model;

import javafx.scene.image.Image;

public class Incident {


    private String categorie;
    private String titre;
    private String description;
    private Image image;
    private String lieu;
    private String urgence;
    private String email;

    public Incident(String categorie, String titre, String description, Image image, String lieu, String urgence, String email) {
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.urgence = urgence;
        this.email = email;
    }

    public Incident(String titre, String email) {
        this.titre = titre;
        this.email = email;
    }


    public String getCategorie() {
        return categorie;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public String getLieu() {
        return lieu;
    }

    public String getUrgence() {
        return urgence;
    }

    public String getEmail() {
        return email;
    }
}
