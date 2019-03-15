package com.itmuch.yes.consumer.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;

public class KeycloakRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        Principal principal = attributes.getRequest().getUserPrincipal();

        if (principal instanceof KeycloakPrincipal) {
            KeycloakSecurityContext keycloakSecurityContext = ((KeycloakPrincipal) principal)
                    .getKeycloakSecurityContext();

            if (keycloakSecurityContext instanceof RefreshableKeycloakSecurityContext) {
                ((RefreshableKeycloakSecurityContext) keycloakSecurityContext)
                        .refreshExpiredToken(true);
                template.header(AUTHORIZATION_HEADER, "Bearer " + keycloakSecurityContext.getTokenString());
            }

        }
        // 否则啥都不干
    }
}