package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

public class SetReservationCustomizeMsg extends BaseMsg {

	public static void setReservationCustomize(String actionType, String SstNumber, String SessionTime, String LobbySn,String SessionPeriod, String BookingClassType, String MaxMember) {
		Pro pro = new Pro();
		String request = pro.getEnvPropties("member.server", "url") + "/aspx/IMO/Booking/SetReservation";
		String data = "[{\\\"SstNumber\\\":" + SstNumber + ", " + "\\\"SessionTime\\\":" + "\\\"" + SessionTime + "\\\""
				+ ", " + "\\\"LobbySn\\\":" + LobbySn + "," + "\\\"SessionPeriod\\\":" + SessionPeriod + ", "
				+ "\\\"BookingClassType\\\":" + "\\\"" + BookingClassType + "\\\"" + ", " + "\\\"MaxMember\\\":" + ""
				+ MaxMember + "}]";
		htf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
		htf.addHeaderValue("Referer", pro.getEnvPropties("referer.server", "url"));
//		htf.addCookieValue("Cookie", cookies);

		String reqBody = "{\"actionType\":" + actionType + ", \"data\": \"" + data + "\"}";
		htf.setRequestBody(reqBody);
		htf.setUrl(request);		
		htf.Post();
	}
}
