import java.util.Arrays;
import java.util.List;

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

    /**
     * Коллекция ресуров приложения с набором тестовых данных.
     */
    private static List<Resource> allResources = Arrays.asList(
            new Resource().setResourceId(1).setResourcePath("A.B"),
            new Resource().setResourceId(2).setResourcePath("A.B.C"),
            new Resource().setResourceId(3).setResourcePath("A.B.C.D")
    );

    /**
     * Получить все ресурсы приложения
     * @return - коллекция объектов с ресурсами
     */
    public static List<Resource> getAllResources(){
        return allResources;
    }


}
