package general.dom;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInputData {

    @Getter
    @Setter
    private String userInputLogin;

    @Getter
    @Setter
    private String userInputPassword;

    @Getter
    @Setter
    private String userInputPathResource;

    @Getter
    @Setter
    private String userInputRole;

    @Getter
    @Setter
    private String userInputDs;

    @Getter
    @Setter
    private String userInputDe;

    @Getter
    @Setter
    private String userInputVol;

}
