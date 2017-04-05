//package com.vipabc.interfacetest;
//
//import java.net.URLEncoder;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.TreeMap;
//
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import com.vipabc.interfacetest.entity.booking.Reservation;
//import com.vipabc.interfacetest.service.booking.ReservationService;
//import com.vipabc.interfacetest.utils.DateFormatUtil;
//import com.vipabc.interfacetest.utils.HttpClientUtil;
//import com.vipabc.interfacetest.utils.JsoupUtil;
//
//public class HttpClientDemo {
//	
//	public static void main(String[] args) throws Exception {
//		String cookie=HttpClientUtil.getCookie("http://stage.vipjr.com/Login/NewLogin", "test_ny@test.com", "123456");
//		
//		Map<String, String> headerMap = new HashMap<>();
//		
//		headerMap.put("Cookie",cookie);		
//		headerMap.put("Content-Type", "application/json; charset=UTF-8");
//		headerMap.put("Referer", "http://stagemember.vipjr.com/aspx/IMO");
//		
//	
//		String LobbyClassDetail="http://stagemember.vipjr.com/aspx/IMO/Booking/LobbyClassDetail";
//		
//		LinkedHashMap<String, String> linkMap = new LinkedHashMap<>();	
//		String currentTimeFormat="MM/dd/yyyy HH:mm:ss";
//		linkMap.put("type", "LobbyClass");	
//		linkMap.put("queryDate", HttpClientUtil.getEncodingString(DateFormatUtil.getCurrentTime(currentTimeFormat)));			
//		linkMap.put("isSettingCondition", "False");
////		sortmap.put("uid", "911");		
//		
//		HttpClientUtil.getMethodWtihPathParam(LobbyClassDetail, headerMap, linkMap);
//		System.out.println("============================================================================================================");
//		String htmlReturn=HttpClientUtil.getResponse();	
////		System.out.println(htmlReturn);
//		
//		
//		
//		Elements eles=JsoupUtil.getElementsByCssSelector(htmlReturn, "a[data-lobbysn][data-bookingtype=LobbyClass]");
//		List<Reservation>  list=ReservationService.getClassReservationAvailable(eles);
//		for(Reservation reservation:list){
//			System.out.println(reservation.getBookingClassType()+"\t"+reservation.getTopic()+"\t"+reservation.getLobbySn()+"\t"+reservation.getSstNumber()+"\t"+reservation.getSessionTime());
//		}
//		
//		
//		System.out.println("============================================================================================================");
//		Random  random=new Random();
//		int randomNum=random.nextInt(list.size()-1);
//		System.out.println("随机数是"+randomNum);		
//		Reservation reservation=list.get(randomNum);
//		System.out.println(reservation.getBookingClassType()+"\t"+reservation.getTopic()+"\t"+reservation.getLobbySn()+"\t"+reservation.getSstNumber()+"\t"+reservation.getSessionTime());
//		
//		
//		String setServationUrl = "http://stagemember.vipjr.com/aspx/IMO/Booking/SetReservation";
//		
//		
//		Map<String,String> bodyMap=new HashMap<>();
//		String jsonStr="{\"actionType\":0,\"data\":\"[{\\\"SstNumber\\\":"+reservation.getSstNumber()+",\\\"SessionTime\\\":\\\""+reservation.getSessionTime()+"\\\",\\\"LobbySn\\\":"+reservation.getLobbySn()+",\\\"SessionPeriod\\\":"+reservation.getSessionPeriod()+",\\\"BookingClassType\\\":\\\""+reservation.getBookingClassType()+"\\\",\\\"MaxMember\\\":0}]\"}";
//		System.out.println("JsonStr is:"+jsonStr);
//		
//		
//		HttpClientUtil.postMethodWithRawBody(setServationUrl, headerMap, jsonStr);	
//			
//	}
//}
