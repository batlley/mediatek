package controllers;

import org.junit.Test;

import ninja.NinjaDocTester;
import org.doctester.testbrowser.Request;
import org.doctester.testbrowser.Response;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ControllerDocTesterTest extends NinjaDocTester {
    
    String URLINDEX = "/";
    
    @Test
    public void testGetIndex() {
    
        Response response = makeRequest(
                Request.GET().url(
                        testServerUrl().path(URLINDEX)));

        assertThat(response.payload, containsString("Mediatek!"));
    }


}
