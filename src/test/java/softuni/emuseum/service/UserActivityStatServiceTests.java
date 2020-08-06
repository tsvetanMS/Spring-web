package softuni.emuseum.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.emuseum.entities.UserActivity;
import softuni.emuseum.repositories.UserActivityRepository;
import softuni.emuseum.services.api.UserActivityStatService;
import softuni.emuseum.services.impl.UserActivityStatServiceImpl;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class UserActivityStatServiceTests {


    @Autowired
    private UserActivityRepository userActivityRepository;

    private ModelMapper modelMapper;
    private UserActivityStatService userActivityStatService;


    @Before
    public void init(){
        this.modelMapper = new ModelMapper();

        this.userActivityStatService = new UserActivityStatServiceImpl(userActivityRepository, modelMapper);

    }
//----------------------------------------------------------------------------------------------------------------------
/*
    @Override
    public void saveActivity(String username, String route) {

        // това се прави без new ! Това е статичен клас?
        LocalDateTime localDateTime = LocalDateTime.now();

        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(username);
        userActivity.setRoute(route);
        userActivity.setDateTime(localDateTime);

        this.userActivityRepository.saveAndFlush(userActivity);
    }
 */
    @Test
    public void saveActivityTest(){
        this.userActivityStatService.saveActivity("usernameOne", "/index");
        this.userActivityStatService.saveActivity("usernameTwo", "/about");

        long actualCount = this.userActivityRepository.count();
        long expectedCount = 2;

        Assert.assertEquals(expectedCount, actualCount);
    }
//----------------------------------------------------------------------------------------------------------------------
/*
    @Override
    public void cleanOldRecords() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7L);

        this.userActivityRepository.deleteAllByDateTimeBefore(localDateTime);
    }
 */
    @Test
    public void cleanOldRecordsTest(){
        this.userActivityRepository.deleteAll();
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7L);
        UserActivity userActivityOne = new UserActivity("usernameOne", "/index", localDateTime);
        UserActivity userActivityTwo = new UserActivity("usernameTwo", "/index", localDateTime);
        this.userActivityRepository.saveAndFlush(userActivityOne);
        this.userActivityRepository.saveAndFlush(userActivityTwo);

        this.userActivityStatService.cleanOldRecords();

        long actualCount = this.userActivityRepository.count();
        long expectedCount = 0L;

        Assert.assertEquals(expectedCount, actualCount);
    }
//----------------------------------------------------------------------------------------------------------------------
/*
 @Override
    public List<UserActivityServiceModel> findAllUsersActivity() {
        List<UserActivity> userActivities = this.userActivityRepository.findAll();

        if(userActivities.size() == 0){
            throw new UserActivityStatNotFoundException("User activity database is empty!");
        } else {
           return userActivities.stream().map(userActivity -> this.modelMapper.map(userActivity, UserActivityServiceModel.class))
                    .collect(Collectors.toList());
        }

    }
 */

    @Test(expected = Exception.class)
    public void findAllUsersActivityTestException(){
        this.userActivityRepository.deleteAll();
        this.userActivityStatService.findAllUsersActivity();
    }

    @Test
    public void findAllUsersActivityTest(){
        this.userActivityRepository.deleteAll();
        LocalDateTime localDateTime = LocalDateTime.now();
        UserActivity userActivityOne = new UserActivity("usernameOne", "/index", localDateTime);
        UserActivity userActivityTwo = new UserActivity("usernameTwo", "/index", localDateTime);
        this.userActivityRepository.saveAndFlush(userActivityOne);
        this.userActivityRepository.saveAndFlush(userActivityTwo);

        long expectedSize = 2L;
        long actualSize = this.userActivityStatService.findAllUsersActivity().size();

        Assert.assertEquals(expectedSize, actualSize);

    }
//----------------------------------------------------------------------------------------------------------------------
}
