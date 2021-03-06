package fr.polytech.ihm.model;

/**
 * Model of the incident
 */
public class Incident {
    private Category category;
    private String title;
    private String description;
    private String image;
    private Location location;
    private String locationDetail;
    private Emergency emergency;
    private String email;
    private String emailDomain;
    private String date;

    /**
     * Create an incident
     *
     * @param category
     * @param title
     * @param description
     * @param image
     * @param location
     * @param locationDetail
     * @param emergency
     * @param email
     * @param emailDomain
     * @param date
     */
    public Incident(String category, String title, String description, String image, String location, String locationDetail, int emergency, String email, String emailDomain, String date) {
        this.category = Category.find(category);
        this.title = title;
        this.description = description;
        this.image = image;
        this.location = Location.find(location);
        this.locationDetail = locationDetail;
        this.emergency = Emergency.values()[emergency];
        this.email = email;
        this.emailDomain = emailDomain;
        this.date = date;
    }

    public Emergency getEmergency() {
        return emergency;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public String getDate() {
        return date;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    /**
     * Check the important input
     *
     * @return true if all the important input are not null else return false
     */
    public boolean checkInput() {
        return !getTitle().equals("") && !getEmailDomain().equals("") && !getEmail().equals("");
    }
}
