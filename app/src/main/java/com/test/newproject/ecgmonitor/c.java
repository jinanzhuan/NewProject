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

public class c {
    private int a;
    private int b;
    private int c;
    private short[] d;

    public c(int arg3) {
        super();
        int v0 = c(arg3);
        this.c = v0 - 1;
        this.a = v0;
        this.d = new short[this.a];
        this.b = 0;
    }

    public void a() {
        this.b = 0;
    }

    public void a(short arg4) {
        this.d[this.b & this.c] = arg4;
        ++this.b;
    }

    public void a(short[] arg4, int arg5, int arg6) {
        while(arg5 < arg6) {
            this.d[this.b & this.c] = arg4[arg5];
            ++this.b;
            ++arg5;
        }
    }

    public void a(int arg6) {
        int v2 = c(arg6);
        if(v2 != this.a) {
            short[] v3 = new short[v2];
            int v0 = v2 > this.a ? this.b - this.a : this.b - v2;
            int v1 = 0;
            while(v0 < this.b) {
                v3[v1] = this.b(v0);
                ++v1;
                ++v0;
            }

            this.d = null;
            this.d = v3;
            this.c = v2 - 1;
            this.b = v1;
            this.a = v2;
        }
    }

    public int b() {
        return this.b;
    }

    public short b(int arg3) {
        return this.d[this.c & arg3];
    }

    private static int c(int arg2) {
        return 1 << 32 - Integer.numberOfLeadingZeros(arg2 - 1);
    }
}
