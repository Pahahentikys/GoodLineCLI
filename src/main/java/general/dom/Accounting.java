package general.dom;

public class Accounting {
    /**
     * Идентификатор ресурса, к которому обращается пользователь.
     */
    private int resourceId;
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

    public String getVolumeOfUseRes() {
        return volumeOfUseRes;
    }
    public String getStartAccountingDate() {
        return startAccountingDate;
    }
    public String getEndAccountingDate() {
        return endAccountingDate;
    }
    public int getResourceId() {
        return resourceId;
    }

    public Accounting withVolumeOfUseRes(String volumeOfUseRes) {
        this.volumeOfUseRes = volumeOfUseRes;
        return this;
    }
    public Accounting withStartAccountingDate(String startAccountingDate) {
        this.startAccountingDate = startAccountingDate;
        return this;
    }
    public Accounting withEndAccountingDate(String endAccountingDate) {
        this.endAccountingDate = endAccountingDate;
        return this;
    }
    public Accounting withResourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }
}
