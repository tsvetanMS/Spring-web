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
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.models.service.ResistorServiceModel;
import softuni.emuseum.repositories.ResistorRepository;
import softuni.emuseum.services.api.ResistorService;
import softuni.emuseum.services.impl.ResistorServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class ResistorServiceTests {

    @Autowired
    private ResistorRepository resistorRepository;

    private ModelMapper modelMapper;
    private ResistorService resistorService;

    private Resistor resistorFirst;
    private Resistor resistorSecond;
    private ResistorServiceModel resistorFirstSM;
    private ResistorServiceModel resistorSecondSM;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();

        this.resistorService = new ResistorServiceImpl(resistorRepository, modelMapper);

        this.resistorFirst = new Resistor("1ZsQ-f8hBKkpiZ9WmSLP2bskk0rK_xvE-",
                "Produced in USSR. Resistor model С5-5В-1В.", "Wired", "3.3 Om", "1W", "5 percent" );
        this.resistorSecond = new Resistor("1HeUFXBBRfPWkSamGYA2iJE-75m06pobG",
                "Produced in Bulgaria. Resistor model РПМ2.", "Metal film", "22 Om", "0.25W", "5 percent" );

        this.resistorFirstSM = this.modelMapper.map(resistorFirst, ResistorServiceModel.class);
        this.resistorSecondSM = this.modelMapper.map(resistorSecond, ResistorServiceModel.class);
    }

//----------------------------------------------------------------------------------------------------------------------
    @Test
    public void loadResistorInDatabaseWithCorrectDataTest(){

        resistorService.loadResistorInDatabase(resistorFirstSM);

        Resistor expected = this.resistorRepository.findAll().get(0);
        int expectedSize = 1;

        Resistor actual = resistorFirst;
        int actualSize = this.resistorRepository.findAll().size();

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getResistance(), actual.getResistance());
        Assert.assertEquals(expected.getMaxPower(), actual.getMaxPower());

        Assert.assertEquals(expectedSize, actualSize);


    }
//----------------------------------------------------------------------------------------------------------------------
    @Test(expected = Exception.class)
    public void loadResistorInDatabaseWithNotCorrectDataTest(){

        String notCorrectPictureID = null;

        Resistor resistorToBeSaved = new Resistor(notCorrectPictureID,
                "Produced in USSR. Resistor model С5-5В-1В.", "Wired", "3.3 Om", "1W", "5 percent");

        ResistorServiceModel resistorToBeSavedSM = this.modelMapper.map(resistorToBeSaved, ResistorServiceModel.class);

        resistorService.loadResistorInDatabase(resistorToBeSavedSM);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void saveResistorInDatabaseTest(){
        this.resistorRepository.deleteAll();

        resistorService.saveResistorInDatabase(resistorFirstSM);

        Resistor expected = resistorFirst;
        Resistor actual = this.resistorRepository.findAll().get(0);
        long actualSize = this.resistorRepository.count();
        long expectedSize = 1;


        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getResistance(), actual.getResistance());
        Assert.assertEquals(expected.getMaxPower(), actual.getMaxPower());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findAllResistorsTest(){
        this.resistorRepository.deleteAll();

        resistorService.saveResistorInDatabase(resistorFirstSM);
        resistorService.saveResistorInDatabase(resistorSecondSM);

        Resistor expectedFirst = resistorFirst;
        Resistor expectedSecond = resistorSecond;
        Resistor actualFirst = this.resistorRepository.findAll().get(0);
        Resistor actualSecond = this.resistorRepository.findAll().get(1);
        long actualSize = this.resistorRepository.count();
        long expectedSize = 2;


        // базата данни връща записите в произволен ред
        if(!actualFirst.getPictureID().equals(expectedFirst.getPictureID())) {
            expectedFirst = resistorSecond;
            expectedSecond = resistorFirst;
        }

        Assert.assertEquals(expectedFirst.getPictureID(), actualFirst.getPictureID());
        Assert.assertEquals(expectedFirst.getDescription(), actualFirst.getDescription());
        Assert.assertEquals(expectedFirst.getType(), actualFirst.getType());
        Assert.assertEquals(expectedFirst.getResistance(), actualFirst.getResistance());
        Assert.assertEquals(expectedFirst.getMaxPower(), actualFirst.getMaxPower());

        Assert.assertEquals(expectedSecond.getPictureID(), actualSecond.getPictureID());
        Assert.assertEquals(expectedSecond.getDescription(), actualSecond.getDescription());
        Assert.assertEquals(expectedSecond.getType(), actualSecond.getType());
        Assert.assertEquals(expectedSecond.getResistance(), actualSecond.getResistance());
        Assert.assertEquals(expectedSecond.getMaxPower(), actualSecond.getMaxPower());

        Assert.assertEquals(expectedSize, actualSize);
    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void deleteResistorByIdTest(){
        this.resistorRepository.deleteAll();

        resistorService.saveResistorInDatabase(resistorFirstSM);
        Resistor resistor = this.resistorRepository.findAll().get(0);
        long id = resistor.getId();

        resistorService.deleteResistorById(id);

        long actualSizeOfRepository = this.resistorRepository.count();
        long expectedSizeOfRepository = 0;

        Assert.assertEquals(expectedSizeOfRepository, actualSizeOfRepository);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findResistorByIdTest(){
        this.resistorRepository.deleteAll();

        resistorService.saveResistorInDatabase(resistorFirstSM);
        Resistor resistor = this.resistorRepository.findAll().get(0);
        long id = resistor.getId();

        ResistorServiceModel actual = resistorService.findResistorById(id);
        ResistorServiceModel expected = resistorFirstSM;

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getResistance(), actual.getResistance());
        Assert.assertEquals(expected.getMaxPower(), actual.getMaxPower());

    }
//----------------------------------------------------------------------------------------------------------------------
}
