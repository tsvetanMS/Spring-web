package softuni.emuseum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "page_statistics")
public class PageStat extends BaseEntity{

    private String route;
    private Long visits;
    private LocalDateTime lastVisit;

    public PageStat() {
    }

    public PageStat(String route, Long visits, LocalDateTime lastVisit) {
        this.route = route;
        this.visits = visits;
        this.lastVisit = lastVisit;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name = "route")
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name = "counter_of_visits")
    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name = "last_visit")
    @PastOrPresent
    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
//----------------------------------------------------------------------------------------------------------------------
}
