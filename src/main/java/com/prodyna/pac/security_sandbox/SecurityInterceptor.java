package com.prodyna.pac.security_sandbox;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityInterceptor implements ContainerRequestFilter {

    @Context
    private SecurityContext secContext;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.getHeaders().put("security", Arrays.asList("was here"));
    }

}
