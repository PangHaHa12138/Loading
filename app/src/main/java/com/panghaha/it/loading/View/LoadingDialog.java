package com.panghaha.it.loading.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.panghaha.it.loading.R;

/**
 * 加载中Dialog
 * 
 * @author lexyhp
 */
public class LoadingDialog extends AlertDialog {

	private TextView tips_loading_msg;
	private int layoutResId;
	private String message = null;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            上下文
	 * @param layoutResId
	 *            要传入的dialog布局文件的id
	 */
	public LoadingDialog(Context context, int layoutResId) {
		super(context);
		this.layoutResId = layoutResId;
		message = context.getResources().getString(R.string.loading);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(layoutResId);
		tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
		tips_loading_msg.setText(this.message);
	}

}
