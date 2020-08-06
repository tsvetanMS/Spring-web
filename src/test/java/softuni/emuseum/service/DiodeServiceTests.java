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
import softuni.emuseum.entities.Diode;
import softuni.emuseum.models.service.DiodeServiceModel;
import softuni.emuseum.repositories.DiodeRepository;
import softuni.emuseum.services.api.DiodeService;
import softuni.emuseum.services.impl.DiodeServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
public class DiodeServiceTests {


    @Autowired
    private DiodeRepository diodeRepository;

    private ModelMapper modelMapper;
    private DiodeService diodeService;

    private Diode diodeFirst;
    private Diode diodeSecond;
    private DiodeServiceModel diodeFirstSM;
    private DiodeServiceModel diodeSecondSM;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();

        this.diodeService = new DiodeServiceImpl(diodeRepository, modelMapper);

        diodeFirst = new Diode("1k2OU3IxYfGEFt8r1aRY_v67EHdQuGDvn",
                "Produced in Bulgaria. Diode model 2Д2402.", "Rectifier.", "100V", "1A");
        diodeSecond = new Diode("1Gs8cDnZ8P2uTcJmxI3HsWPy8VOjH8I4Q",
                "Produced in Bulgaria. Diode model 2Д5607.", "Fast switch.", "60V", "50mA");
        this.diodeFirstSM = this.modelMapper.map(diodeFirst, DiodeServiceModel.class);
        this.diodeSecondSM = this.modelMapper.map(diodeSecond, DiodeServiceModel.class);
    }

//----------------------------------------------------------------------------------------------------------------------
    @Test
    public void loadDiodeInDatabaseWithCorrectDataTest(){

        diodeService.loadDiodeInDatabase(diodeFirstSM);

        Diode expected = this.diodeRepository.findAll().get(0);
        int expectedSize = 1;

        Diode actual = diodeFirst;
        int actualSize = this.diodeRepository.findAll().size();

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getMaxCurrent(), actual.getMaxCurrent());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

        Assert.assertEquals(expectedSize, actualSize);


    }
//----------------------------------------------------------------------------------------------------------------------
    @Test(expected = Exception.class)
    public void loadDiodeInDatabaseWithNotCorrectDataTest(){

        String notCorrectPictureID = null;

        Diode diodeToBeSaved = new Diode(notCorrectPictureID,
                "Produced in Bulgaria. Diode model 2Д2402.", "Rectifier.", "100V", "1A");

        DiodeServiceModel diodeToBeSavedSM = this.modelMapper.map(diodeToBeSaved, DiodeServiceModel.class);

        diodeService.loadDiodeInDatabase(diodeToBeSavedSM);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void saveDiodeInDatabaseTest(){
        this.diodeRepository.deleteAll();

        diodeService.saveDiodeInDatabase(diodeFirstSM);

        Diode expected = diodeFirst;
        Diode actual = this.diodeRepository.findAll().get(0);
        long actualSize = this.diodeRepository.count();
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
    public void findAllDiodesTest(){
        this.diodeRepository.deleteAll();

        diodeService.saveDiodeInDatabase(diodeFirstSM);
        diodeService.saveDiodeInDatabase(diodeSecondSM);

        Diode expectedFirst = diodeFirst;
        Diode expectedSecond = diodeSecond;
        Diode actualFirst = this.diodeRepository.findAll().get(0);
        Diode actualSecond = this.diodeRepository.findAll().get(1);
        long actualSize = this.diodeRepository.count();
        long expectedSize = 2;


        // базата данни връща записите в произволен ред
        if(!actualFirst.getPictureID().equals(expectedFirst.getPictureID())) {
            expectedFirst = diodeSecond;
            expectedSecond = diodeFirst;
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
    public void deleteDiodeByIdTest(){
        this.diodeRepository.deleteAll();

        diodeService.saveDiodeInDatabase(diodeFirstSM);
        Diode Diode = this.diodeRepository.findAll().get(0);
        long id = Diode.getId();

        diodeService.deleteDiodeById(id);

        long actualSizeOfRepository = this.diodeRepository.count();
        long expectedSizeOfRepository = 0;

        Assert.assertEquals(expectedSizeOfRepository, actualSizeOfRepository);

    }
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void findDiodeByIdTest(){
        this.diodeRepository.deleteAll();

        diodeService.saveDiodeInDatabase(diodeFirstSM);
        Diode Diode = this.diodeRepository.findAll().get(0);
        long id = Diode.getId();

        DiodeServiceModel actual = diodeService.findDiodeById(id);
        DiodeServiceModel expected = diodeFirstSM;

        Assert.assertEquals(expected.getPictureID(), actual.getPictureID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getMaxCurrent(), actual.getMaxCurrent());
        Assert.assertEquals(expected.getMaxVoltage(), actual.getMaxVoltage());

    }
//----------------------------------------------------------------------------------------------------------------------
}
