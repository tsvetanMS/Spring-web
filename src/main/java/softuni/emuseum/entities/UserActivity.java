package softuni.emuseum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name="users_activity")
public class UserActivity extends BaseEntity{

    private String username;
    private String route;
    private LocalDateTime dateTime;


    public UserActivity() {
    }

    public UserActivity(String username, String route, LocalDateTime dateTime) {
        this.username = username;
        this.route = route;
        this.dateTime = dateTime;
    }

    //----------------------------------------------------------------------------------------------------------------------
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "date_and_time")
    @PastOrPresent
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
//----------------------------------------------------------------------------------------------------------------------
}
