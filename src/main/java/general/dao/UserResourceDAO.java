package general.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import general.dom.*;

/**
 * Created by Pavel on 23.04.2017.
 */
public class UserResourceDAO {

    Connection connection;

    public UserResourceDAO(Connection connection) {
        this.connection = connection;
    }

//    final String selectUserResourcesWherePath = "SELECT * FROM GOOD_LINE_CLI_SCHEME.USER_RESOURCES WHERE" +
//            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
//            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";
        final String selectUserResourcesWherePath = "SELECT * FROM USER_RESOURCES WHERE" +
            "(USER_RESOURCES.USER_RESOURCE_PATH LIKE ?) AND " +
            "(USER_RESOURCES.USER_RESOURCE_ROLE LIKE ?)";

//    final String selectUserResourceId = "SELECT USER_RESOURCE_ID FROM GOOD_LINE_CLI_SCHEME.USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";
    final String selectUserResourceId = "SELECT USER_RESOURCE_ID FROM USER_RESOURCES WHERE USER_RESOURCES.USER_RESOURCE_PATH = ?";

  public UserResources getPathUserResource(String userResourcePath, String userRole) throws SQLException {

        try {
            PreparedStatement statement = connection.prepareStatement(selectUserResourcesWherePath);
            System.out.println(userResourcePath + ", " + userRole);
            statement.setString(1, userResourcePath);
            statement.setString(2, userRole);
            //statement.setInt(3, resourceId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                System.out.println(resultSet.getString("USER_RESOURCE_PATH")
                        + ", " + resultSet.getString("USER_RESOURCE_ROLE"));
                return new UserResources()
                        .setUserResResId(resultSet.getInt("USER_RESOURCE_ID"))
                        .setResourcePath(resultSet.getString("USER_RESOURCE_PATH"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }

  public  UserResources findIdRes(String path) throws SQLException{

      try {
          PreparedStatement statement = connection.prepareStatement(selectUserResourceId);
          statement.setString(1, path);
          ResultSet resultSet = statement.executeQuery();
          if(resultSet.next()){
              System.out.println(resultSet.getInt("USER_RESOURCE_ID"));
              return new UserResources()
                      .setUserResResId(resultSet.getInt("USER_RESOURCE_ID"));
          }

      } catch (SQLException e) {
          e.printStackTrace();
      }

      return null;

    }
}
