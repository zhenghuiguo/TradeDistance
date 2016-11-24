package api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.td.tradedistance.app.utils.Logger;

public class GsonRequest<T> extends Request<T> {
	private final static int initialTimeoutMs = 20 * 1000;
	private final static int maxNumRetries = 1;
	private final static float backoffMultiplier = 1.0f;
	private final static String RequestEncoding = "UTF-8";
	private final Listener<T> mListener;
	private Gson mGson;

	private Class<T> mClass;
	// 存放请求参数
	private Map<String, String> mParams;
    Context mContext;
	public GsonRequest(int method, String url, Class<T> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mGson = new Gson();
		mClass = clazz;
		mListener = listener;
		Logger.d(url);
		//mParams = new HashMap<String, String>();
	}

	public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		this(Method.GET, url, clazz, listener, errorListener);
	}
	public GsonRequest(Context mContext,String url, Class<T> clazz, Listener<T> listener,String tag,
			ErrorListener errorListener) {
		
		this(Method.GET, url, clazz, listener, errorListener);
	}
	// (java.util.HashMap) {Content-Length=342, Expires=-1,
	// Set-Cookie=ASP.NET_SessionId=xcy3bfe2ymsk41eozrwztagw; path=/; HttpOnly,
	// Connection=Keep-Alive, X-Powered-By=ASP.NET, Server=Microsoft-IIS/7.5,
	// Pragma=no-cache, Cache-Control=no-cache, X-AspNet-Version=4.0.30319,
	// Date=Tue, 29 Mar 2016 05:22:08 GMT,
	// X-Android-Received-Millis=1459228925443, Via=1.1 QMDEV, 1.1 HCMTMG2010,
	// Content-Type=text/plain; charset=utf-8,
	// X-Android-Sent-Millis=1459228925175, Proxy-Connection=Keep-Alive}
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			//Logger.d("URL", getUrlParams(mParams));
			Logger.d(jsonString);
			return Response.success(mGson.fromJson(jsonString, mClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			Logger.d("UnsupportedEncodingException", e.getMessage());
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

	/**
	 * 重试失败的请求，自定义请求超时
	 */
	@Override
	public void setRetryPolicy(RetryPolicy retryPolicy) {
		super.setRetryPolicy(new DefaultRetryPolicy(initialTimeoutMs,
				maxNumRetries, backoffMultiplier));
	}

	/**
	 * 请求参数
	 */
	/*
	 * @Override protected Map<String, String> getParams() throws
	 * AuthFailureError { //Logger.d("URL", getUrlParams(mParams)); return mParams;
	 * }
	 */
	public GsonRequest<T> addParam(String key, String value) {
		mParams.put(key, value);
		return this;
	}

	/**
	 * @param key
	 * @param value
	 *            Integer
	 * @return
	 */

	public GsonRequest<T> addParam(String key, Integer value) {
		mParams.put(key, String.valueOf(value));
		return this;
	}

	/**
	 * @param key
	 * @param value
	 *            Long
	 * @return
	 */

	public GsonRequest<T> addParam(String key, Long value) {
		mParams.put(key, String.valueOf(value));
		return this;
	}

	/**
	 * @param key
	 * @param map
	 *            Map<String, String>
	 * @return
	 */

	public GsonRequest<T> addParam(String key, Map<String, String> map) {

		if (map != null && map.size() > 0) {
			String mapJson = mGson.toJson(map);
			mParams.put(key, mapJson);
		}
		return this;
	}

	/**
	 * @param params
	 */

	public GsonRequest<T> addAllParams(Map<String, String> params) {
		mParams.putAll(params);
		return this;
	}

	public void setParams(Map<String, String> params) {
		mParams = params;
	}

	/*
	 * @Override public Map<String, String> getHeaders() throws AuthFailureError
	 * { HashMap<String, String> headers = new HashMap<String, String>();
	 * headers.put("CUSTOM_HEADER", "Yahoo");
	 * headers.put("ANOTHER_CUSTOM_HEADER", "Google"); return headers; }
	 */
	@Override
	public String getBodyContentType() {
		Logger.d("getBody", "getBodyContentType");
		return "application/json; charset=utf8";
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		Logger.d("getBody", "getBody");
		/*if (mParams.size() > 0) {
			String mapJson = mGson.toJson(mParams);
			Logger.d("getBody----", mapJson);
			try {
				return mapJson.getBytes("utf8");
			} catch (UnsupportedEncodingException e) {
				VolleyLog
						.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
								"", "utf8");
			}
		}*/
		return null;
	}
}