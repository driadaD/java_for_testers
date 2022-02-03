package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

    @Test
    public void testMyIp () {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
        assertTrue(geoIp.contains("RU"));
    }
}
