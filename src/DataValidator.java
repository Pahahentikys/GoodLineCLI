/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.cli.*;

import java.util.ArrayList;

public class DataValidator {
    private static CommandLine commandLine;


    public static Options generateOptions() {
        Options options = new Options();
        options.addOption("h", "help", true, "Показать справочную информацию");
        options.addOption("login", "login", true, "Логин: ");
        options.addOption("pass", "pass", true, "Пароль: ");
        options.addOption("res", "res", true, "Ресурс для доступа: ");
        options.addOption("role", "role", true, "Права доступа к ресурсу: ");
        options.addOption("ds", "ds", true, "Начало доступа к ресурсу: ");
        options.addOption("de", "de", true, "Конец доступа к ресурсу: ");
        options.addOption("vol", "vol", true, "Потреблённые ресурсы: ");
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

        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Показать справочную информацию: ", generateOptions());
            System.exit(0);
        }

        return userInputData;
    }

    /**
     * Проверка на то, аутентифицирован ли пользователь
     *
     * @param usersList     - коллекция пользователей
     * @param userInputData - объект, хранящий в себе входные параметры
     * @return - true, если верный логин и пароль
     */
    boolean isUserAuthentification(ArrayList<UserInfo> usersList, UserInputData userInputData) {
        if (!AuthentifAndAuthorizService.isGetUserLogin(usersList, userInputData)) {
            System.exit(1);
        }

        if (!AuthentifAndAuthorizService.isGetUserPassword(usersList, userInputData)) {

            System.exit(2);

        }
        return true;
    }

}
