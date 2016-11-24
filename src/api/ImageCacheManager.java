package api;


import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.td.tradedistance.app.utils.Logger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 图片缓存管理类（方便外部调用）
 * 这里向外部提供了一个loadImage的重载方法，一个传入加载图片的宽高，一个默认加载原图，使得外部不再需要关注任何关于缓存的操作。
 * @author zhenghg
 *
 */
/**
 * 图片缓存管理类 获取ImageLoader对象
 * @author Javen
 *
 */
public class ImageCacheManager {
    private static String TAG = ImageCacheManager.class.getSimpleName();
    private static Context mContext;
    /**
     * 获取ImageListener
     * 
     * @param view
     * @param defaultImage
     * @param errorImage
     * @return
     */
    public static ImageListener getImageListener(final ImageView view, final Bitmap defaultImage, final Bitmap errorImage,final ListView list) {
        return new ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // 回调失败
                if (errorImage != null) {
                    view.setImageBitmap(errorImage);
                }
            }

            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                // 回调成功
                if (response.getBitmap() != null) {
                	if(list.findViewWithTag(view.getTag())!=null){
                		 /* AvatarDrawableFactory avatarDrawableFactory = new AvatarDrawableFactory(HSApp.getContext().getResources());
   	              	 Drawable roundedAvatarDrawable = null;
   	              	 roundedAvatarDrawable = avatarDrawableFactory.getRoundedAvatarDrawable(response.getBitmap());
   	              	 view.setImageDrawable(roundedAvatarDrawable);*/
   	                 view.setImageBitmap(response.getBitmap());
                	}
                	
	               
                } else if (defaultImage != null) {
                    view.setImageBitmap(defaultImage);
                }
            }
        };

    }
    public static ImageListener getImageListener(final ImageView view, final Bitmap defaultImage, final Bitmap errorImage) {
        return new ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // 回调失败
                if (errorImage != null) {
                    view.setImageBitmap(errorImage);
                }
            }

            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                // 回调成功
                if (response.getBitmap() != null) {
                		 /* AvatarDrawableFactory avatarDrawableFactory = new AvatarDrawableFactory(HSApp.getContext().getResources());
   	              	 Drawable roundedAvatarDrawable = null;
   	              	 roundedAvatarDrawable = avatarDrawableFactory.getRoundedAvatarDrawable(response.getBitmap());
   	              	 view.setImageDrawable(roundedAvatarDrawable);*/
   	                 view.setImageBitmap(response.getBitmap());
	               
                } else if (defaultImage != null) {
                    view.setImageBitmap(defaultImage);
                }
            }
        };

    }
    /**
     * 提供给外部调用方法
     * 
     * @param url
     * @param view
     * @param defaultImage
     * @param errorImage
     */
    public static void loadImage(Context context,String url, ImageView view, Bitmap defaultImage, Bitmap errorImage,ListView listView) {
    	Logger.d("URL", url);
    	VolleyController.getInstance(context).getImageLoader(context).get(url, ImageCacheManager.getImageListener(view, defaultImage, errorImage,listView), 0, 0);
    }
    public static void loadImage(Context context,String url, ImageView view, Bitmap defaultImage, Bitmap errorImage) {
        Logger.d("URL", url);
    	VolleyController.getInstance(context).getImageLoader(context).get(url, ImageCacheManager.getImageListener(view, defaultImage, errorImage), 0, 0);
    }
    /**
     * 提供给外部调用方法
     * 
     * @param url
     * @param view
     * @param defaultImage
     * @param errorImage
     */
    public static void loadImage(Context context,String url, ImageView view, Bitmap defaultImage, Bitmap errorImage, int maxWidth, int maxHeight,ListView listView) {
    	VolleyController.getInstance(context).getImageLoader(context).get(url, ImageCacheManager.getImageListener(view, defaultImage, errorImage,listView), maxWidth, maxHeight);
    }
    public static void loadImage(Context context,String url, ImageView view, Bitmap defaultImage, Bitmap errorImage, int maxWidth, int maxHeight) {
    	VolleyController.getInstance(context).getImageLoader(context).get(url, ImageCacheManager.getImageListener(view, defaultImage, errorImage), maxWidth, maxHeight);
    }
    /**
     * 设置默认图片
     */
    public static Bitmap setDefaultImage(Context context, int value){
    	Bitmap	defaultImage = BitmapFactory.decodeResource(context.getResources(),value);
		return defaultImage;
    }
    /**
     * 设置错误图片
     */
    public static Bitmap setErrorImage(Context context, int value){
    	Bitmap	errorImage = BitmapFactory.decodeResource(context.getResources(),value);
		return errorImage;
    }
}