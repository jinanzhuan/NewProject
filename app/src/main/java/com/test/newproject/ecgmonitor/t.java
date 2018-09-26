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

public class t implements s {
    private boolean a;
    private double[] b;
    private float c;
    private int d;
    private int e;

    public t() {
        super();
        this.a = true;
        this.b = new double[6];
        this.setMainsFrequency(60);
        this.a = true;
        this.d = 0;
        this.c = 0f;
    }

    @Override
    public double a(double arg10) {
        int v0;
        if(this.a) {
            this.d = 0;
            for(v0 = 0; v0 < this.e; ++v0) {
                this.b[v0] = arg10;
            }

            this.a = false;
        }
        else {
            this.b[this.d] = arg10;
            double v2 = 0;
            for(v0 = 0; v0 < this.e; ++v0) {
                v2 += this.b[v0];
            }

            arg10 = v2 / (((double)this.e));
            float v2_1 = 0f;
            for(v0 = 0; v0 < this.e; ++v0) {
                double v4 = this.b[v0];
                double v6 = ((double)v2_1);
                v2 = v4 > arg10 ? v4 - arg10 : arg10 - v4;
                v2_1 = ((float)(v2 + v6));
            }

            this.c = ((float)((((double)(v2_1 / (((float)this.e)) - this.c))) * 0.01 + (((double)this.c))));
            ++this.d;
            if(this.d != this.e) {
                return arg10;
            }

            this.d = 0;
        }

        return arg10;
    }

    @Override
    public void a() {
        this.a = true;
    }

    @Override
    public void b() {
        int v0 = 0;
        this.d = 0;
        while(v0 < this.e) {
            this.b[v0] = 0;
            ++v0;
        }

        this.c = 0f;
    }

    @Override
    public int c() {
        return this.e / 2;
    }

    public float d() {
        return this.c;
    }

    @Override
    public void setMainsFrequency(int arg2) {
        int v0 = arg2 == 50 ? 6 : 5;
        this.e = v0;
    }
}
