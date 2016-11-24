package com.td.tradedistance.app.utils;


public class CommonUtil {
	//同一帐号、同一天内密码连续输错5次，锁定该帐号4小时
	
	/**
     *判断当前时间与给定时间差是否大于4个小时 
     * @param date
     * @return 大于4个小时 返回true
     * @throws Exception
     */
    public static boolean localdateLtDate(String date) throws Exception{
    	 if(System.currentTimeMillis()-Long.parseLong(date)>4*60*60*1000){
             return true;
         }
         else{
             return false;
         }
    }


    /**
     *判断当前时间与给定时间差是否大于一天 
     * @param date
     * @return 大于一天返回true
     * @throws Exception
     */
    public static boolean localdateLtDate1(String date) throws Exception{
        if(System.currentTimeMillis()-Long.parseLong(date)>24*60*60*1000){
            return true;
        }
        else{
            return false;
        }
    }
/*    public static boolean localdateLtDate2(String date) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date1=sdf.parse(date);
        Date now=sdf.parse(sdf.format(new Date()));
        if(now.getTime()-date1.getTime()>24*60*60*1000){
            return true;
        }
        else{
            return false;
        }
    }*/
    public static String getKeChengText(String keCheng,String zhang, String jie) {
		StringBuffer b = new StringBuffer();
		b.append(keCheng).append(" ");
		b.append(zhang).append(" ");
		b.append(jie);
		return b.toString();
	}
}
