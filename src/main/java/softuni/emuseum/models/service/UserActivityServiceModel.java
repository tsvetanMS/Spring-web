package softuni.emuseum.models.service;

import java.time.LocalDateTime;

public class UserActivityServiceModel {

    private String username;
    private String route;
    private LocalDateTime dateTime;

    public UserActivityServiceModel() {
    }

    public UserActivityServiceModel(String username, String route, LocalDateTime dateTime) {
        this.username = username;
        this.route = route;
        this.dateTime = dateTime;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
