package fr.polytech.ihm.model;

public class Incident {
    private String categorie;
    private String titre;
    private String description;
    private String image;
    private String localisation;
    private int urgence;
    private String email;
    private String emailDomaine;
    private String date;

    public Incident(String categorie, String titre, String description, String image, String localisation, int urgence, String email, String emailDomaine, String date) {
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.localisation = localisation;
        this.urgence = urgence;
        this.email = email;
        this.emailDomaine = emailDomaine;
        this.date = date;
    }

    public int getUrgence() {
        return urgence;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
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

    public String getDate() {
        return date;
    }

    public boolean checkInput() {
        return !getTitre().equals("") && !getEmailDomaine().equals("") && !getEmail().equals("");
    }
}
