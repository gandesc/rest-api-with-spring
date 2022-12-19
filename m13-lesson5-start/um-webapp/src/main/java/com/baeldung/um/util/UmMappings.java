package com.baeldung.um.util;

public final class UmMappings {

    public static final String USERS = "api/users";
    public static final String PRIVILEGESx = "api/v{version:[1,2]}/privileges";
    public static final String PRIVILEGES1 = "api/v1/privileges";
    public static final String PRIVILEGES2 = "api/v2/privileges";
    public static final String ROLES = "api/roles";

    // discoverability

    public static final class Singular {

        public static final String USER = "api/user";
        public static final String PRIVILEGE = "api/privilege";
        public static final String ROLE = "api/role";
    }

    public static final String AUTHENTICATION = "authentication";

    private UmMappings() {
        throw new AssertionError();
    }

    // API
}
