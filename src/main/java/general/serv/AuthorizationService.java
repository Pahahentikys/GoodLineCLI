package general.serv;

import general.ExitCodeType;
import general.dao.UserResourceDAO;
import general.dom.Accounting;
import general.dom.UserInputData;
import general.dom.UserResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class AuthorizationService {

    private static final Logger logger = LogManager.getLogger(AuthorizationService.class.getName());

    /**
     * Проверка на то, авторизован ли пользователь
     *
     * @param userResourceDAO        - перечень ресурсов, который получен выборкой из БД
     * @param userResourcePath       - пользовательский путь, который считывается с входных аргументов
     * @param userResourceRole       - пользовательская роль, которая считывается с входных аргументов
     * @param isUserAuthentification - проверка на то, аутентифицирован ли пользователь
     * @return - код: 3, если неправильная роль, код: 4, если нет доступа
     * @throws SQLException
     */
    public int isUserAuthorization(UserResourceDAO userResourceDAO, String userResourcePath, String userResourceRole, int isUserAuthentification) throws SQLException {
        logger.debug("Проверка на то, авторизован ли пользователь");
        DataValidator dataValidator = new DataValidator();
        DataBaseContext dataBaseContext = new DataBaseContext();
        if ((isUserAuthentification == ExitCodeType.SUCCESS.getExitCode()) && (userResourceRole != null) && (userResourcePath != null)) {
            if (dataValidator.isUserRoleValid(userResourceRole) == ExitCodeType.INVALID_ROLE.getExitCode()) {
                return ExitCodeType.INVALID_ROLE.getExitCode();
            }
            if (dataBaseContext.hasResUserAccessDAO(userResourceDAO, userResourcePath, userResourceRole) == ExitCodeType.INVALID_ACCESS.getExitCode()) {
                return ExitCodeType.INVALID_ACCESS.getExitCode();
            }
        }
        return ExitCodeType.SUCCESS.getExitCode();
    }


    /**
     * Метод для создания сеанса доступа к ресурсу для юзера, который фиксирует даты: начала доступа, окончания, а также объем потреблённых ресурсов и id ресурса.
     *
     * @param accounting      - объект для наполнения параметрами, который после будет помещаться в БД
     * @param userInputData   - входные параметры
     * @param userResourceDAO - слой доступа к БД
     * @throws SQLException
     */
    public void addAccounting(Accounting accounting, UserInputData userInputData, UserResourceDAO userResourceDAO) throws SQLException {
        logger.debug("Добавление пользовательского сеанса в БД");
        UserResources userResources = userResourceDAO.findIdRes(userInputData.getUserInputPathResource());
        accounting.withResourceId(userResources.getUserResResId());
        accounting.withStartAccountingDate(userInputData.getUserInputDs());
        accounting.withEndAccountingDate(userInputData.getUserInputDe());
        accounting.withVolumeOfUseRes(userInputData.getUserInputVol());
    }

    /**
     * Проверка на то, был ли пользовательский сеанс
     *
     * @param accounting          - сеанс пользователя
     * @param userResourceDAO     - слой доступа к БД
     * @param userInputData       - входные данные
     * @param dataValidator       - объект для валидации данных
     * @param isUserAuthorization - проверка на то, авторизован ли юзер
     * @return код: 5, если некорректная дата
     * @throws SQLException
     */
    public int isUserAccounting(Accounting accounting, UserResourceDAO userResourceDAO, UserInputData userInputData, DataValidator dataValidator, int isUserAuthorization) throws SQLException {
        logger.debug("Проверка на то, выполнен ли процесс аккаунтинга");
        if (isUserAuthorization == ExitCodeType.SUCCESS.getExitCode() && userInputData.getUserInputDs() != null) {
            if (!dataValidator.isDateDsAndDeValid(userInputData) || !dataValidator.isVolumeValid(userInputData)) {
              return ExitCodeType.INVALID_ACTION.getExitCode();
            }
            addAccounting(accounting, userInputData, userResourceDAO);
            return ExitCodeType.SUCCESS.getExitCode();
        }
        return ExitCodeType.INVALID_ACCESS.getExitCode();
    }
}
