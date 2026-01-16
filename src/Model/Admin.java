package Model;


/**
 * @author Ishan Maharjan
 * Represents an admin user in the system.
 * Contains details such as username, name, email, and password.
 */
public class Admin {
    private String adminID; // Admin username
    private String adminName; // Admin full name
    private String adminEmail; // Admin email address
    private String adminPassword; // Admin password


    /**
     * Constructor to initialize the admin details.
     * @param adminID Admin username.
     * @param adminName Admin full name.
     * @param adminEmail Admin email address.
     * @param adminPassword Admin password.
     */
    public Admin(String adminID, String adminName, String adminEmail, String adminPassword) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    // Getters and setters for all fields
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
