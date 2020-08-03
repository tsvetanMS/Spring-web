package softuni.emuseum.models.responce;

import softuni.emuseum.models.service.RoleServiceModel;

import java.util.Set;

public class UserEditResponseModel {

    private long id;
    private String username;
    private String email;
    private String userRole;

    public UserEditResponseModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
