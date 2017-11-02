import general.serv.DataValidator;
import org.junit.Before;
import org.junit.Test;

public class HelpMenuTest {

    private DataValidator dataValidator;

    @Before
    public void setDefaultParamsForTests() {

        dataValidator = new DataValidator();
    }

    @Test
    public void testHelpMenu() {

        dataValidator.getUserInputData(new String[]{"-h"});

    }

    @Test
    public void testHelpMenuWithNoParams() {

        dataValidator.getUserInputData(new String[0]);
    }
}
