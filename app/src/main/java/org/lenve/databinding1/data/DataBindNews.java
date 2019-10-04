package org.lenve.databinding1.data;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.lenve.databinding1.BR;
import org.lenve.databinding1.WebviewActivity;
import org.lenve.databinding1.util.UILRequestManager;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class DataBindNews extends BaseObservable implements Serializable{

        /**
         * id : 206
         * title : 衡水幸运彩民狂中9枚“金如意”
         * summary : 幸运彩民张先生一举斩获9枚“金如意”，圆了自己的中奖梦。自4月10日—5月31日，河北省福彩中心开展网点即开票“一刮如意”促销活动，彩民购买任意一款刮刮乐彩票，单张中得500元奖金，即可获得“金如意”一枚（足金5g）；单张中得5000元奖金，即可获得价值2万元加油卡。
         * logofile : http://m.zhcw.com/upload/resources/image/2017/04/28/566021.png
         * url : http://m.zhcw.com/khd/zx/tt/gdlb/10366387.shtml
         * publishdate : 2017-04-28 14:31:02
         */

        private String id;
        private String title;
        private String summary;
        private String logofile;
        private String url;
        private String publishdate;

//    public DataBindNews() {
//    }

//    public DataBindNews(String id, String title, String summary, String logofile, String url, String publishdate) {
//        this.id = id;
//        this.title = title;
//        this.summary = summary;
//        this.logofile = logofile;
//        this.url = url;
//        this.publishdate = publishdate;
//    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyPropertyChanged(BR.title);
    }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

    @BindingAdapter("pic")
    public static void getInternetImage(ImageView iv, String pic) {
        UILRequestManager.displayImage(pic, iv);
    }

    public void onItemTip(View view) {
//        setTitle("111");//点击动态改变item的UI

        Context context = view.getContext();
//        Toast.makeText(context, getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("link", getUrl());
        intent.putExtra("title", getTitle());//"hide"
        context.startActivity(intent);
    }

    public String getLogofile() {
            return logofile;
        }

        public void setLogofile(String logofile) {
            this.logofile = logofile;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPublishdate() {
            return publishdate;
        }

        public void setPublishdate(String publishdate) {
            this.publishdate = publishdate;
        }
}
