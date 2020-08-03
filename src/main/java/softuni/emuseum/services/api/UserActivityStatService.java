package softuni.emuseum.services.api;

public interface UserActivityStatService {

    void saveActivity(String username, String route);

    void cleanOldRecords();
}
