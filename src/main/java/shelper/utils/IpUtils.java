package shelper.utils;
import java.net.InetAddress;

public class IpUtils{
/*
 * 获取本机ip
 */
 public static String getLocalHostIP(){
  String ip;
  try{
   InetAddress addr = InetAddress.getLocalHost();
   ip = addr.getHostAddress();
  }
  catch(Exception ex){
   ip = "";
  }
  return ip;
 }
/*
 * 获取本机主机名称
 */
 public static String getLocalHostName(){
  String hostName;
  try{
   InetAddress addr = InetAddress.getLocalHost();
   hostName = addr.getHostName();
  }
  catch(Exception ex){
   hostName = "";
  }
  return hostName;
 }
/*
 * 获取本机所有网卡的ip地址
 */
 public static String[] getAllLocalHostIP(){
  String[] ret = null;
  try{
   String hostName = getLocalHostName();
   if(hostName.length()>0){
    InetAddress[] addrs = InetAddress.getAllByName(hostName);
    if(addrs.length>0){
     ret = new String[addrs.length];
     for(int i=0 ; i< addrs.length ; i++){
      ret[i] = addrs[i].getHostAddress();
     }
    }
   }

  }
  catch(Exception ex){
   ret = null;
  }
  return ret;
 }

 public static String[] getAllHostIPByName(String hostName){
  String[] ret = null;
  try{
   if(hostName.length()>0){
    InetAddress[] addrs = InetAddress.getAllByName(hostName);
    if(addrs.length>0){
     ret = new String[addrs.length];
     for(int i=0 ; i< addrs.length ; i++){
      ret[i] = addrs[i].getHostAddress();
     }
    }
   }

  }
  catch(Exception ex){
   ret = null;
  }
  return ret;
 }


 public static void main(String[] args) {
  //System.out.println(getLocalHostIP());
  System.out.println("主机名：" + getLocalHostName());

  String[] localIP = getAllLocalHostIP();
  for(int i=0 ; i<localIP.length ; i++){
   System.out.println( localIP[i]);
  }
 }
}
