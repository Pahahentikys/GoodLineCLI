/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import java.text.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
     * Проверка даты на соответтствие шаблону ГГГГ-ММ-ДД, если соответствие есть, выполнить конфертацию в Date
     * @param userInputData - входные данные
     * @return - null, если дата с инупта не совпала с шаблоном
     * @throws java.text.ParseException - кидается исключение, если дата, которую ввёл юзер, не соответствует шаблону
     */
    public static Date[] DateStartAndEndParse(UserInputData userInputData) throws java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

        return new Date[]{
                dateFormat.parse(userInputData.getUserInputDs()),
                dateFormat.parse(userInputData.getUserInputDe())
        };

    }

}
