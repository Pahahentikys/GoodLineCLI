/**
 * Created by Pavel on 12.03.2017.
 */

import java.util.Date;

public class Accounting {
    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    private int resourceUserId;
    /**
     * Объём ресурсов, потреблённых юзером.
     */
    private String volumeOfUseRes;

    /**
     * Дата, по которой началось пользование ресурсом.
     */
    private String startAccountingDate;
    /**
     * Дата, по которой закончилось пользование ресурсом.
     */
    private String endAccountingDate;

    public int getResourceUserId() {
        return resourceUserId;
    }

    public Accounting setResourceUserId(int resourceUserId) {
        this.resourceUserId = resourceUserId;
        return this;
    }

    public String getVolumeOfUseRes() {
        return volumeOfUseRes;
    }

    public Accounting setVolumeOfUseRes(String volumeOfUseRes) {
        this.volumeOfUseRes = volumeOfUseRes;
        return this;
    }

    public String getStartAccountingDate() {
        return startAccountingDate;
    }

    public Accounting setStartAccountingDate(String startAccountingDate) {
        this.startAccountingDate = startAccountingDate;
        return this;
    }

    public String getEndAccountingDate() {
        return endAccountingDate;
    }

    public Accounting setEndAccountingDate(String endAccountingDate) {
        this.endAccountingDate = endAccountingDate;
        return this;
    }
}
