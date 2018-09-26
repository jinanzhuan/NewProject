package com.test.newproject.ecgmonitor;

import java.io.File;
import java.io.FileFilter;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class af implements FileFilter {
    private long a;

    af(long arg2) {
        super();
        this.a = arg2;
    }

    @Override
    public boolean accept(File arg5) {
        boolean v0 = arg5.lastModified() >= this.a || !arg5.getName().endsWith(".pdf") ? false : true;
        return v0;
    }
}
