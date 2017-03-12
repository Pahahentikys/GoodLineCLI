/**
 * Created by Pavel on 12.03.2017.
 */
import java.util.Date;
public class Accounting {
    /**
     * Идентификатор ресурса, к которому обращается пользователь. Объём ресурсов, потреблённых юзером.
     */
    private int resourceUserId, volumeOfUseRes;
    /**
     * Дата, по которой началось пользование ресурсом. Дата, по которой закончилось пользование ресурсом.
     */
    private Date startAccountingDate, endAccountingDate;

    public int getResourceUserId() {
        return resourceUserId;
    }

    public Accounting setResourceUserId(int resourceUserId) {
        this.resourceUserId = resourceUserId;
        return this;
    }

    public int getVolumeOfUseRes() {
        return volumeOfUseRes;
    }

    public Accounting setVolumeOfUseRes(int volumeOfUseRes) {
        this.volumeOfUseRes = volumeOfUseRes;
        return this;
    }

    public Date getStartAccountingDate() {
        return startAccountingDate;
    }

    public Accounting setStartAccountingDate(Date startAccountingDate) {
        this.startAccountingDate = startAccountingDate;
        return this;
    }

    public Date getEndAccountingDate() {
        return endAccountingDate;
    }

    public Accounting setEndAccountingDate(Date endAccountingDate) {
        this.endAccountingDate = endAccountingDate;
        return this;
    }
}
