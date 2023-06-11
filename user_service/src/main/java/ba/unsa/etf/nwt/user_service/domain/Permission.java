package ba.unsa.etf.nwt.user_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"), //moze koliko zelimo permisija, npr. moze read2 ako je specijalno nesto
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    REG_USER_READ("REG_USER:read"),
    REG_USER_UPDATE("REG_USER:update"),
    REG_USER_CREATE("REG_USER:create"),
    REG_USER_DELETE("REG_USER:delete"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete")
    ;

    @Getter
    private final String permission;
}
