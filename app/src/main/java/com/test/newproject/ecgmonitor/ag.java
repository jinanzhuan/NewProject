package com.test.newproject.ecgmonitor;

import java.io.File;
import java.util.Comparator;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class ag implements Comparator {
    public int a(File arg5, File arg6) {
        return Long.valueOf(arg5.lastModified()).compareTo(Long.valueOf(arg6.lastModified()));
    }

    @Override
    public int compare(Object arg2, Object arg3) {
        return this.a(((File)arg2), ((File)arg3));
    }
}
