package softuni.emuseum.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.emuseum.services.api.UserActivityStatService;

@Component
public class UserVisitsStatCleaner {

    private final UserActivityStatService userActivityStatService;

    public UserVisitsStatCleaner(UserActivityStatService userActivityStatService) {
        this.userActivityStatService = userActivityStatService;
    }

    @Scheduled(cron = "0 0 12 * * SUN") // всяка неделя в 12h00min00sec
    public void cleanOldUsersVisits(){
       userActivityStatService.cleanOldRecords();
    }
}
