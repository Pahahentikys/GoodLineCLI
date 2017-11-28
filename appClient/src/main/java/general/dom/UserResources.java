package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder
public class UserResources {

    @SerializedName("user_role")
    @Expose
    @Getter
    private UserRoles userRole;

    @SerializedName("resource_path")
    @Expose
    @Getter
    private String resourcePath;

    @Getter
    private int userResId;

    @SerializedName("user_id")
    @Expose
    @Getter
    private int userResUserId;

    @Getter
    private int userResResId;
}
