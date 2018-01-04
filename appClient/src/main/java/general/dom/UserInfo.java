package general.dom;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@Builder
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    @Getter
    private int userId;

    @Column(name="user_login")
    @SerializedName("user_login")
    @Expose
    @Getter
    private String userLogin;

    @Column(name = "user_hash_password")
    @Getter
    private String userHashPassword;

    @Column(name = "user_salt")
    @Getter
    private String userSalt;

}
