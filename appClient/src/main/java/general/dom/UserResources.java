package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_resources")
@Builder
public class UserResources {

    @Column(name="user_resources_id")
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int userResourcesId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @Column(name="user_role")
    @SerializedName("user_role")
    @Expose
    @Getter
    private UserRoles userRole;

    @Column(name="resource_path")
    @SerializedName("resource_path")
    @Expose
    @Getter
    private String resourcePath;

    @Getter
    private int userResId;

    @Column(name="user_res_user_id")
    @SerializedName("user_id")
    @Expose
    @Getter
    private int userResUserId;

    @Getter
    private int userResResId;
}
