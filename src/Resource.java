/**
 * Created by Pavel on 14.03.2017.
 */
public class Resource {
   private int resourceId;
    private String resourcePath;

    public int getResourceId() {
        return resourceId;
    }

    public Resource setResourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public Resource setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        return this;
    }
}
