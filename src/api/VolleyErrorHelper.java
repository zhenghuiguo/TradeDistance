package api;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.td.tradedistance.app.global.Global;
import com.td.tradedistance.app.utils.Logger;
/*7.错误处理

正如前面代码看到的，在创建一个请求时，需要添加一个错误监听onErrorResponse。如果请求发生异常，会返回一个VolleyError实例。

以下是Volley的异常列表：

AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。

NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。

NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。

ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。

SERVERERROR：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。

TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy。

可以使用一个简单的Help类根据这些异常提示相应的信息：*/
public class VolleyErrorHelper {
	   /**
	   * Returns appropriate message which is to be displayed to the user 
	   * against the specified error object.
	   * 
	   * @param error
	   * @param context
	   * @return
	   */
	  public static String getMessage(Object error) {
	    if (error instanceof TimeoutError) {
	      return "Socket超时，服务器太忙或网络延迟会产生这个异常";//context.getResources().getString(R.string.generic_server_down);
	    }
	    else if (isServerProblem(error)) {
	      return handleServerError(error);
	    }
	    else if (isNetworkProblem(error)) {
	    	//String s = new String(VolleyController.getInstance(context).reqQueue.getCache().get("dl").data);
	        // Logger.d("getMessage", s);
	    	return Global.networdtip;
	    }
	    return "普通错误";//context.getResources().getString(R.string.generic_error);
	  }
	  /**
	  * Determines whether the error is related to network
	  * @param error
	  * @return
	  */
	  private static boolean isNetworkProblem(Object error) {
	    return (error instanceof NetworkError) || (error instanceof NoConnectionError);
	  }
	  /**
	  * Determines whether the error is related to server
	  * @param error
	  * @return
	  */
	  private static boolean isServerProblem(Object error) {
	    return (error instanceof ServerError) || (error instanceof AuthFailureError);
	  }
	  /**
	  * Handles the server error, tries to determine whether to show a stock message or to 
	  * show a message retrieved from the server.
	  * 
	  * @param err
	  * @param context
	  * @return
	  */
	  private static String handleServerError(Object err) {
	    VolleyError error = (VolleyError) err;
	    NetworkResponse response = error.networkResponse;
	    if (response != null) {
	      switch (response.statusCode) {
	      case 404:return "服务器404出错";
	      case 422:
	      case 401://未经授权
	        try {
	          // server might return error like this { "error": "Some error occured" }
	          // Use "Gson" to parse the result
	          HashMap<String, String> result = new Gson().fromJson(new String(response.data),
	              new TypeToken<Map<String, String>>() {
	              }.getType());
	          if (result != null && result.containsKey("error")) {
	        	  Logger.d("handleServerError", result.get("error"));
	            return result.get("error");
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	        // invalid request
	        return error.getMessage();
	      case 500:
	    	  return "服务器500出错";
	      default:
	        return "服务器异常";//context.getResources().getString(R.string.generic_server_down);
	      }
	    }
	    return "服务器异常";//context.getResources().getString(R.string.generic_error);
	  }
	}
