package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;
//import org.hibernate.annotations.Entity;

@Entity
@Table(name = "User_Resources")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserResources {

    @Column(name="user_resource_id")
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @SerializedName("user_resource_id")
    @Expose
    private int userResourcesId;

    @ManyToOne
    @Expose
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @Column(name="user_resource_role")
    @SerializedName("user_role")
    @Enumerated(EnumType.STRING)
    @Expose
    @Getter
    private UserRoles userRole;

    @Column(name="user_resource_path")
    @SerializedName("resource_path")
    @Expose
    @Getter
    private String resourcePath;

    @Transient
    @Getter
    private int userResResId;
}
