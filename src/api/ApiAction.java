package api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.android.volley.VolleyError;

import android.content.Context;
import android.widget.Toast;
import com.td.tradedistance.app.ApiGlobal;
public class ApiAction {
	public  static final String DengLu ="DengLu";
	public  static final String BangDingZhongDuan ="ZhuCeZhongDuan";
	public static final String ZhuXiao = "ZhuXiao";
	public static final String GeRenXinXi = "GeRenXinXi";
	public static final String JiaoXueJiHua = "JiaoXueJiHua";
	public static final String KeChengXinXi = "KeChengXinXi";
	public static final String ZaiXueKeCheng = "ZaiXueKeCheng";
	public static final String ZaiXueKeCheng_YWJ = "ZaiXueKeCheng_YWJ";
	public static final String FuXiDaGang = "FuXiDaGang";
	public static final String PingShiZuoYeChengJi = "PingShiZuoYeChengJi";
	public static final String LanMuCaiDan = "LanMuCaiDan";
	public static final String kaoshi = "kaoshi";
	public static final String zongxuefen = "zongxuefen.html";
	public static final String GuangGaoWei = "GuangGaoWei";
	public static final String kaochangchaxun = "kaochangchaxun.html";
	public static final String kaoshiyuyue = "kaoshiyuyue.html";
	public static final String tongzhigonggao = "tongzhigonggao.html";
	public static final String xiazai = "xiazai";
	public static final String GeRenSheZhi = "GeRenSheZhi";
	/**
	 * @param mParams
	 * @return
	 */
	 public static String getUrlParams(String action,Map<String, String> mParams){
	    	StringBuffer paramStr= new StringBuffer();
	    	paramStr.append(getRequestUrl(action)).append("?");
	    	if(mParams!=null ) {
		    	for (String key : mParams.keySet()) {  
		    		   try {
						paramStr.append(URLEncoder.encode(key,ApiGlobal.RequestEncoding)+"="+URLEncoder.encode(mParams.get(key),ApiGlobal.RequestEncoding)+"&");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        }  	
		    	return paramStr.toString().substring(0, paramStr.toString().length()-1);
	    	}
	    	
	    	return paramStr.toString();
	    	//tpwxs%2FDVD7GMh7OhYa4mW%2F9PFpn9IlYMLUxPyL2mpFiBbxmxqVIw6w%3D%3D
	    	//tpwxs/  DVD7GMh7OhYa4mW/  9PFpn9IlYMLUxPyL2mpFiBbxmxqVIw6w==
	    }
	 /**
     * 请求url生成
     * @param path
     * @return
     */
    public final static String getRequestUrl(final String action) {
    	/*if("DengLu".equals(action)){
   		    return  ApiGlobal.baseUrl+"ChinaHCM/".concat(action).concat(".ashx");
   	 	}*/
        return  ApiGlobal.baseUrl.concat(action).concat(".ashx");
    }
    public final static void errorTip(Context context,VolleyError error) {
    	Toast.makeText(context,VolleyErrorHelper.getMessage(error),Toast.LENGTH_LONG).show();
    }
    
	/** (com.android.volley.NoConnectionError) com.android.volley.NoConnectionError: java.net.SocketException: socket failed: EACCES (Permission denied)
	 * <p>
	 * 功能描述:wcslb 额外参数列表 格式：key_value|key_value|......
	 * </p>
	 * 
	 * @param jgid
	 * @param zlmid
	 * @param hqts
	 * @param dqym
	 * @param myts
	 * @param xxid
	 * @param hqnr
	 * @param hqnrb
	 * @param hqgy
	 * @param hqtj
	 * @return
	 * @author:郑惠国
	 * @update:[日期2015-10-12] [更改人姓名][变更描述]
	 *//*
	public static String getEwcslb(Ewcslb ewcslb) {
		StringBuilder sb = new StringBuilder();
		if (null != ewcslb.getJdid()) {
			sb.append("jgid_").append(ewcslb.getJdid()).append("|");
		}
		if (null != ewcslb.getZlmid()) {
			sb.append("zlmid_").append(ewcslb.getZlmid()).append("|");
		}
		if (null != ewcslb.getHqts()) {
			sb.append("hqts_").append(ewcslb.getHqts()).append("|");
		}
		if (null != ewcslb.getDqym()) {
			sb.append("dqym_").append(ewcslb.getDqym()).append("|");
		}
		if (null != ewcslb.getMyts()) {
			sb.append("myts_").append(ewcslb.getMyts()).append("|");
		}
		if (null != ewcslb.getXxid()) {
			sb.append("xxid_").append(ewcslb.getXxid()).append("|");
		}
		if (null != ewcslb.getYhwybs()) {
			sb.append("yhwybs_").append(ewcslb.getYhwybs()).append("|");
		}
		if (null != ewcslb.getHqnr()) {
			sb.append("hqnr_").append(ewcslb.getHqnr()).append("|");
		}
		if (null != ewcslb.getHqnrb()) {
			sb.append("hqnrb_").append(ewcslb.getHqnrb()).append("|");
		}
		if (null != ewcslb.getHqgy()) {
			sb.append("hqgy_").append(ewcslb.getHqgy()).append("|");
		}
		if (null != ewcslb.getBhqxxlxfs()) {
			sb.append("bhqxxlxfs_").append(ewcslb.getBhqxxlxfs()).append("|");
		}
		if (0 != ewcslb.getDlzt()) {
			sb.append("dlzt_").append(ewcslb.getDlzt()).append("|");
		}
		if (null != ewcslb.getHqywydtjs()) {
			sb.append("hqywydtjs_").append(ewcslb.getHqywydtjs()).append("|");
		}

		if (null != ewcslb.getHqzj()) {
			sb.append("hqzj_").append(ewcslb.getHqzj()).append("|");
		}
		if (0 != ewcslb.getHqtj()) {
			sb.append("hqtj_").append(ewcslb.getHqtj()).append("|");
		}
		if (0 != ewcslb.getWdhd()) {
			sb.append("wdhd_").append(ewcslb.getWdhd()).append("|");
		}
		if (0 != ewcslb.getWdsc()) {
			sb.append("wdsc_").append(ewcslb.getWdsc()).append("|");
		}
		if (0 != ewcslb.getWdkc()) {
			sb.append("wdkc_").append(ewcslb.getWdkc()).append("|");
		}
		if (null != ewcslb.getKcdm()) {
			sb.append("kcdm_").append(ewcslb.getKcdm()).append("|");
		}
		if (0 != ewcslb.getHqwd()) {
			sb.append("hqwd_").append(ewcslb.getHqwd()).append("|");
		}
		if (null != ewcslb.getDdh()) {
			sb.append("ddh_").append(ewcslb.getDdh()).append("|");
		}
		if (null != ewcslb.getHqjs()) {
			sb.append("hqjs_").append(ewcslb.getHqjs()).append("|");
		}
		if ("".equals(sb.toString())) {
			return "";
		}
		Logger.d("getEwcslb格式：",
				sb.toString().substring(0, sb.toString().length() - 1));
		return sb.toString().substring(0, sb.toString().length() - 1);
	}*/
}
