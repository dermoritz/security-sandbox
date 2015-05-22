package com.prodyna.pac.security_sandbox;

import static com.jayway.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FirstTest {

    @Deployment(testable = false)
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.prodyna.pac.security_sandbox");
    }
    
    @ArquillianResource
    private URL base;
    
    @Test
    public void test() throws MalformedURLException {
        URL url = new URL(base, "sandbox/service/get");
        String response = given().then().contentType(MediaType.TEXT_PLAIN).when().get(url).body().asString();
       // System.out.println(response);
        String string = response + "";
    }
    
    
    
}
