package com.yl.updatedemo;

import android.app.Application;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yl.updatedemo.bean.MyUpdate;

import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.callback.UpdateCheckCB;
import org.lzh.framework.updatepluginlib.callback.UpdateDownloadCB;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.model.UpdateParser;

import java.io.File;

import static com.yl.updatedemo.Global.url;

/**
 * Created by Administrator on 2016/11/13 0013.
 */

public class MyAppliaction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 建议在Application中进行配置。
// UpdateConfig为全局配置。当在其他页面中。使用UpdateBuilder进行检查更新时。
// 对于没传的参数，会默认使用UpdateConfig中的全局配置
        UpdateConfig.getConfig()
                // url 与 checkEntity方法可任选一种填写，且至少必填一种。
                // 数据更新接口数据，此时默认为使用GET请求
                .url(url)
                // 同url方法。CheckEntity方法可填写url,params,method。可在此设置为post请求
//                .checkEntity(checkEntity)
                // 必填：用于从数据更新接口获取的数据response中。解析出Update实例。以便框架内部处理
                .jsonParser(new UpdateParser() {
                    @Override
                    public Update parse(String response) {
                        // 此处根据上面url接口返回的数据response进行update类组装。框架内部会使用此
                        // 组装的update实例判断是否需要更新以做进一步工作
                        Gson gson = new Gson();
                        MyUpdate.OriginalBean original = gson.fromJson(response, MyUpdate.class).getOriginal();
                        Update update = new Update(response);//设计这个有参构造意义何在??
                        update.setForced(original.isForced());
                        // update.setForced(true);
                        update.setIgnore(original.isIgnore());
                        update.setUpdateContent(original.getUpdateContent());
                        update.setUpdateTime(original.getUpdateTime() == 0 ? System.currentTimeMillis() : original.getUpdateTime());
                        update.setUpdateUrl(original.getUpdateUrl());
                        update.setVersionCode((int) original.getVersionCode());//版本号设计成int类型也是醉了
                        update.setVersionName(original.getVersionName());
                        return update;
                    }
                })
                .checkCB(new UpdateCheckCB() {

                    @Override
                    public void onCheckError(int code, String errorMsg) {
                        Toast.makeText(MyAppliaction.this, "更新失败：code:" + code + ",errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserCancel() {
                        Toast.makeText(MyAppliaction.this, "用户取消更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCheckIgnore(Update update) {
                        Toast.makeText(MyAppliaction.this, "用户忽略此版本更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void hasUpdate(Update update) {
                        Toast.makeText(MyAppliaction.this, "检查到有更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noUpdate() {
                        Toast.makeText(MyAppliaction.this, "无更新", Toast.LENGTH_SHORT).show();
                    }
                })
                // apk下载的回调
                .downloadCB(new UpdateDownloadCB() {
                    @Override
                    public void onUpdateStart() {
                        Toast.makeText(MyAppliaction.this, "下载开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateComplete(File file) {
                        Toast.makeText(MyAppliaction.this, "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateProgress(long current, long total) {
                    }

                    @Override
                    public void onUpdateError(int code, String errorMsg) {
                        Toast.makeText(MyAppliaction.this, "下载失败：code:" + code + ",errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
