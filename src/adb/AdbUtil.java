/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package adb;


import org.apache.http.util.TextUtils;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class AdbUtil {


    public static String sdkPath(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("sdk.dir")) {
                    String[] temp = line.split("=");
                    if (temp != null && temp.length == 2) {
                        return temp[1];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean isDirExists(String path) {
        do {

            if (TextUtils.isEmpty(path)) {
                break;
            }
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                return true;
            }

        } while (false);

        return false;
    }

    public static boolean isFileExists(String path) {
        do {

            if (TextUtils.isEmpty(path)) {
                break;
            }
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                return true;
            }

        } while (false);

        return false;
    }


}
