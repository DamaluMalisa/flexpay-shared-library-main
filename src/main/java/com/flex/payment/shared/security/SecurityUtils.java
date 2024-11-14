package com.flex.payment.shared.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static Long ROLE_ROOT_ID = 1L;
    public static String ROLE_ROOT = "ROLE_ROOT";

    public static Long ROLE_ADMIN_ID = 2L;
    public static String ROLE_ADMIN = "ROLE_ADMIN";

    public static Long ROLE_MERCHANT_ID = 3L;
    public static String ROLE_MERCHANT = "ROLE_MERCHANT";

    public static Long ROLE_PAYER_ID = 4L;
    public static String ROLE_PAYER = "ROLE_PAYER";

    public static Long ROOT_USER_ID = 1337L;

    public static String USER_ID_CLAIM = "userId";
    public static String ADMIN_ID_CLAIM = "adminId";
    public static String MERCHANT_ID_CLAIM = "merchantId";
    public static String PAYER_ID_CLAIM = "payerId";

    public static String BEARER_PREFIX = "Bearer ";

    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : "";
    }

    public static Claims getClaims() {
        return (Claims) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public static Long getUserId() {
        return getClaims().get(USER_ID_CLAIM, Long.class);
    }

    public static Long getAdminId() {
        return getClaims().get(ADMIN_ID_CLAIM, Long.class);
    }

    public static Long getMerchantId() {
        return getClaims().get(MERCHANT_ID_CLAIM, Long.class);
    }

    public static Long getPayerId() {
        return getClaims().get(PAYER_ID_CLAIM, Long.class);
    }

    public static boolean hasAuthority(String authority) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(authority));
    }
}
