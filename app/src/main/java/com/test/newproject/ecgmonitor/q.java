package com.test.newproject.ecgmonitor;

import android.support.annotation.WorkerThread;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public interface q {
    void a(boolean arg1, boolean arg2);

    void a(int arg1);

    void a(int arg1, boolean arg2);

    @WorkerThread
    void a(short[] arg1, int arg2, int arg3);

    long a(f arg1);

    @WorkerThread void a(long arg1);

    void b(int arg1);

    void b(long arg1);

    void c(int arg1);

    void d();

    void e(boolean arg1);

    void e();

    void f(boolean arg1);
}
