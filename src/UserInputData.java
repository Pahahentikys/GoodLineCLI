/**
 * Created by Pavel on 09.03.2017.
 */
public class UserInputData {
    private String userInputLogin,
            userInputPassword,
            userInputPathResource,
            userInputRole,
            userInputDs,
            userInputDe,
            userInputVol;

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

    public UserInputData setUserInputLogin(String userInputLogin) {
        this.userInputLogin = userInputLogin;
        return this;
    }

    public UserInputData setUserInputPassword(String userInputPassword) {
        this.userInputPassword = userInputPassword;
        return this;
    }

    public UserInputData setUserInputPathResource(String userInputPathResource) {
        this.userInputPathResource = userInputPathResource;
        return this;
    }

    public UserInputData setUserInputRole(String userInputRole) {
        this.userInputRole = userInputRole;
        return this;
    }

    public UserInputData setUserInputDs(String userInputDs) {
        this.userInputDs = userInputDs;
        return this;

    }

    public UserInputData setUserInputDe(String userInputDe) {
        this.userInputDe = userInputDe;
        return this;
    }

    public UserInputData setUserInputVol(String userInputVol) {
        this.userInputVol = userInputVol;
        return this;
    }
}