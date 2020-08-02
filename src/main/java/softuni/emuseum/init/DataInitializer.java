package softuni.emuseum.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.emuseum.entities.Capacitor;
import softuni.emuseum.entities.Diode;
import softuni.emuseum.entities.Resistor;
import softuni.emuseum.entities.Transistor;
import softuni.emuseum.services.api.CapacitorService;
import softuni.emuseum.services.api.DiodeService;
import softuni.emuseum.services.api.ResistorService;
import softuni.emuseum.services.api.TransistorService;

@Component
public class DataInitializer implements CommandLineRunner{

    private final TransistorService transistorService;
    private final ResistorService resistorService;
    private final CapacitorService capacitorService;
    private final DiodeService diodeService;

    public DataInitializer(TransistorService transistorService, ResistorService resistorService, CapacitorService capacitorService, DiodeService diodeService) {
        this.transistorService = transistorService;
        this.resistorService = resistorService;
        this.capacitorService = capacitorService;
        this.diodeService = diodeService;
    }


    Transistor transistorOne = new Transistor("1hUfxKmAPlHPGRCue_DbzMLFqeCYTPTYW", "Transistor 2T3169. Produced in Bulgaria. Low noise, general purpose, low power.",
                                                "NPN", "25V", "100mA", "150MHz", "180-850");

    Transistor transistorTwo = new Transistor("1zJkg-Il0Xfau0OCC5l46Ifij2t4UtUkN", "Transistor 2T6551. Produced in Bulgaria. Low frequency, general purpose, low power.",
                                                "NPN", "75V", "500mA", "200MHz", "26-470");

    Transistor transistorThree = new Transistor("1pvgvv15PmUYd4fGAYQK2t1CjyZ8QQaMG", "Transistor 2T7055. Produced in Bulgaria. Low frequency, general purpose, high power.",
                                                "NPN", "100V", "15A", "2.5MHz", "20-250");

    Transistor transistorFour = new Transistor("1RJA7ek6fOV9LeHJ0g55W7RK8kp21gyuD", "Transistor 2T9136. Produced in Bulgaria. Low frequency, general purpose, middle power.",
                                                "PNP", "45V", "1A", "40MHz", "40-250");

    Transistor transistorFive = new Transistor("1UtwRaRkNTH6VkKEYnu6EUJ9sbfZTOfpW", "Transistor SFT308. Produced in Bulgaria. Low frequency, general purpose, low power.",
                                                "PNP", "18V", "100mA", "10MHz", "70");

    Resistor resistorOne = new Resistor("1ZsQ-f8hBKkpiZ9WmSLP2bskk0rK_xvE-", "Produced in USSR. Resistor model С5-5В-1В.", "Wired", "3.3 Om", "1W", "5 percent" );
    Resistor resistorTwo = new Resistor("1HeUFXBBRfPWkSamGYA2iJE-75m06pobG", "Produced in Bulgaria. Resistor model РПМ2.", "Metal film", "22 Om", "0.25W", "5 percent" );
    Resistor resistorThree = new Resistor("1liltkMKjcEACURtLP0UMWD0UignevLBX", "Produced in Bulgaria. Resistor model ПВА-С II-Б.", "Carbon film", "220 Om", "2W", "5 percent" );
    Resistor resistorFour = new Resistor("1FuaVM0xlvE8K9Rk9GO-zWVidpbiiY8_4", "Produced in Bulgaria. Resistor model СП5-2.", "Wired", "10 kOm", "1W", "5 percent" );
    Resistor resistorFive = new Resistor("1TjkNCxBhOk1olm7wS0Wv_p-AaQ-MTTZj", "Produced from Tesla. Resistor model TR161 T308.", "Metal film", "100 Om", "0.25W", "1 percent" );

    Capacitor capacitorOne = new Capacitor("1P66qO_nXkcjQ0pTJUd6PL6R7tT_XOFGw", "Produced in Bulgaria. Capacitor model ККрД.", "Ceramic disk", "4.7nF", "100V");
    Capacitor capacitorTwo = new Capacitor("1nnF7BGdLVXkXs97_Uh8T34mDbByDNs21", "Produced in Bulgaria. Capacitor model MPT-221.", "Polyester Multilayer", "6.8nF", "630V");
    Capacitor capacitorThree = new Capacitor("1KiaDgd1hDbw-BCFqlX8J8NMwFNYRPaje", "Produced in USSR. Capacitor model КПМ-М.", "Ceramic trimer", "8-30pF", "300V");
    Capacitor capacitorFour = new Capacitor("1D4GY1ViCioZara6wXX7LBmeuO9bWncX5", "Produced in Bulgaria. Capacitor model КрМП.", "Ceramic disk", "15pF", "63V");
    Capacitor capacitorFive = new Capacitor("1OK0nguvl7gnfUPsHo61mPxtnlWRzgJ20", "Produced in Bulgaria. Capacitor model KEA-II.", "Electrolytic", "100microF", "10V");

    Diode diodeOne = new Diode("1k2OU3IxYfGEFt8r1aRY_v67EHdQuGDvn", "Produced in Bulgaria. Diode model 2Д2402.", "Rectifier.", "100V", "1A");
    Diode diodeTwo = new Diode("1Gs8cDnZ8P2uTcJmxI3HsWPy8VOjH8I4Q", "Produced in Bulgaria. Diode model 2Д5607.", "Fast switch.", "60V", "50mA");
    Diode diodeThree = new Diode("1-ZlBfptp2ki2eG801PukNRfvEsd56Dys", "Produced in Bulgaria. Diode model 3E2013.", "Light diode.", "3V", "50mA");
    Diode diodeFour = new Diode("1DZNgOD58f34QiBvg2_xvEEK5hCwZFUgw", "Produced in Bulgaria. Diode model Д2-25-5.", "Power rectifier.", "500V", "25A");
    Diode diodeFive = new Diode("16jK4U-wBEYBH71AQp12hy9s5abQQLZmt", "Produced in Bulgaria. Diode model КД1-102.", "Rectifier.", "200V", "300mA");

    @Override
    public void run(String... args) throws Exception {

        this.transistorService.loadTransistorInDatabase(transistorOne);
        this.transistorService.loadTransistorInDatabase(transistorTwo);
        this.transistorService.loadTransistorInDatabase(transistorThree);
        this.transistorService.loadTransistorInDatabase(transistorFour);
        this.transistorService.loadTransistorInDatabase(transistorFive);

        this.resistorService.loadResistorInDatabase(resistorOne);
        this.resistorService.loadResistorInDatabase(resistorTwo);
        this.resistorService.loadResistorInDatabase(resistorThree);
        this.resistorService.loadResistorInDatabase(resistorFour);
        this.resistorService.loadResistorInDatabase(resistorFive);

        this.capacitorService.loadCapacitorInDatabase(capacitorOne);
        this.capacitorService.loadCapacitorInDatabase(capacitorTwo);
        this.capacitorService.loadCapacitorInDatabase(capacitorThree);
        this.capacitorService.loadCapacitorInDatabase(capacitorFour);
        this.capacitorService.loadCapacitorInDatabase(capacitorFive);

        this.diodeService.loadDiodeInDatabase(diodeOne);
        this.diodeService.loadDiodeInDatabase(diodeTwo);
        this.diodeService.loadDiodeInDatabase(diodeThree);
        this.diodeService.loadDiodeInDatabase(diodeFour);
        this.diodeService.loadDiodeInDatabase(diodeFive);



    }
}
