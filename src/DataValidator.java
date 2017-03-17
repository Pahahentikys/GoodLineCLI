/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import java.time.LocalDate;

public class DataValidator {
    public static final String HELP = "help";
    public static final String LOGIN = "login";
    public static final String PASS = "pass";
    public static final String RES = "res";
    public static final String ROLE = "role";
    public static final String DS = "ds";
    public static final String DE = "de";
    public static final String VOL = "vol";
    private static CommandLine commandLine;


    public static Options generateOptions() {
        Options options = new Options();
        options.addOption("h", HELP, true, "Help menu: ");
        options.addOption("login", LOGIN, true, "Login : ");
        options.addOption("pass", PASS, true, "Password: ");
        options.addOption("res", RES, true, "Resource: ");
        options.addOption("role", ROLE, true, "Access: ");
        options.addOption("ds", DS, true, "Date start to access: ");
        options.addOption("de", DE, true, "Date start to access: ");
        options.addOption("vol", VOL, true, "Volume: ");
        return options;
    }

    public static UserInputData getUserInputData(UserInputData userInputData, String[] args) {
        try {
            commandLine = new DefaultParser().parse(generateOptions(), args);
            userInputData.setUserInputLogin(commandLine.getOptionValue("login"));
            userInputData.setUserInputPassword(commandLine.getOptionValue("pass"));
            userInputData.setUserInputPathResource(commandLine.getOptionValue("res"));
            userInputData.setUserInputRole(commandLine.getOptionValue("role"));
            userInputData.setUserInputDs(commandLine.getOptionValue("ds"));
            userInputData.setUserInputDe(commandLine.getOptionValue("de"));
            userInputData.setUserInputVol(commandLine.getOptionValue("vol"));

            if (userInputData.getUserInputLogin() == null || userInputData.getUserInputPassword() == null) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Help menu: ", generateOptions());
                System.exit(0);
            }

        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Показать справочную информацию: ", generateOptions());
            System.exit(0);
        }

        return userInputData;
    }

    /**
     * Проверка корректности значения роли
     *
     * @param userInpData - данные, которые идут на вход в консоль
     * @return - true в том случае, если роль существует
     */
    public static boolean isUserRoleValid(UserInputData userInpData) {
        for (UserRoles role : UserRoles.values()) {
            if (role.name().equals((userInpData.getUserInputRole())))
                return true;
        }
        return false;
    }

    /**
     * Конвертация в даты в формат ГГГГ-ММ-ДД
     *
     * @param userInputData - входные данные
     * @return - true, если конвертация прошла успешно
     */
    public static boolean isDateDsAndDeValid(UserInputData userInputData) {
        try {
            LocalDate.parse(userInputData.getUserInputDs());
            LocalDate.parse(userInputData.getUserInputDe());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Приведение объёма используемых юзером ресурсов к целому числу
     *
     * @param userInputData - входные данные
     * @return - true, если число в целое
     */
    public static boolean isVolumeValid(UserInputData userInputData) {
        try {
            String testVol = userInputData.getUserInputVol();
            Integer.valueOf(testVol);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}