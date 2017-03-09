/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.cli.*;

public class DataValidator {
    private static CommandLine commandLine;
    Options options = new Options();
    UserInputData userInputData = new UserInputData();
    CommandLineParser commandLineParser = new DefaultParser();


    public Options generateOptions() {
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

    public UserInputData getUserInputData(String[] args) {
        try {
            userInputData = new UserInputData();
            commandLine = commandLineParser.parse(options, args);
            userInputData.setUserInputLogin(commandLine.getOptionValue("login"));
            userInputData.setUserInputPassword(commandLine.getOptionValue("pass"));
            userInputData.setUserInputPathResource(commandLine.getOptionValue("res"));
            userInputData.setUserInputRole(commandLine.getOptionValue("role"));
            userInputData.setUserInputDs(commandLine.getOptionValue("ds"));
            userInputData.setUserInputDe(commandLine.getOptionValue("de"));
            userInputData.setUserInputVol(commandLine.getOptionValue("vol"));

        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Показать справочную информацию: ", options);
            System.exit(0);
        }

        return null;
    }

}
