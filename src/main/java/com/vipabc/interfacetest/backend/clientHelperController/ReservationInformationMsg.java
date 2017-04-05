package com.vipabc.interfacetest.backend.clientHelperController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 21/03/2017.
 */
public class ReservationInformationMsg {
    public static void reservationInformation(HttpFixture hf, String cookies) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("member.server", "url") + "/aspx/IMO/ClientHelper2/ReservationInformation";

        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
