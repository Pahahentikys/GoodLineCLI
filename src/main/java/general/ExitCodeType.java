package general;

public enum ExitCodeType {
    INVALID_LOGIN(1),
    INVALID_PASSWORD(2),
    INVALID_ROLE(3),
    INVALID_ACTION(5),
    INVALID_ACCESS(4),
    SUCCESS(0);

    private int exitCode;

    public int getExitCode() {
        return exitCode;
    }

    ExitCodeType(int exitCode) {
        this.exitCode = exitCode;
    }
}

