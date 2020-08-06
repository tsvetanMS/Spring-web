package softuni.emuseum.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.models.service.CapacitorServiceModel;
import softuni.emuseum.repositories.CapacitorRepository;
import softuni.emuseum.services.api.CapacitorService;
import softuni.emuseum.services.impl.CapacitorServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
// и с двете бази данни минават тестовете
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class CapacitorServiceTests {

    @Autowired
    private CapacitorRepository capacitorRepository;

    private ModelMapper modelMapper;
    private CapacitorService capacitorService;

    private Capacitor capacitorFirst;
    private Capacitor capacitorSecond;
    private CapacitorServiceModel capacitorFirstSM;
    private CapacitorServiceModel capacitorSecondSM;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.capacitorService = new CapacitorServiceImpl(capacitorRepository, modelMapper);
        this.capacitorFirst = new Capacitor("1P66qO_nXkcjQ0pTJUd6PL6R7tT_XOFGw",
                "Description", "Ceramic disk", "4.7nF", "100V");
        this.capacitorSecond = new Capacitor("1nnF7BGdLVXkXs97_Uh8T34mDbByDNs21",
                "Description", "Polyester Multilayer", "6.8nF", "630V");
        this.capacitorFirstSM = this.modelMapper.map(capacitorFirst, CapacitorServiceModel.class);
        this.capacitorSecondSM = this.modelMapper.map(capacitorSecond, CapacitorServiceModel.class);
    }

//----------------------------------------------------------------------------------------------------------------------
    @Test
    public void loadCapacitorInDatabaseWithCorrectDataTest(){

        capacitorService.loadCapacitorInDatabase(capacitorFirstSM);

        Capacitor expected = this.capacitorRepository.findAll().get(0);
        int expectedSize = 1;

        Capacitor actual = capacitorFirst;
        int actualSize = this.capacitorRepository.findAll().size();

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getCapacitance(), actual.getCapacitance());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);


    }
//----------------------------------------------------------------------------------------------------------------------
    @Test(expected = Exception.class)
    public void loadCapacitorInDatabaseWithNotCorrectDataTest(){

        String notCorrectPictureID = null;

        Capacitor capacitorToBeSaved = new Capacitor(notCorrectPictureID,
                "Description", "Ceramic disk", "4.7nF", "100V");
        CapacitorServiceModel capacitorToBeSavedSM = this.modelMapper.map(capacitorToBeSaved, CapacitorServiceModel.class);

        capacitorService.loadCapacitorInDatabase(capacitorToBeSavedSM);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void saveCapacitorInDatabaseTest(){
        this.capacitorRepository.deleteAll();

        capacitorService.saveCapacitorInDatabase(capacitorFirstSM);

        Capacitor expected = capacitorFirst;
        Capacitor actual = this.capacitorRepository.findAll().get(0);
        long actualSize = this.capacitorRepository.count();
        long expectedSize = 1;


        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getCapacitance(), actual.getCapacitance());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findAllCapacitorsTest(){
        this.capacitorRepository.deleteAll();

        capacitorService.saveCapacitorInDatabase(capacitorFirstSM);
        capacitorService.saveCapacitorInDatabase(capacitorSecondSM);

        Capacitor expectedFirst = capacitorFirst;
        Capacitor expectedSecond = capacitorSecond;
        Capacitor actualFirst = this.capacitorRepository.findAll().get(0);
        Capacitor actualSecond = this.capacitorRepository.findAll().get(1);
        long actualSize = this.capacitorRepository.count();
        long expectedSize = 2;


        // базата данни връща записите в произволен ред
        if(!actualFirst.getPictureID().equals(expectedFirst.getPictureID())) {
            expectedFirst = capacitorSecond;
            expectedSecond = capacitorFirst;
        }

        Assert.assertEquals(expectedFirst.getPictureID(), actualFirst.getPictureID());
        Assert.assertEquals(expectedFirst.getDescription(), actualFirst.getDescription());
        Assert.assertEquals(expectedFirst.getType(), actualFirst.getType());
        Assert.assertEquals(expectedFirst.getCapacitance(), actualFirst.getCapacitance());
        Assert.assertEquals(expectedFirst.getMaxVoltage(), actualFirst.getMaxVoltage());

        Assert.assertEquals(expectedSecond.getPictureID(), actualSecond.getPictureID());
        Assert.assertEquals(expectedSecond.getDescription(), actualSecond.getDescription());
        Assert.assertEquals(expectedSecond.getType(), actualSecond.getType());
        Assert.assertEquals(expectedSecond.getCapacitance(), actualSecond.getCapacitance());
        Assert.assertEquals(expectedSecond.getMaxVoltage(), actualSecond.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void deleteCapacitorByIdTest(){
        this.capacitorRepository.deleteAll();

        capacitorService.saveCapacitorInDatabase(capacitorFirstSM);
        Capacitor capacitor = this.capacitorRepository.findAll().get(0);
        long id = capacitor.getId();

        capacitorService.deleteCapacitorById(id);

        long actualSizeOfRepository = this.capacitorRepository.count();
        long expectedSizeOfRepository = 0;

        Assert.assertEquals(expectedSizeOfRepository, actualSizeOfRepository);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findCapacitorByIdTest(){
        this.capacitorRepository.deleteAll();

        capacitorService.saveCapacitorInDatabase(capacitorFirstSM);
        Capacitor capacitor = this.capacitorRepository.findAll().get(0);
        long id = capacitor.getId();

        CapacitorServiceModel actual = capacitorService.findCapacitorById(id);
        CapacitorServiceModel expected = capacitorFirstSM;

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getCapacitance(), actual.getCapacitance());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

    }
//----------------------------------------------------------------------------------------------------------------------
}



