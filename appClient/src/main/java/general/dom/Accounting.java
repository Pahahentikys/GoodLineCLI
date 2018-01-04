package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="accounting")
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounting {
    /**
     * Идентификатор сеанса пользователя.
     */
    @Id
    @Column(name="accounting_id")
    @GeneratedValue
    @SerializedName("accounting_id")
    @Expose
    @Getter
    @Setter
    private int accountingId;

    @ManyToOne
    @JoinColumn(name="user_resources_id")
    @Getter
    @Setter
    private UserResources userResources;

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
    @Column(name = "volume_of_res")
    @SerializedName("volume_of_res")
    @Expose
    @Getter
    @Setter
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    @Column(name="start_accounting_date")
    @SerializedName("start_accounting_date")
    @Expose
    @Getter
    @Setter
    private String startAccountingDate;

    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    @Column(name="end_accounting_date")
    @SerializedName("end_accounting_date")
    @Expose
    @Getter
    @Setter
    private String endAccountingDate;

}
