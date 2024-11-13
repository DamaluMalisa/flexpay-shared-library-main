package com.flex.payment.shared.security;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomAuditorAware implements AuditorAware<String> {

    private final TokenUtils tokenUtils;

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        String token = extractJwtFromRequest();

        if (StringUtils.hasText(token)) {
            String username = tokenUtils.getUsername(token);

            if (username != null) {
                return Optional.of(username);
            }
        }

        return Optional.empty();
    }

    private String extractJwtFromRequest() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        }
        return null;
    }
}




//package com.flex.payment.shared.security;
//
//import lombok.NonNull;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.StringUtils;
//import com.flex.payment.shared.client.AuthFeignClient;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class CustomAuditorAware implements AuditorAware<UserDetails> {  // Change to UserDetails to return complete user object
//
//    private final AuthFeignClient authFeignClient;
//    private final TokenUtils tokenUtils;
//
//    @Override
//    @NonNull
//    public Optional<UserDetails> getCurrentAuditor() {
//        String token = extractJwtFromRequest();
//
//        if (StringUtils.hasText(token)) {
//            String username = tokenUtils.getUsername(token);
//
//            if (username != null) {
//                UserDetails userDetails = getUserDetails(username);
//                return Optional.ofNullable(userDetails);
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    private String extractJwtFromRequest() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
//        }
//        return null;
//    }
//
//    protected UserDetails getUserDetails(String username) {
//        return authFeignClient.getUser(username);
//    }
//}
