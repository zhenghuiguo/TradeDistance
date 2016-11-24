package api;

import java.io.File;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.td.tradedistance.app.utils.Logger;

/**
 * Volley请求队列处理类，用来管理Rquest请求对象操作
 * @author zhenghg
 *
 */
public class VolleyController {
	 private static final int DISKMAXSIZE = 10 * 1024 * 1024;
    // 创建一个TAG，方便调试或Log
    private static final String TAG = "VolleyController";
    private static final String DEFAULT_CACHE_DIR = "volley";

    // 创建一个全局的请求队列
    public RequestQueue reqQueue;
    private ImageLoader imageLoader;

    // 创建一个static VolleyController对象，便于全局访问
    private static VolleyController mInstance;
    
    private Context mContext;

    private VolleyController(Context context) {
        mContext=context;
    }

    /**
     * 以下为需要我们自己封装的添加请求取消请求等方法
     */

    // 用于返回一个VolleyController单例
    public static VolleyController getInstance(Context context) {
        if (mInstance == null) {
            synchronized(VolleyController.class)
            {
                if (mInstance == null) {
                    mInstance = new VolleyController(context);
                }
            }
        }
        return mInstance;
    }

    // 用于返回全局RequestQueue对象，如果为空则创建它
    public RequestQueue getRequestQueue() {
        if (reqQueue == null){
            synchronized(VolleyController.class)
            {
                if (reqQueue == null){
                    //reqQueue = Volley.newRequestQueue(mContext);
                	 reqQueue = newRequestQueue(mContext,null);
                }
            }
        }
        return reqQueue;
    }
   /* public RequestQueue getRequestQueueImage(DiskBasedCache diskBasedCache,Network network) {
        if (reqQueue == null){
            synchronized(VolleyController.class)
            {
                if (reqQueue == null){
                	
                	 RequestQueue queue = new RequestQueue(diskBasedCache, network);
                     queue.start();
                }
            }
        }
        return reqQueue;
    }*/
    
    public ImageLoader getImageLoader(Context context){
        getRequestQueue();
        //如果imageLoader为空则创建它，第二个参数代表处理图像缓存的类
        if(imageLoader==null){
            //LruCache
            //imageLoader=new ImageLoader(reqQueue, new LruBitmapCache());
            //LruCache  DiskLruCachenew ImageCacheUtil(mContext)
            imageLoader=new ImageLoader(reqQueue, new ImageCacheUtil(mContext));
        }
        return imageLoader;
    }
    public static RequestQueue newRequestQueue(Context context, HttpStack stack) {
    	
    	 File rootCache = context.getExternalCacheDir();
         if (rootCache == null) {
             System.err.println("Can't find External Cache Dir, "
                     + "switching to application specific cache directory");
             rootCache = context.getCacheDir();//得到缓存文件夹
             Logger.d("context.getCacheDir()", context.getCacheDir()+"");
         }

         File cacheDir = new File(rootCache, DEFAULT_CACHE_DIR);
         if(!cacheDir.exists())
         	cacheDir.mkdirs();
       

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (NameNotFoundException e) {
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        //RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();

        return queue;
    }
  /*  public  RequestQueue newRequestQueueWithDiskCache(Context context) {
        // define cache folder
    	通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    	      通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    	      如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
    	      而且上面二个目录分别对应 设置->应用->应用详情里面的”清除数据“与”清除缓存“选项
    	  Environment .getExternalStorageDirectory().getAbsolutePath();
		      获取外部存储的路径返回绝对路径的,其实就是你的SD卡的文件路径
        File rootCache = context.getExternalCacheDir();
        if (rootCache == null) {
            System.err.println("Can't find External Cache Dir, "
                    + "switching to application specific cache directory");
            rootCache = context.getCacheDir();//得到缓存文件夹
            Logger.d("context.getCacheDir()", context.getCacheDir()+"");
        }

        File cacheDir = new File(rootCache, "uuu");
        if(!cacheDir.exists())
        	cacheDir.mkdirs();
       //SDK如果大于等于9，也就是Android 2.3以后，因为引进了HttpUrlConnection，所以会用一个HurlStack  
        HttpStack stack = new HurlStack();
        //创建一个Network，构造函数需要一个stack参数，Network里面会调用stack去跟网络通信  
        Network network = new BasicNetwork(stack);
        DiskBasedCache diskBasedCache = new DiskBasedCache(cacheDir, DISKMAXSIZE);
        //创建RequestQueue，并将缓存实现DiskBasedCache和网络实现BasicNetwork传进去，然后调用start方法  
        

        return getRequestQueueImage(diskBasedCache,network);
    }*/
    /**
     * 将Request对象添加进RequestQueue，由于Request有*StringRequest,JsonObjectResquest...
     * 等多种类型，所以需要用到*泛型。同时可将*tag作为可选参数以便标示出每一个不同请求
     */

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // 如果tag为空的话，就是用默认TAG
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueueRepeat(Request<T> req, String tag) {
    	cancelPendingRequests(tag);
        // 如果tag为空的话，就是用默认TAG
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        getRequestQueue().add(req);
    }
    // 通过各Request对象的Tag属性取消请求
    public void cancelPendingRequests(Object tag) {
        if (reqQueue != null) {
            reqQueue.cancelAll(tag);
        }
    }
    public void setCookie() {
		DefaultHttpClient  mHttpClient = new DefaultHttpClient();
		  CookieStore cs = mHttpClient.getCookieStore();
		  // create a cookie
		  cs.addCookie(new BasicClientCookie2("cookie", "spooky"));
    }
    /**
     * 使用方法

 

   public void CacheImage(View view){
        Bitmap defaultImage=BitmapFactory.decodeResource(getResources(), R.drawable.loading);
        Bitmap errorImage=BitmapFactory.decodeResource(getResources(), R.drawable.load_error);
        ImageCacheManager.loadImage(this, url, imageView, defaultImage, errorImage);
    }
     */
}