package com.ratulsikder.neolife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WebView homeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeWebView = (WebView) findViewById(R.id.homeWeb);
//
        homeWebView.setWebViewClient(new HomeWebViewClient());
        WebSettings homeWebSetting = homeWebView.getSettings();
        homeWebSetting.setJavaScriptEnabled(true);
        homeWebSetting.setSupportZoom(false);
        homeWebSetting.setAllowFileAccess(true);
        homeWebSetting.setDomStorageEnabled(true);
        homeWebView.addJavascriptInterface(new HomeWebInterface(this), "ANDROID");
        homeWebView.clearCache(true);
        homeWebView.loadUrl("192.168.10.91:80");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && homeWebView.canGoBack()) {
            homeWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    public class HomeWebInterface {
        Context webContext;
        HomeWebInterface(Context context) {
            webContext = context;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(webContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String renderAllTask() {
            TaskHelper taskHelper = new TaskHelper(getBaseContext());
            SQLiteDatabase taskHelperDb = taskHelper.getReadableDatabase();
            Cursor cursor = taskHelperDb.query(TaskContract.TaskEntry.TABLE_NAME, new String[]{BaseColumns._ID, TaskContract.TaskEntry.COLUMN_NAME_TITLE, TaskContract.TaskEntry.COLUMN_NAME_SUBTITLE}, null, null,null, null, null);

            List tasks = new ArrayList<JSONObject>();
            while(cursor.moveToNext()) {
                Task task = new Task();
                task.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TITLE)));
                task.setSubtitle(cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_SUBTITLE)));
                tasks.add(task);
            }
            cursor.close();

            return   new Gson().toJson(tasks);
        }
    }
//
    private class HomeWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }
}