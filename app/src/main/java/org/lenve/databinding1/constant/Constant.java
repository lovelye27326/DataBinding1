package org.lenve.databinding1.constant;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.lenve.databinding1.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Constant {

//	public static final String url = "http://khd.funnypicsbox.com/jokes/";//福岛笑话

	public static String URLNEWS = "http://888.shof789.com/Home/Outs/article/type/1";//时时中彩票新闻
	/**
	 * 请求条数
	 */
	public static final int PAGESIZE=10;
	/**
	 * 请求失败
	 */
	public static final int RESPONSE_CODE_FAILED = -100;

	/**
	 * joke
	 */
	public static final String GetJokeList = "0_4247.json";

	public static final String URL_JOKE_LIKE = "xihuan_nr.asp";

	public static final String URL_JOKE_UNLIKE = "xihuan_nr_qx.asp";

	public static final String IS_OPEN = "is_open";


	public static final String URL_NEWS = "article.html";

	public static String URL_SHARE = "http://888.shof789.com/Home/Outs/index/mchid/59103170cf3b5.html";//北京赛车pk10分享  5910363b9e811  彩票 59103170cf3b5

	public static String TULING_ROBOT = "http://www.tuling123.com/openapi/api";//图灵机器人

	public static String TULING_KEY = "5a4b5c8bbf2c8a9dd02861999fa0d45c";

	public static String DEVICE_ID = "device_id";

	public static String URL_SAVE = "url";

	public static String URL_COOKIE = "cookie";

	public static String EXIT_TIME = "time";

	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
	 .imageScaleType(com.nostra13.universalimageloader.core.assist.ImageScaleType.EXACTLY)
     .showStubImage(R.mipmap.empty_photo)    //在ImageView加载过程中显示图片
     .showImageForEmptyUri(R.mipmap.empty_photo)  //image连接地址为空时
     .showImageOnFail(R.mipmap.empty_photo)  //image加载失败
     .cacheInMemory(true)  //加载图片时会在内存中加载缓存
     .cacheOnDisc(true)   //加载图片时会在磁盘中加载缓存
      .bitmapConfig(Bitmap.Config.RGB_565)
     .build();

	public static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	/**图片加载监听事件**/
  private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500); //设置image隐藏动画500ms
                    displayedImages.add(imageUri); //将图片uri添加到集合中
                }
            }
        }
    }
}
