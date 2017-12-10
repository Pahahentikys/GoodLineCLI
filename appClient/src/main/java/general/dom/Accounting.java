package general.dom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Accounting {
    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    @Getter
    @Setter
    private int resourceId;

    /**
     * Объём ресурсов, потреблённых юзером.
     */
    @Getter
    @Setter
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    @Getter
    @Setter
    private String startAccountingDate;

    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    @Getter
    @Setter
    private String endAccountingDate;

}
