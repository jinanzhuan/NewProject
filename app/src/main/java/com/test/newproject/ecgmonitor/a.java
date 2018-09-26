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

public class a {
    private byte[] a;
    private int b;
    private int c;

    private a(int arg2) {
        super();
        if(arg2 != this.b) {
            if(this.a != null) {
                this.a = null;
            }

            this.a = new byte[arg2];
            if(this.a == null) {
                return;
            }

            this.b = arg2;
        }
    }

    public static long a(byte[] arg2) {
        long v0 = arg2.length < 4 ? 0 : ((long)(arg2[0] & 255 | (arg2[1] & 255) << 8 | (arg2[2] & 255) << 16 | (arg2[3] & 255) << 24));
        return v0;
    }

    public static a a(int arg2) {
        a v0 = new a(arg2);
        if(v0.a() != arg2) {
            v0 = null;
        }

        return v0;
    }

    public void a(long arg8) {
        long v4 = 255;
        if(this.c + 4 <= this.b) {
            byte[] v0 = this.a;
            int v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(((int)(arg8 & v4))));
            v0 = this.a;
            v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(((int)(arg8 >> 8 & v4))));
            v0 = this.a;
            v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(((int)(arg8 >> 16 & v4))));
            v0 = this.a;
            v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(((int)(arg8 >> 24 & v4))));
        }
    }

    public void a(String arg7, int arg8) {
        int v4;
        byte[] v3;
        if(arg7 != null && this.c + arg7.length() <= this.b) {
            int v2 = arg7.length();
            int v0;
            for(v0 = 0; v0 < arg8; ++v0) {
                if(v0 < v2) {
                    v3 = this.a;
                    v4 = this.c;
                    this.c = v4 + 1;
                    v3[v4] = ((byte)arg7.charAt(v0));
                }
                else {
                    v3 = this.a;
                    v4 = this.c;
                    this.c = v4 + 1;
                    v3[v4] = 0;
                }
            }
        }
    }

    public int a() {
        return this.b;
    }

    public void b(byte[] arg5) {
        int v0 = arg5.length;
        if(this.c + v0 <= this.b) {
            System.arraycopy(arg5, 0, this.a, this.c, v0);
            this.c = v0 + this.c;
        }
    }

    public int b() {
        return this.c;
    }

    public void b(int arg4) {
        if(this.c + 1 <= this.b) {
            byte[] v0 = this.a;
            int v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(arg4 & 255));
        }
    }

    public void c(int arg4) {
        if(this.c + 2 <= this.b) {
            byte[] v0 = this.a;
            int v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(arg4 & 255));
            v0 = this.a;
            v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(arg4 >> 8 & 255));
        }
    }

    public boolean c() {
        boolean v0 = this.c < this.b ? true : false;
        return v0;
    }

    public void d() {
        this.c = 0;
    }

    public void d(int arg4) {
        if(this.c + 2 <= this.b) {
            byte[] v0 = this.a;
            int v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(arg4 & 255));
            v0 = this.a;
            v1 = this.c;
            this.c = v1 + 1;
            v0[v1] = ((byte)(arg4 >> 8 & 255));
        }
    }

    public long e() {
        long v0 = ((long)(this.a[this.c] & 255 | (this.a[this.c + 1] & 255) << 8 | (this.a[this.c + 2] & 255) << 16 | (this.a[this.c + 3] & 255) << 24));
        this.c += 4;
        return v0;
    }

    public String e(int arg4) {
        StringBuilder v1 = new StringBuilder(arg4);
        int v0;
        for(v0 = this.c; v0 < this.c + arg4; ++v0) {
            if(this.a[v0] == 0) {
                break;
            }

            v1.append(((char)this.a[v0]));
        }

        this.c += arg4;
        return v1.toString();
    }

    public long f(int arg4) {
        return ((long)(this.a[arg4] & 255 | (this.a[arg4 + 1] & 255) << 8 | (this.a[arg4 + 2] & 255) << 16 | (this.a[arg4 + 3] & 255) << 24));
    }

    public int f() {
        int v0 = this.a[this.c] & 255 | (this.a[this.c + 1] & 255) << 8;
        this.c += 2;
        return v0;
    }

    public int g() {
        byte[] v0 = this.a;
        int v1 = this.c;
        this.c = v1 + 1;
        return v0[v1] & 255;
    }

    public byte[] h() {
        return this.a;
    }
}
