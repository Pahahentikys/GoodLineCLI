package general.dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="User_Seans")
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounting {
    /**
     * Идентификатор сеанса пользователя.
     */
    @Id
    @Column(name="user_seans_id")
    @GeneratedValue
    @SerializedName("accounting_id")
    @Expose
    @Getter
    @Setter
    private int accountingId;

    @ManyToOne
    @JoinColumn(name="user_resource_id")
    @Getter
    @Setter
    private UserResources userResources;

    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    @Transient
    @SerializedName("resource_id")
    @Expose
    @Getter
    @Setter
    private int resourceId;

    /**
     * Объём ресурсов, потреблённых юзером.
     */
    @Column(name = "user_seans_volume")
    @SerializedName("volume_of_res")
    @Expose
    @Getter
    @Setter
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    @Column(name="user_seans_date_start")
    @SerializedName("start_accounting_date")
    @Expose
    @Getter
    @Setter
    private String startAccountingDate;

    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    @Column(name="user_seans_date_end")
    @SerializedName("end_accounting_date")
    @Expose
    @Getter
    @Setter
    private String endAccountingDate;

}
