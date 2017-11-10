import general.ExitCodeType;
import general.dao.AccountingDAO;
import general.dao.UserInfoDAO;
import general.dao.UserResourceDAO;
import general.dom.*;
import general.serv.AuthenticationService;
import general.serv.AuthorizationService;
import general.serv.DataValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AccountingServiceTest {

    // Класс, отвечающий за валидацию дат и объёма.
    private DataValidator dataValidator;

    @Before
    public void setDefaultParamsForTests() throws SQLException {

        dataValidator = new DataValidator();
    }

    @Test
    public void accountingTestInvalidDate() throws SQLException {

        UserInputData userInputData =  UserInputData.builder()
                .userInputDs("xxx-10-19")
                .userInputDe("2017-10-19")
                .build();

        assertEquals(ExitCodeType.INVALID_ACTION.getExitCode(), dataValidator.isDateDsAndDeValid(userInputData));
    }

    @Test
    public void accountingTestInvalidVolume() throws SQLException {

        UserInputData userInputData =  UserInputData.builder()
                .userInputVol("xxx")
                .build();

        assertEquals(ExitCodeType.INVALID_ACTION.getExitCode(), dataValidator.isVolumeValid(userInputData));
    }

    @Test
    public void accountingTestValidDates() throws SQLException {

        UserInputData userInputData = UserInputData.builder()
                .userInputDs("2017-10-19")
                .userInputDe("2017-10-19")
                .build();

        assertEquals(ExitCodeType.SUCCESS.getExitCode(), dataValidator.isDateDsAndDeValid(userInputData));
    }

    @Test
    public void accountingTestValidVolume() throws SQLException {

        UserInputData userInputData = UserInputData.builder()
                .userInputVol("100")
                .build();

        assertEquals(ExitCodeType.SUCCESS.getExitCode(), dataValidator.isVolumeValid(userInputData));
    }
}
