package org.lenve.databinding1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.lenve.databinding1.adapter.DataBindAdapter;
import org.lenve.databinding1.adapter.NewsAdapter;
import org.lenve.databinding1.constant.Constant;
import org.lenve.databinding1.data.BaseListModel;
import org.lenve.databinding1.data.DataBindNews;
import org.lenve.databinding1.data.DayNews;
import org.lenve.databinding1.http.HttpMethod;
import org.lenve.databinding1.http.HttpUser;
import org.lenve.databinding1.ui.Base2Activity;
import org.lenve.databinding1.ui.PullToRefreshView;
import org.lenve.databinding1.util.GsonUtils;
import org.lenve.databinding1.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class DayNewsActivity extends Base2Activity implements AdapterView.OnItemClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {

	private TextView title;
	private PullToRefreshView mRefreshView;
//	private List<DayNews> list;
	private List<DataBindNews> list;
//	private List<DayNews> listPage;
	private List<DataBindNews> listPage;
	private ListView listArticle;
	private LinearLayout msgLayout;
	private LinearLayout linear;
//	private NewsAdapter newsAdapter;
	private DataBindAdapter<DataBindNews> newsAdapter;
	private int mCurrentPage = 1;
	private int mTotalPage = 0;
	private String TAG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_activity);
		TAG = "DayNewsActivity";
		title = (TextView) findViewById(R.id.title_top);
		title.setText("资讯");
		ImageView titleimgLeft = (ImageView) findViewById(R.id.title_left);
		titleimgLeft.setImageResource(R.mipmap.top_back);
		msgLayout = (LinearLayout) findViewById(R.id.msg_layout);
		linear = (LinearLayout) findViewById(R.id.msg_load);
		mRefreshView = (PullToRefreshView) findViewById(R.id.pullToRefreshView1);
		listArticle = (ListView) findViewById(R.id.msg_listview);
		linear = (LinearLayout) findViewById(R.id.msg_load);
		mRefreshView.setOnHeaderRefreshListener(this);
		mRefreshView.setOnFooterRefreshListener(this);
		msgLayout.setVisibility(View.GONE);
		linear.setVisibility(View.VISIBLE);
		listArticle.setOnItemClickListener(this);
		list = new ArrayList<>();
		listPage = new ArrayList<>();
		newsAdapter = new DataBindAdapter<>(this, R.layout.listview_item, listPage, BR.news);
		listArticle.setAdapter(newsAdapter);
		gainData();
	}


	private void gainData() {
		String url = Constant.URLNEWS;
		LogUtil.e(TAG, "url=" + url);
		HttpUser.get(url, new AsyncHttpResponseHandler(){

			public void onFailure(int arg0, Header[] arg1,
								  byte[] arg2, Throwable arg3) {
				LogUtil.e(TAG, "err=" + arg3.getMessage());
				msgLayout.setVisibility(View.VISIBLE);
				linear.setVisibility(View.GONE);
				Toast.makeText(DayNewsActivity.this, "网络异常!", Toast.LENGTH_SHORT).show();
			}


			public void onSuccess(int arg0, Header[] arg1,
								  byte[] arg2) {
				String str = new String(arg2);
				LogUtil.e(TAG, "result=" + str);
				BaseListModel<DataBindNews> info = null;
				try {
					info = GsonUtils.parseJSON(str, new TypeToken<BaseListModel<DataBindNews>>() {
					}.getType());
				}catch (JsonSyntaxException e){
					LogUtil.e(TAG, "err=" + e.getMessage());
					e.printStackTrace();
				}
				if (info != null && info.getMsg() != null && info.getMsg().size() > 0) {
					list = info.getMsg();
					for (int i = 0; i < 10; i++){
						listPage.add(list.get(i));
					}
					msgLayout.setVisibility(View.VISIBLE);
					linear.setVisibility(View.GONE);
					newsAdapter.notifyDataSetChanged();
					mTotalPage = list.size() / 10;
					LogUtil.e(TAG, "total page=" + mTotalPage);
				}else {
					msgLayout.setVisibility(View.VISIBLE);
					linear.setVisibility(View.GONE);
					Toast.makeText(DayNewsActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
				}
			}

		});

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Intent intent = new Intent(this, WebviewActivity.class);
//		intent.putExtra("link", list.get(arg2).getUrl());
//		intent.putExtra("title", list.get(arg2).getTitle());//"hide"
//		startActivity(intent);
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		if (mCurrentPage < mTotalPage) {
			if (list != null && list.size() > 0)
			{
				for (int i = mCurrentPage * 10; i < (mCurrentPage + 1) * 10; i++) {

					listPage.add(list.get(i));
				}
				newsAdapter.notifyDataSetChanged();
			}
			mCurrentPage ++;
		}else{
			HttpMethod.Toast(this, "没有数据可加载了！");
		}
		mRefreshView.onFooterRefreshComplete();
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		list.clear();
		listPage.clear();
		mCurrentPage = 1;
		msgLayout.setVisibility(View.GONE);
		linear.setVisibility(View.VISIBLE);
		gainData();
		mRefreshView.onHeaderRefreshComplete();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		HttpUser.cancel(this);
	}

}
