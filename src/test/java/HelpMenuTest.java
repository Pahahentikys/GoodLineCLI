import general.dom.UserInputData;
import general.serv.AuthenticationService;
import general.serv.DataValidator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Pavel on 19.10.2017.
 */
public class HelpMenuTest {

    private UserInputData userInputData;

    private DataValidator dataValidator;

    private AuthenticationService authenticationService;


    @Before
    public void setDefaultParamsForTests() {

        userInputData = new UserInputData();

        dataValidator = new DataValidator();

        authenticationService = new AuthenticationService();
    }

    @Test
    public void testHelpMenu() {

        dataValidator.getUserInputData(userInputData, new String[]{"-h"});

    }

    @Test
    public void testHelpMenuWithNoParams() {

        dataValidator.getUserInputData(userInputData, new String[0]);
    }
}
