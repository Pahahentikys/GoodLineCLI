package general.dom;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UserResources {

    @Getter
    private UserRoles userRole;

    @Getter
    private String resourcePath;

    @Getter
    private int userResId;

    @Getter
    private int userResUserId;

    @Getter
    private int userResResId;
}
