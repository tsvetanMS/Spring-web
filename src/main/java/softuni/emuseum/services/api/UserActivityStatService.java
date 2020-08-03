package softuni.emuseum.services.api;

import softuni.emuseum.entities.UserActivity;
import softuni.emuseum.models.service.UserActivityServiceModel;

import java.util.List;


public interface UserActivityStatService {

    void saveActivity(String username, String route);

    void cleanOldRecords();

    List<UserActivityServiceModel> findUserActivity(String username);
}
