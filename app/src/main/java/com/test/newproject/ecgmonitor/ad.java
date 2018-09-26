package com.test.newproject.ecgmonitor;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Arrays;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class ad {
    private static Context a;

    public static File a() {
        return new File(ad.a.getFilesDir() + File.separator + "ecgs");
    }

    public static String a(String arg2) {
        return "ecg-" + arg2 + ".atc";
    }

    public static void a(Context arg0) {
        ad.a = arg0;
    }

    public static File b() {
        return ad.a.getCacheDir();
    }

    public static String b(String arg2) {
        return "ecg-" + arg2 + "-enhanced.atc";
    }

    public static File c() {
        return new File(ad.a.getFilesDir() + File.separator + "temp");
    }

    public static File c(String arg3) {
        return new File(ad.a(), ad.a(arg3));
    }

    public static File d(String arg3) {
        return new File(ad.a(), ad.b(arg3));
    }

    public static void d() {
        try {
            File v0_1 = ad.c();
            if(!v0_1.isDirectory()) {
                return;
            }

            File[] v1 = v0_1.listFiles(new ae(System.currentTimeMillis() - 172800000));
            int v0_2;
            for(v0_2 = 0; v0_2 < v1.length; ++v0_2) {
                v1[v0_2].delete();
            }
        }
        catch(Exception v0) {
            Log.e("EcgMonitor.Util", "Exception deleting temp files", ((Throwable)v0));
        }
    }

    public static void e() {
        int v0 = 0;
        try {
            File v1 = ad.b();
            long v2 = System.currentTimeMillis() - 172800000;
            if(!v1.isDirectory()) {
                return;
            }

            File[] v4 = v1.listFiles(new af(v2));
            v2 = 0;
            int v1_1;
            for(v1_1 = 0; v1_1 < v4.length; ++v1_1) {
                v2 += v4[v1_1].length();
            }

            if(v2 <= 2000000) {
                return;
            }

            Arrays.sort(((Object[])v4), new ag());
            while(true) {
                label_24:
                if(v0 >= v4.length) {
                    return;
                }

                v2 -= v4[v0].length();
                v4[v0].delete();
                if(v2 >= 1000000) {
                    break;
                }

                return;
            }
        }
        catch(Exception v0_1) {
            Log.e("EcgMonitor.Util", "Exception deleting cache files", ((Throwable)v0_1));
        }

        ++v0;

    }
}
