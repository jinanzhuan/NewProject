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

public class ae implements FileFilter {
    private long a;
    ae(long arg2) {
        super();
        this.a = arg2;
    }

    @Override
    public boolean accept(File arg5) {
        boolean v0 = arg5.lastModified() < this.a ? true : false;
        return v0;
    }
}
