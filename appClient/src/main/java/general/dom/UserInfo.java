package general.dom;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    @Getter
    private int userId;

    @Column(name = "user_login")
    @SerializedName("user_login")
    @Expose
    @Getter
    private String userLogin;

    @Column(name = "user_name")
    @SerializedName("user_name")
    @Expose
    @Getter
    private String userName;

    @Column(name = "user_pass_hash")
    @Getter
    private String userHashPassword;

    @Column(name = "user_salt")
    @Getter
    private String userSalt;

}
