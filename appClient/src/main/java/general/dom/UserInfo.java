package general.dom;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder
public class UserInfo {

    @SerializedName("user_id")
    @Expose
    @Getter
    private int userId;

    @SerializedName("user_login")
    @Expose
    @Getter
    private String userLogin;

    @Getter
    private String userHashPassword;

    @Getter
    private String userSalt;

}
