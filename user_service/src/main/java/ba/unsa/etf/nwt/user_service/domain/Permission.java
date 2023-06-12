package ba.unsa.etf.nwt.user_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"), //moze koliko zelimo permisija, npr. moze read2 ako je specijalno nesto
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    CUSTOMER_READ("CUSTOMER:read"),
    CUSTOMER_UPDATE("CUSTOMER:update"),
    CUSTOMER_CREATE("CUSTOMER:create"),
    CUSTOMER_DELETE("CUSTOMER:delete"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete")
    ;

    @Getter
    private final String permission;
}
