package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounting {
    /**
     * Идентификатор сеанса пользователя.
     */
    @SerializedName("accounting_id")
    @Expose
    @Getter
    @Setter
    private int accountingId;

    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    @SerializedName("resource_id")
    @Expose
    @Getter
    @Setter
    private int resourceId;

    /**
     * Объём ресурсов, потреблённых юзером.
     */
    @SerializedName("volume_of_res")
    @Expose
    @Getter
    @Setter
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    @SerializedName("start_accounting_date")
    @Expose
    @Getter
    @Setter
    private String startAccountingDate;

    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    @SerializedName("end_accounting_date")
    @Expose
    @Getter
    @Setter
    private String endAccountingDate;

}
