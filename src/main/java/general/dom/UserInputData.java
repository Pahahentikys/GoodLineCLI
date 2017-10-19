package general.dom;

public class UserInputData {

    int userInputId;
    private String userInputLogin;
    private String userInputPassword;
    private String userInputPathResource;
    private String userInputRole;
    private String userInputDs;
    private String userInputDe;
    private String userInputVol;

    public int getUserInputId() {
        return userInputId;
    }

    public String getUserInputLogin() {
        return userInputLogin;
    }

    public String getUserInputPassword() {
        return userInputPassword;
    }

    public String getUserInputPathResource() {
        return userInputPathResource;
    }

    public String getUserInputRole() {
        return userInputRole;
    }

    public String getUserInputDs() {
        return userInputDs;
    }

    public String getUserInputDe() {
        return userInputDe;
    }

    public String getUserInputVol() {
        return userInputVol;
    }

    public UserInputData withUserInputId(int userInputId) {
        this.userInputId = userInputId;
        return this;
    }

    public UserInputData withUserInputLogin(String userInputLogin) {
        this.userInputLogin = userInputLogin;
        return this;
    }

    public UserInputData withUserInputPassword(String userInputPassword) {
        this.userInputPassword = userInputPassword;
        return this;
    }

    public UserInputData withUserInputPathResource(String userInputPathResource) {
        this.userInputPathResource = userInputPathResource;
        return this;
    }

    public UserInputData withUserInputRole(String userInputRole) {
        this.userInputRole = userInputRole;
        return this;
    }

    public UserInputData withUserInputDs(String userInputDs) {
        this.userInputDs = userInputDs;
        return this;

    }

    public UserInputData withUserInputDe(String userInputDe) {
        this.userInputDe = userInputDe;
        return this;
    }

    public UserInputData withUserInputVol(String userInputVol) {
        this.userInputVol = userInputVol;
        return this;
    }

    public UserInputData() {

    }

    public UserInputData(String userInputLogin, String userInputPassword) {
        this.userInputLogin = userInputLogin;
        this.userInputPassword = userInputPassword;
    }

    public UserInputData(String userInputLogin, String userInputPassword, String userInputRole, String userInputPathResource) {
        this.userInputLogin = userInputLogin;
        this.userInputPassword = userInputPassword;
        this.userInputRole = userInputRole;
        this.userInputPathResource = userInputPathResource;
    }

}
