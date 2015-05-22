package com.prodyna.pac.security_sandbox;

import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/service")
@RequestScoped
public class SecuredService {
    
    @Context
    private SecurityContext secContext;
    
    @Context
    private UriInfo uriInfo;
    
    @Context
    private ServletRequest request;
    
    
    @Inject
    private HttpSession session;
    
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSome(@HeaderParam("security") String userAgent){
        String name = secContext.getUserPrincipal().getName();
        boolean userInRole = secContext.isUserInRole("Manager");
        String role= userInRole ? "Manager " : "other";
        
        return Response.ok("You are user " + name + "role: " + role + " security: " + userAgent).build();
    }
    
    @GET
    @Path("/logout")
    public Response logOut() throws URISyntaxException{
        URI source = uriInfo.getAbsolutePath();
        URI uri = new URI(source.getScheme(), "invalid:invalid", source.getHost(), source.getPort(), "/security-sandbox/sandbox/service/get", null, null);
        return Response.temporaryRedirect(uri).build();
    }
}
