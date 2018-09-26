package com.test.newproject.ecgmonitor;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class FilterEnhanced implements s {
    static {
        System.loadLibrary("FilterEnhanced");
    }

    public FilterEnhanced() {
        super();
        this.setMainsFrequency(60);
        this.a();
    }

    @Override
    public double a(double arg1) {
        return 0;
    }

    @Override
    public void a() {
    }

    @Override
    public void b() {

    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public void setMainsFrequency(int arg1) {

    }

    public float[] runEF(float[] v4, int v12, int m) {
        return new float[0];
    }
}
