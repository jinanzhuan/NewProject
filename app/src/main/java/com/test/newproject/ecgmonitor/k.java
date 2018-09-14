package com.test.newproject.ecgmonitor;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/14
 *     desc   :
 *     modify :
 * </pre>
 */

public class k implements Runnable {
    private j a;
    public k (j j) {
        this.a = j;
    }
    @Override
    public void run() {
        int v6;
        double v10 = 32767;
        double v8 = -32768;
        int v4 = 150;
        j.a(this.a, false);
    }
}
