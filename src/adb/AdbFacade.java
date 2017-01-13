package adb;/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */



import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.intellij.openapi.project.Project;

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
                String localProperties = project.getBasePath() + "/local.properties";

                if (!AdbUtil.isFileExists(localProperties)) {
                    error("local.properties not found");
                    return;
                }

                String androidSdkPath = AdbUtil.sdkPath(localProperties);

                if (AdbUtil.isDirExists(androidSdkPath)) {
                    androidSdkPath = androidSdkPath + "/platform-tools/";
                } else {
                    error("Android SDK path not found");
                    return;
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
