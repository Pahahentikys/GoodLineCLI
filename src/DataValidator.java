/**
 * Created by Pavel on 09.03.2017.
 */

import org.apache.commons.cli.*;

public class DataValidator {
    private static CommandLine commandLine;
    private static Options options = new Options();
    CommandLineParser commandLineParser = new DefaultParser();


    public Options generateOptions() {
        options.addOption("h", "help", true, "Показать справочную информацию!");
        options.addOption("login", "login", true, "Логин: ");
        options.addOption("pass", "pass", true, "Пароль: ");
        options.addOption("res", "res", true, "Ресурс для доступа: ");
        options.addOption("role", "role", true, "Права доступа к ресурсу: ");
        options.addOption("ds", "ds", true, "Начало доступа к ресурсу: ");
        options.addOption("de", "de", true, "Конец доступа к ресурсу: ");
        options.addOption("vol", "vol", true, "Потреблённые ресурсы: ");
        return options;
    }


}
