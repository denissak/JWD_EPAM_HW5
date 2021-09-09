import bean.Device;
import bean.Type;
import exception.ParseException;
import org.junit.Assert;
import org.junit.Test;
import parser.DomParser;
import parser.Parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class DomParserTest extends Assert {

    @Test

    public void domTest(){
        Device investment = Device.create()
                .setId(1)
                .setName("AMD Ryzen 5 5600 X")
                .setOrigin("China")
                .setPrice((float)100.0)
                .setType(Type.create()
                        .setIsPeriferal(false)
                        .setEnergyConsumption(76)
                        .setIsCooler(false)
                        .setGroupofcomponent("processor")
                        .setPort("socket")
                        .build())
                .setIsCritical(true)
                .build();
        Parse parser = new DomParser();
        try {
            List<Device> investments = parser.pars(
                    new FileInputStream("D:\\java\\JWD_EPAM_TASK5\\src\\main\\resources"));
            assertEquals(investment, investments.get(0));
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }
}
