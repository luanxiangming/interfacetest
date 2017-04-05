package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.backend.classController.GetClientClassesMsg;
import com.vipabc.interfacetest.utils.*;
import shelper.iffixture.HttpFixture;

import java.util.Date;


/**
 * Created by oliverluan on 08/03/2017.
 */
public class SetReservationMsg extends BaseMsg {
    public static void setReservation(HttpFixture hf, int actionType, int SstNumber, String SessionTime, int LobbySn, int SessionPeriod, String BookingClassType, int MaxMember, String cookies) {
        String request = prop.getEnvPropties("member.server", "url") + "/aspx/IMO/Booking/SetReservation";
        String data = "[{\"SstNumber\":" + SstNumber + ", " +
                "\"SessionTime\":" + "\"" + SessionTime + "\"" + ", " +
                "\"LobbySn\":" + LobbySn + "," +
                "\"SessionPeriod\":" + SessionPeriod + ", " +
                "\"BookingClassType\":" + "\"" + BookingClassType + "\"" + ", " +
                "\"MaxMember\":" + "" + MaxMember + "}]";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.addHeaderValue("Referer", prop.getEnvPropties("referer.server", "url"));
        hf.addCookieValue("Cookie", cookies);

        String reqBody = "{\"actionType\":" + actionType + ", \"data\": \"" + data + "\"}";
        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }

    /**
     * @param actionType       订课：actionType=0， 取消订课：actionType=2
     * @param SstNumber        课程类型
     * @param SessionTime      订课时间
     * @param LobbySn          讲堂编号
     * @param SessionPeriod    课程时长
     * @param BookingClassType 订课类型
     * @param MaxMember        课程最大人数，1对1：1，小班课：6，vip讲堂，随选：0
     */
    public static void reservation(int actionType, int SstNumber, String SessionTime, int LobbySn, int SessionPeriod, String BookingClassType, int MaxMember, int IsCycleBooking) {
        String data;
//        String request = prop.getEnvPropties("member.server", "url") + "/aspx/IMO/Booking/SetReservation";
        String request = prop.getEnvPropties("url.server.v", "url") + "/Center3.0/Booking/SetReservation";
        if (IsCycleBooking == 0) {
            data = "[{\\\"SstNumber\\\":" + SstNumber + ", " +
                    "\\\"SessionTime\\\":" + "\\\"" + SessionTime + "\\\"" + ", " +
                    "\\\"LobbySn\\\":" + LobbySn + "," +
                    "\\\"SessionPeriod\\\":" + SessionPeriod + ", " +
                    "\\\"BookingClassType\\\":" + "\\\"" + BookingClassType + "\\\"" + ", " +
                    "\\\"MaxMember\\\":" + "" + MaxMember + "}]";
        } else {
            data = "[{\\\"SstNumber\\\":" + SstNumber + ", " +
                    "\\\"SessionTime\\\":" + "\\\"" + SessionTime + "\\\"" + ", " +
                    "\\\"LobbySn\\\":" + LobbySn + "," +
                    "\\\"SessionPeriod\\\":" + SessionPeriod + ", " +
                    "\\\"BookingClassType\\\":" + "\\\"" + BookingClassType + "\\\"" + ", " +
                    "\\\"MaxMember\\\":" + "" + MaxMember + ",\\\"IsCycleBooking\\\":" + IsCycleBooking + "}," +
                    "{\\\"SstNumber\\\":" + SstNumber + ", " +
                    "\\\"SessionTime\\\":" + "\\\"" + DateUtils.addDays(SessionTime, 7) + "\\\"" + ", " +
                    "\\\"LobbySn\\\":" + LobbySn + "," +
                    "\\\"SessionPeriod\\\":" + SessionPeriod + ", " +
                    "\\\"BookingClassType\\\":" + "\\\"" + BookingClassType + "\\\"" + ", " +
                    "\\\"MaxMember\\\":" + "" + MaxMember + ",\\\"IsCycleBooking\\\":" + IsCycleBooking + "}]";
        }
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
//        htf.addHeaderValue("Referer", "http://stagemember.vipjr.com/aspx/IMO");
        htf.addHeaderValue("Referer", "http://stage.vipabc.com/Center3.0");
        String reqBody = "{\"actionType\":" + actionType + ", \"data\": \"" + data + "\"}";
        htf.setRequestBody(reqBody);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void reservationLobby(int actionType, int brandId, int clientSn, int SessionPeriod, String BookingClassType, int IsCycleBooking) {
        String BeginTime = DateUtils.defaultFormatDate(DateUtils.addMinutes(new Date(), 10));
        String EndTime = DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), 2));
        GetClientClassesMsg.getClientClasses(brandId, clientSn, BookingClassType, 16, BeginTime, EndTime);
        int LobbySn = (Integer) (htf.getValue(".LobbySn"));
        String SessionTime = ((String) htf.getValue(".BeginDateTime")).replace("T", " ");
        reservation(actionType, 0, SessionTime, LobbySn, SessionPeriod, BookingClassType, 0, IsCycleBooking);
    }

    public static void reservationRegular(int actionType, String SessionTime, int SessionPeriod, int MaxMember, int IsCycleBooking) {
        reservation(actionType, 0, SessionTime, 0, SessionPeriod, "SmallClass", MaxMember, IsCycleBooking);
    }
}
