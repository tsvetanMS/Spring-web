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
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.models.service.TransistorServiceModel;
import softuni.emuseum.repositories.TransistorRepository;
import softuni.emuseum.services.api.TransistorService;
import softuni.emuseum.services.impl.TransistorServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class TransistorServiceTests {


    @Autowired
    private TransistorRepository transistorRepository;

    private ModelMapper modelMapper;
    private TransistorService transistorService;

    private Transistor transistorFirst;
    private Transistor transistorSecond;
    private TransistorServiceModel transistorFirstSM;
    private TransistorServiceModel transistorSecondSM;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();

        this.transistorService = new TransistorServiceImpl(transistorRepository, modelMapper);

        transistorFirst = new Transistor("1hUfxKmAPlHPGRCue_DbzMLFqeCYTPTYW", "Transistor 2T3169. Produced in Bulgaria. Low noise, general purpose, low power.",
                "NPN", "25V", "100mA", "150MHz", "180-850");

        transistorSecond = new Transistor("1zJkg-Il0Xfau0OCC5l46Ifij2t4UtUkN", "Transistor 2T6551. Produced in Bulgaria. Low frequency, general purpose, low power.",
                "NPN", "75V", "500mA", "200MHz", "26-470");

        this.transistorFirstSM = this.modelMapper.map(transistorFirst, TransistorServiceModel.class);
        this.transistorSecondSM = this.modelMapper.map(transistorSecond, TransistorServiceModel.class);
    }

//----------------------------------------------------------------------------------------------------------------------
    @Test
    public void loadTransistorInDatabaseWithCorrectDataTest(){

        transistorService.loadTransistorInDatabase(transistorFirstSM);

        Transistor expected = this.transistorRepository.findAll().get(0);
        int expectedSize = 1;

        Transistor actual = transistorFirst;
        int actualSize = this.transistorRepository.findAll().size();

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getMaxCurrent(), actual.getMaxCurrent());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);


    }
//----------------------------------------------------------------------------------------------------------------------
    @Test(expected = Exception.class)
    public void loadTransistorInDatabaseWithNotCorrectDataTest(){

        String notCorrectPictureID = null;

        Transistor transistorToBeSaved = new Transistor(notCorrectPictureID,
                "Transistor 2T3169. Produced in Bulgaria. Low noise, general purpose, low power.",
                "NPN", "25V", "100mA", "150MHz", "180-850" );

        TransistorServiceModel transistorToBeSavedSM = this.modelMapper.map(transistorToBeSaved, TransistorServiceModel.class);

        transistorService.loadTransistorInDatabase(transistorToBeSavedSM);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void saveTransistorInDatabaseTest(){
        this.transistorRepository.deleteAll();

        transistorService.saveTransistorInDatabase(transistorFirstSM);

        Transistor expected = transistorFirst;
        Transistor actual = this.transistorRepository.findAll().get(0);
        long actualSize = this.transistorRepository.count();
        long expectedSize = 1;


        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getMaxCurrent(), actual.getMaxCurrent());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findAllTransistorsTest(){
        this.transistorRepository.deleteAll();

        transistorService.saveTransistorInDatabase(transistorFirstSM);
        transistorService.saveTransistorInDatabase(transistorSecondSM);

        Transistor expectedFirst = transistorFirst;
        Transistor expectedSecond = transistorSecond;
        Transistor actualFirst = this.transistorRepository.findAll().get(0);
        Transistor actualSecond = this.transistorRepository.findAll().get(1);
        long actualSize = this.transistorRepository.count();
        long expectedSize = 2;


        // базата данни връща записите в произволен ред
        if(!actualFirst.getPictureID().equals(expectedFirst.getPictureID())) {
            expectedFirst = transistorSecond;
            expectedSecond = transistorFirst;
        }

        Assert.assertEquals(expectedFirst.getPictureID(), actualFirst.getPictureID());
        Assert.assertEquals(expectedFirst.getDescription(), actualFirst.getDescription());
        Assert.assertEquals(expectedFirst.getType(), actualFirst.getType());
        Assert.assertEquals(expectedFirst.getMaxCurrent(), actualFirst.getMaxCurrent());
        Assert.assertEquals(expectedFirst.getMaxVoltage(), actualFirst.getMaxVoltage());

        Assert.assertEquals(expectedSecond.getPictureID(), actualSecond.getPictureID());
        Assert.assertEquals(expectedSecond.getDescription(), actualSecond.getDescription());
        Assert.assertEquals(expectedSecond.getType(), actualSecond.getType());
        Assert.assertEquals(expectedSecond.getMaxCurrent(), actualSecond.getMaxCurrent());
        Assert.assertEquals(expectedSecond.getMaxVoltage(), actualSecond.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void deleteTransistorByIdTest(){
        this.transistorRepository.deleteAll();

        transistorService.saveTransistorInDatabase(transistorFirstSM);
        Transistor Transistor = this.transistorRepository.findAll().get(0);
        long id = Transistor.getId();

        transistorService.deleteTransistorById(id);

        long actualSizeOfRepository = this.transistorRepository.count();
        long expectedSizeOfRepository = 0;

        Assert.assertEquals(expectedSizeOfRepository, actualSizeOfRepository);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findTransistorByIdTest(){
        this.transistorRepository.deleteAll();

        transistorService.saveTransistorInDatabase(transistorFirstSM);
        Transistor Transistor = this.transistorRepository.findAll().get(0);
        long id = Transistor.getId();

        TransistorServiceModel actual = transistorService.findTransistorById(id);
        TransistorServiceModel expected = transistorFirstSM;

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getMaxCurrent(), actual.getMaxCurrent());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

    }
//----------------------------------------------------------------------------------------------------------------------
}
