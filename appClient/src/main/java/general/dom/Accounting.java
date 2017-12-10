package general.dom;

import com.google.gson.annotations.Expose;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounting {
    /**
     * Идентификатор сеанса пользователя.
     */
    @Expose
    @Getter
    @Setter
    private int accountingId;

    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    @Expose
    @Getter
    @Setter
    private int resourceId;

    /**
     * Объём ресурсов, потреблённых юзером.
     */
    @Expose
    @Getter
    @Setter
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    @Expose
    @Getter
    @Setter
    private String startAccountingDate;

    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    @Expose
    @Getter
    @Setter
    private String endAccountingDate;

}
