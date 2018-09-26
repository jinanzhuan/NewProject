package com.test.newproject.ecgmonitor;

import android.content.pm.PackageInfo;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class f {
    public long a;
    public int b;
    public h c;
    public g d;
    public int[] e;
    public int f;
    private static final byte[] g;
    private static final byte[] h;
    private static final byte[] i;
    private static final byte[] j;
    private static final byte[] k;
    private static final long l;
    private static final long m;
    private static final long n;
    private static final long o;
    private File p;
    private RandomAccessFile q;
    private long r;
    private int s;
    private long t;
    private Location u;
    private float v;
    private int w;
    private PackageInfo x;
    private a[] y;
    private int z;

    static {
        g = new byte[]{65, 76, 73, 86, 69, 0, 0, 0};
        h = new byte[]{105, 110, 102, 111};
        i = new byte[]{102, 109, 116, 32};
        j = new byte[]{101, 99, 103, 32};
        k = new byte[]{97, 110, 110, 32};
        l = com.test.newproject.ecgmonitor.a.a(h);
        m = com.test.newproject.ecgmonitor.a.a(i);
        n = com.test.newproject.ecgmonitor.a.a(j);
        o = com.test.newproject.ecgmonitor.a.a(k);
    }

    public f(PackageInfo arg3) {
        super();
        this.v = 0f;
        this.w = 50;
        this.y = new a[2];
        this.z = 0;
        this.b = 0;
        this.c = new h(this);
        this.d = new g(this);
        this.f = 0;
        this.a(arg3);
    }

    public f() {
        super();
        this.v = 0f;
        this.w = 50;
        this.y = new a[2];
        this.z = 0;
        this.b = 0;
        this.c = new h(this);
        this.d = new g(this);
        this.f = 0;
    }

    public void a(int arg1) {
        this.b = arg1;
    }


    public int a(byte[] arg7, int arg8, int arg9) {
        int v1_1;
        int v0 = 0;
        if(this.q != null && arg8 < this.s) {
            if(arg8 + arg9 > this.s) {
                arg9 = this.s - arg8;
            }

            try {
                this.q.seek(this.t + (((long)(arg8 * 2))));
                v1_1 = this.q.read(arg7, 0, arg9 * 2);
            }
            catch(IOException v1) {
                v1_1 = 0;
                Log.e("EcgFile", "error reading from file");
            }

            v1_1 /= 2;
            if(v1_1 < 0) {
                return v0;
            }

            v0 = v1_1;
        }

        return v0;
    }

    public int a() {
        return this.f;
    }

    public void a(int[] arg3, int arg4) {
        if(this.e == null || this.e.length < arg4) {
            this.e = new int[arg4];
        }

        System.arraycopy(arg3, 0, this.e, 0, arg4);
        this.f = arg4;
    }

    public void a(PackageInfo arg1) {
        this.x = arg1;
    }

    private float a(int[] arg9, int arg10, int arg11) {
        int v3;
        int v0;
        int v2;
        for(v3 = 0; v3 < arg10; ++v3) {
            int v4 = arg9[v3];
            for(v0 = 0; v4 < arg9[v0]; ++v0) {
                if(v0 >= v3) {
                    break;
                }
            }

            for(v2 = v3 - 1; v2 >= v0; --v2) {
                arg9[v2 + 1] = arg9[v2];
            }

            arg9[v0] = v4;
        }

        v0 = arg10 * arg11 / 100;
        if(v0 > arg10) {
            v0 = arg10;
        }

        v3 = arg10 - v0 >> 1;
        v2 = 0;
        float v1 = 0f;
        while(v2 < v0) {
            v1 += ((float)arg9[v3 + v2]);
            ++v2;
        }

        return v1 / (((float)v0));
    }

    private static long a(a arg6, int arg7, int arg8) {
        long v2 = 0;
        byte[] v1 = arg6.h();
        int v0;
        for(v0 = 0; v0 < arg8; ++v0) {
            v2 += ((long)(v1[arg7 + v0] & 255));
        }

        return v2;
    }

    public static void a(File arg5, File arg6) throws IOException {
        FileInputStream v0 = new FileInputStream(arg5);
        FileOutputStream v1 = new FileOutputStream(arg6);
        byte[] v2 = new byte[2048];
        while(true) {
            int v3 = ((InputStream)v0).read(v2);
            if(v3 <= 0) {
                break;
            }

            ((OutputStream)v1).write(v2, 0, v3);
        }

        ((InputStream)v0).close();
        ((OutputStream)v1).close();
    }

    private static void a(RandomAccessFile arg8, int[] arg9, int arg10, int arg11) throws IOException {
        int v0 = arg11 - arg10;
        if(v0 >= 1) {
            a v2 = com.test.newproject.ecgmonitor.a.a(v0 * 6 + 20);
            v2.b(com.test.newproject.ecgmonitor.f.k);
            v2.a(((long)(v0 * 6 + 4)));
            v2.a(300);
            for(v0 = 0; v0 < arg11; ++v0) {
                v2.a(((long)arg9[arg10 + v0]));
                v2.c(1);
            }

            v2.a(com.test.newproject.ecgmonitor.f.a(v2, 0, v2.b()) & -1);
            arg8.write(v2.h(), 0, v2.b());
        }
    }

    private boolean a(byte[] arg5, byte[] arg6, int arg7) {
        boolean v0 = false;
        if(arg5 != null && arg6 != null && arg5.length >= arg7 && arg6.length >= arg7) {
            int v1 = 0;
            while(true) {
                if(v1 >= arg7) {
                    break;
                }
                else if(arg5[v1] == arg6[v1]) {
                    ++v1;
                    continue;
                }

                return v0;
            }

            v0 = true;
        }

        return v0;
    }

    @NonNull public static float[] a(@NonNull float[] arg3, int arg4) {
        float v1 = (((float)arg4)) / 1000000f;
        int v0;
        for(v0 = 0; v0 < arg3.length; ++v0) {
            arg3[v0] *= v1;
        }

        return arg3;
    }

    public boolean b(int arg6) {
        boolean v0 = false;
        int v1 = this.s - arg6;
        if(v1 >= 0) {
            if(this.e == null || this.f >= this.e.length) {
                int[] v2 = new int[this.f + 512];
                if(this.e != null) {
                    System.arraycopy(this.e, 0, v2, 0, this.e.length);
                }

                this.e = null;
                this.e = v2;
            }

            int[] v0_1 = this.e;
            int v2_1 = this.f;
            this.f = v2_1 + 1;
            v0_1[v2_1] = v1;
            v0 = true;
        }

        return v0;
    }

    public int[] b() {
        return this.e;
    }

    public void c(int arg11) {
        int v0 = 0;
        if(this.q != null) {
            try {
                int v4 = (this.z + 1) % 2;
                int v2 = this.y[this.z].b();
                int v1 = this.y[v4].b();
                this.s -= v2 / 2;
                this.s -= v1 / 2;
                Log.i("EcgFile", "Before Adjustment: prevBufferBytes=" + v1 + " currBufferBytes=" + v2 + "  totalsample=" + this.s);
                int v3 = arg11 * 2;
                if(v2 >= v3) {
                    v3 = v2 - v3;
                    v2 = v1;
                }
                else {
                    v2 = v3 - v2;
                    if(v1 >= v2) {
                        v2 = v1 - v2;
                        v3 = 0;
                    }
                    else {
                        v2 = 0;
                        v3 = 0;
                    }
                }

                if(v2 > 0) {
                    byte[] v4_1 = this.y[v4].h();
                    for(v1 = 0; v1 < v2; ++v1) {
                        this.r += ((long)(v4_1[v1] & 255));
                    }

                    this.q.write(v4_1, 0, v2);
                    this.s += v2 / 2;
                }

                if(v3 > 0) {
                    byte[] v1_1 = this.y[this.z].h();
                    while(v0 < v3) {
                        this.r += ((long)(v1_1[v0] & 255));
                        ++v0;
                    }

                    this.q.write(v1_1, 0, v3);
                    this.s += v3 / 2;
                }

                Log.i("EcgFile", "After Adjustment: prevBufferBytes=" + v2 + " currBufferBytes=" + v3 + "  totalsample=" + this.s);
            }
            catch(Exception v0_1) {
                Log.e("EcgFile", "Exception updating file", ((Throwable)v0_1));
            }
        }
    }

    public void d() {
        this.s = 0;
        this.f = 0;
        if(this.y[0] != null) {
            this.y[0].d();
        }

        if(this.y[1] != null) {
            this.y[1].d();
        }

        if(this.q != null) {
            try {
                this.q.setLength(0);
            }
            catch(IOException v0) {
                Log.e("EcgFile", "Exception truncating file", ((Throwable)v0));
            }
        }
    }

    public void d(int arg1) {
        this.w = arg1;
    }

    public String e() {
        String v0 = this.p == null ? null : this.p.getName();
        return v0;
    }

    public int i() {
        return this.s;
    }

    public float j() {
        return this.v;
    }

    public boolean k() {
        boolean v0 = true;
        if(this.a == 3 && ((((byte)this.d.d)) & 32) == 0) {
            v0 = false;
        }

        return v0;
    }

    public boolean l() {
        boolean v0 = true;
        if(this.a == 3 && ((((byte)this.d.d)) & 16) == 0) {
            v0 = false;
        }

        return v0;
    }

    public int m() {
        int v0 = ((((byte)this.d.d)) & 2) != 0 ? 60 : 50;
        return v0;
    }

    private float p() {
        float v0_1;
        int v12 = 2;
        float v5 = 0f;
        float v11 = 18000f;
        if(this.f >= v12) {
            int v6 = 600;
            int v7 = 60;
            int[] v8 = new int[this.f - 1];
            int v0 = 1;
            int v2 = 0;
            while(v0 < this.f) {
                int v9 = this.e[v0] - this.e[v0 - 1];
                if(v9 >= v7 && v9 <= v6) {
                    v8[v2] = v9;
                    ++v2;
                }

                ++v0;
            }

            if(v2 == 1) {
                v0_1 = v11 / (((float)v8[0]));
            }
            else if(v2 == v12) {
                v0_1 = v11 / (((float)((v8[0] + v8[1]) / 2)));
            }
            else if(v2 > 0) {
                v0_1 = v11 / this.a(v8, v2, 40);
            }
            else {
                v0_1 = 0f;
            }

            v5 = v0_1;
        }

        return v5;
    }
}
