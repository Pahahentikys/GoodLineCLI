import com.google.inject.Provider;

import java.sql.Connection;

public class DataBaseConnectionProvider implements Provider<Connection> {
    @Override
    public Connection get() {
        return null;
    }
}
