package com.nanvir.templateapps.module.tapwallet.constants;

import java.util.HashMap;
import java.util.Map;

public class UserConstants {

    private static final Integer[] COMMON_MENUS = new Integer[] {null,1,0,null,4};
    private static final Integer[] MASTER_MENUS = new Integer[] {2,1,0,3,4};
    private static final Integer[] SALES_MENUS = new Integer[] {2,1,0,3,4};
    private static final Integer[] GUEST_MENUS = new Integer[] {2,1,0,3,4};

    public enum UserMenus {
        COMMON_MENUS(UserConstants.COMMON_MENUS),
        MASTER_MENUS(UserConstants.MASTER_MENUS),
        SALES_MENUS(UserConstants.SALES_MENUS),
        GUEST_MENUS(UserConstants.GUEST_MENUS);

        private Integer[] userMenu;

        UserMenus(Integer[] userMenu) {
            this.userMenu = userMenu;
        }

        public Integer[] getUserMenu() {
            return userMenu;
        }
    }

    public enum Users {
        PRESIDENT_DIRECTOR(1, true, UserMenus.MASTER_MENUS),
        HUMAN_RESOURCES(2, false, UserMenus.COMMON_MENUS),
        MARKETING_SALES(3, false, UserMenus.SALES_MENUS),
        BUSINESS_INTELLIGENCE(4, false, UserMenus.COMMON_MENUS),
        DATABASE_MANAGEMENT(5, false, UserMenus.COMMON_MENUS),
        INFORMATION_TECHNOLOGY(21, false, UserMenus.COMMON_MENUS);

        private final int groupPosId;
        private final boolean isMasterUser;
        private final UserMenus userMenus;

        Users(int groupPosId, boolean isMasterUser, UserMenus userMenus) {
            this.groupPosId = groupPosId;
            this.isMasterUser = isMasterUser;
            this.userMenus = userMenus;
        }

        public int getGroupPosId() {
            return groupPosId;
        }

        private static final Map<Integer, Users> map = new HashMap<>();

        static {
            for (Users user : Users.values()) {
                map.put(user.groupPosId, user);
            }
        }

        public static Users getByValue(int value) {
            return map.get(value);
        }

        public boolean isMasterUser() {
            return isMasterUser;
        }

        public UserMenus getUserMenus() {
            return userMenus;
        }
    }
}
