import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {

        //        /**
//         * Коллеция объектов с пользователями, которая нужна
//         * для формирования тестовых данных
//         */
//        List<UserInfo> usersInfo = Arrays.asList(
//                new UserInfo()
//                        .setUserId(1)
//                        .setUserLogin("userOne")
//                        .setUserSalt(RandomStringUtils.randomAscii(8, 8))
//                        .setUserHashPassword("111"),
//                new UserInfo()
//                        .setUserId(2)
//                        .setUserLogin("userTwo")
//                        .setUserSalt(RandomStringUtils.randomAscii(8, 8))
//                        .setUserHashPassword("111")
//        );
//        /**
//         * Получить всех пользователей
//         *
//         * @return - коллекция объектов с пользователями
//         */
//        public  List<UserInfo> getUsersinfo() {
//            return usersInfo;
//        }

        UserInfo userOne = new UserInfo();
        UserResources userResourceOne = new UserResources();
        UserInputData userInputData = new UserInputData();
        DataValidator dataValidator = new DataValidator();

        userOne.setUserId(1)
                .setUserLogin("userOne")
                .setUserSalt(RandomStringUtils.randomAscii(8))
                .setUserHashPassword("111");

        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        usersInfo.add(userOne);

        userResourceOne.setUserResId(1)
                .setUserResUserId(1)
                .setUserResResId(1)
                .setResourcePath("A.B")
                .setUserRole(UserRoles.EXECUTE);

        ArrayList<UserResources> usersResources = new ArrayList<>();
        usersResources.add(userResourceOne);


        }
    }





