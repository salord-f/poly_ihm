package fr.polytech.ihm.model;

import java.net.URL;

public class Declaration {
    private String categorie;
    private String titre;
    private String descrpition;
    private URL image;
    private String localisation;
    private int urgence;
    private String email;

    private Declaration(String categorie, String titre, String descrpition, URL image, String localisation, int urgence, String email )
    {
        this.categorie = categorie;
        this.titre = titre;
        this.descrpition = descrpition;
        this.image = image;
        this.localisation = localisation;
        this.urgence = urgence;
        this.email = email;
    }

    public int getUrgence() {
        return urgence;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescrpition() {
        return descrpition;
    }

    public String getEmail() {
        return email;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getTitre() {
        return titre;
    }

    public URL getImage() {
        return image;
    }
}
