package softuni.emuseum.models.service;

import java.time.LocalDateTime;

public class PageStatServiceModel {

    private String route;
    private Long visits;
    private LocalDateTime lastVisit;

    public PageStatServiceModel() {
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
}
