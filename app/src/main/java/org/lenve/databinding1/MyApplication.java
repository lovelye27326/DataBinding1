package org.lenve.databinding1;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.lenve.databinding1.constant.Constant;

/**
 * Created by Administrator on 2017/8/8.
 */

public class MyApplication extends Application {
    protected static volatile MyApplication instance;
    public static ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader(this);
    }

    private void initImageLoader(Context context) {
        // 获取本地缓存的目录，该目录在SDCard的根目录下
        // File cacheDir = StorageUtils.getOwnCacheDirectory(context,
        // "Mall/UIL/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(BuildConfig.DEBUG)) // default
                .defaultDisplayImageOptions(Constant.options) // default
                .writeDebugLogs()
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }


    public static MyApplication getInstance() {
        return instance;
    }

}
