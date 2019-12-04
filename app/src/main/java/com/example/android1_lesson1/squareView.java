package com.example.android1_lesson1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class squareView extends View {

    public static final int DEF_COLOR = Color.BLUE;
    public static final int DEF_RECTANGLE_H = 50;
    public static final int DEF_RECTANGLE_W = 50;

    Paint paint;
    int rectangleH;
    int rectangleW;

    public squareView(Context context) {
        super(context);
        init(DEF_COLOR,DEF_RECTANGLE_H,DEF_RECTANGLE_W);
    }

    public squareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.squareView);
        int color = typedArray.getColor(R.styleable.squareView_cv_color, DEF_COLOR);
        int rectangleH = typedArray.getInt(R.styleable.squareView_cv_rectangleH, DEF_RECTANGLE_H);
        int rectangleW = typedArray.getInt(R.styleable.squareView_cv_rectangleW, DEF_RECTANGLE_W);
        init(color,rectangleH,rectangleW);
    }

    void init(int color, int rectangleH , int rectangleW){
        paint = new Paint();   // создаём чем будем рисовать
        paint.setColor(color);   // определяем цвет
        paint.setStyle(Paint.Style.FILL); // определяем стиль. У нас заливка.
        paint.setStrokeWidth(5);
        this.rectangleH = rectangleH;
        this.rectangleW = rectangleW;
    }

    public squareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(DEF_COLOR,DEF_RECTANGLE_H,DEF_RECTANGLE_W);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      //  canvas.drawCircle(50,50,50,paint);
        canvas.drawRect(200, 200, 0, 0, paint);
    }
}
