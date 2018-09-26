package com.test.newproject.ecgmonitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/25
 *     desc   :
 *     modify :
 * </pre>
 */

public class EcgRealTimeView extends View {
    i a;
    private final int b;
    private Paint linePaint;
    private Paint dividePaint;
    private s e;
    private boolean f;
    private boolean g;
    private boolean h;
    private c i;
    private b j;
    private Path path;
    private int l;
    private float m;
    private float n;
    private float o;
    private int p;
    private int q;
    private DisplayMetrics r;
    private float s;
    private float t;

    public EcgRealTimeView(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.b = 4;
        this.linePaint = new Paint();
        this.dividePaint = new Paint();
        this.f = false;
        this.g = true;
        this.h = false;
        this.e = new t();
        this.linePaint.setColor(Color.BLACK);
        this.linePaint.setStrokeWidth(2.5f);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.linePaint.setStrokeJoin(Paint.Join.ROUND);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.dividePaint.setColor(Color.BLACK);
        this.dividePaint.setStrokeWidth(3f);
        this.dividePaint.setAntiAlias(true);
        this.dividePaint.setStrokeCap(Paint.Cap.ROUND);
        this.dividePaint.setStrokeJoin(Paint.Join.ROUND);
        this.dividePaint.setStyle(Paint.Style.STROKE);
        this.setLayerType(1, null);
        this.r = new DisplayMetrics();
    }

    public void a() {
        this.e.b();
        if(this.h) {
            this.i.a();
            this.j.a();
        }

        this.invalidate();
    }

    public void a(int arg3) {
        if(this.j != null && this.i != null) {
            this.j.b(this.i.b() - arg3);
            this.invalidate();
        }
    }

    public void a(short[] arg9, int arg10, int arg11) {
        double v2 = 32767;
        double v4 = -32768;
        if(!this.h) {
            int v0 = this.l + 12;
            this.path = new Path();
            this.path.incReserve(v0);
            this.i = new c(v0);
            this.j = new b(v0 * 300 / 18000);
            this.h = true;
        }

        if(this.f) {
            if(this.g) {
                this.i.a(arg9, arg10, arg11);
            }

            while(arg10 < arg11) {
                double v0_1 = this.e.a((double)arg9[arg10]);
                if(v0_1 < v4) {
                    v0_1 = v4;
                }

                if(v0_1 > v2) {
                    v0_1 = v2;
                }

                this.i.a((short)((int)v0_1));
                ++arg10;
            }
        }
        else {
            this.i.a(arg9, arg10, arg11);
        }

        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas arg12) {
        int v1;
        super.onDraw(arg12);
        if(this.h) {
            int v8 = 48;
            this.path.rewind();
            int v0 = this.i.b() - 1;
            if(v0 >= this.l - v8) {
                v1 = v0 - (this.l - v8);
                v0 = this.l - v8 + v1;
            }
            else {
                v1 = 0;
            }

            float v2 = 0f;
            int v3 = v1;
            int v5 = 0;
            while(v3 < v0) {
                float v4 = (((float)v3)) * this.n % (((float)this.p));
                float v7 = this.m - (((float)this.i.b(v3))) * this.o;
                if(v3 == v1 || v4 < v2) {
                    this.path.moveTo(v4, v7);
                }
                else {
                    this.path.lineTo(v4, v7);
                }

                int v2_1 = v5 + 1;
                if(v2_1 == 200) {
                    arg12.drawPath(this.path, this.linePaint);
                    this.path.rewind();
                    this.path.moveTo(v4, v7);
                    v2_1 = 0;
                }

                ++v3;
                v5 = v2_1;
                v2 = v4;
            }

            if(v5 > 1) {
                arg12.drawPath(this.path, this.linePaint);
            }

            int v7_1 = 0;
            for(v0 = this.j.b(); v0 > 0; v0 = v7_1) {
                v7_1 = v0 - 1;
                v1 = this.j.c(v7_1);
                if(v1 < this.i.b() - this.l - v8) {
                    return;
                }

                if(v1 < this.i.b()) {
                    v0 = !this.f || (this.g) ? 0 : this.e.c();
                    v0 = (((int)((((float)(v0 + v1 - 1))) * this.n))) % this.p;
                    arg12.drawLine(((float)v0), ((float)this.q), ((float)v0), ((float)(this.q - 15)), this.dividePaint);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int arg5, int arg6, int arg7, int arg8) {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(this.r);
        this.s = this.r.xdpi / 2.54f * 1f;
        this.t = this.r.ydpi / 2.54f * 1f;
        this.t = this.s;
        this.n = 2.5f * this.s / 300f;
        this.o = this.t / 2000f;
        this.m = (((float)arg6)) * 0.5f;
        this.p = arg5;
        this.q = arg6;
        this.l = ((int)((((float)arg5)) / this.n));
        if(this.h) {
            int v0 = this.l + 12;
            this.i.a(v0);
            this.j.a(v0 * 300 / 18000);
        }

        super.onSizeChanged(arg5, arg6, arg7, arg8);
    }

    @Override
    public void onWindowFocusChanged(boolean arg2) {
        super.onWindowFocusChanged(arg2);
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setIsEnhancedFilter(boolean arg1) {
        this.g = arg1;
    }

    public void setIsMuscleFilter(boolean arg1) {
        this.f = arg1;
    }

    public void setMainsFrequency(int arg2) {
        this.e.setMainsFrequency(arg2);
    }

    public void setOnWinFocusChangeListener(i arg1) {
        this.a = arg1;
    }
}
