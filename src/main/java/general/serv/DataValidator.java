package general.serv;

import general.dom.UserInputData;
import general.dom.UserRoles;
import org.apache.commons.cli.*;

import java.time.LocalDate;
import java.util.Locale;


public class DataValidator {
    enum ComLineOptions {
        HELP,
        LOGIN,
        PASS,
        RES,
        ROLE,
        DS,
        DE,
        VOL;

        @Override
        public String toString() {
            String ComLineOptions = name().toLowerCase(Locale.US);
            return ComLineOptions;
        }
    }

    public Options generateOptions() {
        Options options = new Options();
        options.addOption("h", String.valueOf(ComLineOptions.HELP), true, "Help menu: ");
        options.addOption("login", String.valueOf(ComLineOptions.LOGIN), true, "Login : ");
        options.addOption("pass", String.valueOf(ComLineOptions.PASS), true, "Password: ");
        options.addOption("res", String.valueOf(ComLineOptions.RES), true, "Resource: ");
        options.addOption("role", String.valueOf(ComLineOptions.ROLE), true, "Access: ");
        options.addOption("ds", String.valueOf(ComLineOptions.DS), true, "Date start to access: ");
        options.addOption("de", String.valueOf(ComLineOptions.DE), true, "Date end to access: ");
        options.addOption("vol", String.valueOf(ComLineOptions.VOL), true, "Volume: ");
        return options;
    }

    public UserInputData getUserInputData(UserInputData userInputData, String[] args) {
        try {
            CommandLine commandLine = new DefaultParser().parse(generateOptions(), args);
            userInputData.setUserInputLogin(commandLine.getOptionValue(String.valueOf(ComLineOptions.LOGIN)));
            userInputData.setUserInputPassword(commandLine.getOptionValue(String.valueOf(ComLineOptions.PASS)));
            userInputData.setUserInputPathResource(commandLine.getOptionValue(String.valueOf(ComLineOptions.RES)));
            userInputData.setUserInputRole(commandLine.getOptionValue(String.valueOf(ComLineOptions.ROLE)));
            userInputData.setUserInputDs(commandLine.getOptionValue(String.valueOf(ComLineOptions.DS)));
            userInputData.setUserInputDe(commandLine.getOptionValue(String.valueOf(ComLineOptions.DE)));
            userInputData.setUserInputVol(commandLine.getOptionValue(String.valueOf(ComLineOptions.VOL)));

            if (userInputData.getUserInputLogin() == null || userInputData.getUserInputPassword() == null) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Help menu: ", generateOptions());
                System.exit(0);
            }

        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Help menu: ", generateOptions());
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
    public boolean isUserRoleValid(UserInputData userInpData) {
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
    public boolean isDateDsAndDeValid(UserInputData userInputData) {
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
    public boolean isVolumeValid(UserInputData userInputData) {
        try {
            String testVol = userInputData.getUserInputVol();
            Integer.valueOf(testVol);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}