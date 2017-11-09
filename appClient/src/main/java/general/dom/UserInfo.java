package general.dom;


import lombok.Builder;
import lombok.Getter;

@Builder
public class UserInfo {

    @Getter
    private int userId;

    @Getter
    private String userLogin;

    @Getter
    private String userHashPassword;

    @Getter
    private String userSalt;

}
