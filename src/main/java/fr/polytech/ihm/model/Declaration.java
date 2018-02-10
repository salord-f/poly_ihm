package fr.polytech.ihm.model;

import java.net.URL;

public class Declaration {
    private String categorie;
    private String titre;
    private String descrpition;
    private String image;
    private String localisation;
    private int urgence;
    private String email;
    private String emailDomaine;

    public Declaration(String categorie, String titre, String descrpition, String image, String localisation, int urgence, String email, String emailDomaine )
    {
        this.categorie = categorie;
        this.titre = titre;
        this.descrpition = descrpition;
        this.image = image;
        this.localisation = localisation;
        this.urgence = urgence;
        this.email = email;
        this.emailDomaine = emailDomaine;
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

    public String getImage() {
        return image;
    }

    public String getEmailDomaine() {
        return emailDomaine;
    }
}
