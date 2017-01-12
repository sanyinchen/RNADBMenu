package adb;/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */


import com.android.ddmlib.IDevice;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ui.NotificationHelper.error;
import static ui.NotificationHelper.info;


public class AdbFacade {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("AdbRN-%d").build());

    public static void invokeDevmenu(Project project) {
        executeOnDevice(project);

    }


    private static void executeOnDevice(final Project project) {
        EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                String androidSdkPath = AdbUtil.sdkPath(project.getBasePath() + "/local.properties");

                if (AdbUtil.isExists(androidSdkPath)) {
                    androidSdkPath = androidSdkPath + "/platform-tools/";
                } else {

                    if (AndroidSdkUtils.getAndroidSdkPathsFromExistingPlatforms().size() > 0) {
                        androidSdkPath = Iterables.get(AndroidSdkUtils.getAndroidSdkPathsFromExistingPlatforms(), 0);
                        androidSdkPath = androidSdkPath + "/platform-tools/";
                    } else {
                        error("Android SDK path not found");
                        return;
                    }
                }

                try {
                    //WindowManager.getInstance().getStatusBar(project).setInfo("adb kill-server...");
                    //Runtime.getRuntime().exec(androidSdkPath + "adb kill-server");
                    //WindowManager.getInstance().getStatusBar(project).setInfo("adb start-server...");
                    Process p = Runtime.getRuntime().exec(androidSdkPath + "adb shell input keyevent 82");
                    if (p.waitFor() != 0) {
                        error("invoke rn dev failed,please check connected");
                    } else {
                        info("invoke rn dev successfully");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    error(e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    error(e.getMessage());
                }
            }
        });

    }
}
