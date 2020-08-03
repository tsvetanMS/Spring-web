package softuni.emuseum.models.responce;

import java.time.LocalDateTime;

public class PageStatResponseModel {


    private String route;
    private Long visits;
    private String lastVisit;

    public PageStatResponseModel() {
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

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }
}
