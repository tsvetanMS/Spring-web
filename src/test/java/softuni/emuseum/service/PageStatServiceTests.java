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
import softuni.emuseum.entities.PageStat;
import softuni.emuseum.models.service.PageStatServiceModel;
import softuni.emuseum.repositories.PageStatRepository;
import softuni.emuseum.services.api.PageStatService;
import softuni.emuseum.services.impl.PageStatServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class PageStatServiceTests {

    @Autowired
    private PageStatRepository pageStatRepository;

    private ModelMapper modelMapper;
    private PageStatService pageStatService;

    private PageStat pageStatFirst;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();

        this.pageStatService = new PageStatServiceImpl(pageStatRepository, modelMapper);

        pageStatFirst = new PageStat("/index", 10L, LocalDateTime.now());

    }
//----------------------------------------------------------------------------------------------------------------------
  /*
   @Override
    public Long findCounterByPageRoute(String pageRoute) {

        PageStat pageStat = this.pageStatRepository.findByRoute(pageRoute).orElse(null);

        if(pageStat == null){
            throw new PageStatNotFoundException("Page stat database is empty!");
        } else {
            return pageStat.getVisits();
        }

    }
   */

    @Test(expected = Exception.class)
   public void findCounterByPageRouteTestException(){
       this.pageStatService.findCounterByPageRoute("/users/login");
   }

    @Test
    public void findCounterByPageRouteTest(){
        this.pageStatRepository.saveAndFlush(pageStatFirst);

        long expectedCounter = 10L;
        long actualCounter = this.pageStatService.findCounterByPageRoute("/index");

        Assert.assertEquals(expectedCounter, actualCounter);

    }
//----------------------------------------------------------------------------------------------------------------------
/*
 @Override
    public void updatePageCounter(String pageRoute) {

        PageStat pageStat = this.pageStatRepository.findByRoute(pageRoute).orElse(null);

        if(pageStat == null){
            pageStat = new PageStat(pageRoute, 1L, LocalDateTime.now());
        } else {
            Long counter = pageStat.getVisits();
            counter ++;
            pageStat.setVisits(counter);
            pageStat.setLastVisit(LocalDateTime.now());
        }

        this.pageStatRepository.saveAndFlush(pageStat);
    }
 */

    @Test
    public void updatePageCounterIfIsEmptyTest() {
        this.pageStatRepository.deleteAll();

        this.pageStatService.updatePageCounter("/users");

        PageStat actualPageStat = this.pageStatRepository.findAll().get(0);
        long actualCounter = actualPageStat.getVisits();
        long expectedCounter = 1L;

        Assert.assertEquals(expectedCounter, actualCounter);
    }
    @Test
    public void updatePageCounterTest() {
        this.pageStatRepository.deleteAll();

        this.pageStatService.updatePageCounter("/users");
        this.pageStatService.updatePageCounter("/users");

        PageStat actualPageStat = this.pageStatRepository.findAll().get(0);
        long actualCounter = actualPageStat.getVisits();
        long expectedCounter = 2L;

        Assert.assertEquals(expectedCounter, actualCounter);
    }
//----------------------------------------------------------------------------------------------------------------------
  /*
    @Override
    public List<PageStatServiceModel> findAllPages() {
        List<PageStatServiceModel> pages = this.pageStatRepository.findAll().stream()
                .map(page -> this.modelMapper.map(page, PageStatServiceModel.class))
                .collect(Collectors.toList());

        return pages;

    }
   */
    @Test
    public void findAllPagesTest(){
        this.pageStatRepository.deleteAll();

        this.pageStatService.updatePageCounter("/users");
        this.pageStatService.updatePageCounter("/users");
        this.pageStatService.updatePageCounter("/index");
        this.pageStatService.updatePageCounter("/index");
        this.pageStatService.updatePageCounter("/about");
        this.pageStatService.updatePageCounter("/about");


        List<PageStatServiceModel> pageStatList = this.pageStatService.findAllPages();
        long actualSize = this.pageStatRepository.count();
        long expectedSize = 3L;

        Assert.assertEquals(expectedSize, actualSize);


    }
//----------------------------------------------------------------------------------------------------------------------
}
