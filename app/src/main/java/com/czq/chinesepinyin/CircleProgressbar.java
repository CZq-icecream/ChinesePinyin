package com.czq.chinesepinyin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * 倒计时控件
 * 思想：定时刷新视图
 * @date 2019.2.15
 * @author czq
 */
public class CircleProgressbar extends View {

    private static final String TAG = "CircleProgressbar";

    private static final String TEXT = "跳过";    //控件中间的文字
    private static final int DEFAULT_DURATION = 3000;  //倒计时长

    private int circleSolidColor;   //实心圆的颜色
    private int ringColor;  //圆环颜色
    private int ringWidth;  //圆环宽度
    private int radius; //圆的半径
    private int textColor;  //字体颜色
    private int textWidth;  //字体宽度

    private Rect rect;  //整个view的外框
    private RectF rectF; //圆环周围的外框
    private Paint circleSolidPaint; //绘制实心圆
    private Paint ringPaint;  //绘制圆环
    private Paint textPaint;    //绘制文字

    private int duration;  //倒计时
    private int progress = 0;   //进度

    //新增回调方法，用于组件使用者在倒计时完成后进行某些操作
    private OnProgressListener onProgressListener;
    public interface OnProgressListener{
        void onProgress();
    }

    public CircleProgressbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = null;
        try{
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressbar);

            circleSolidColor = typedArray.getColor(R.styleable.CircleProgressbar_circleSolidColor, getResources().getColor(R.color.colorSolidRing));
            ringColor = typedArray.getColor(R.styleable.CircleProgressbar_ringColor, getResources().getColor(R.color.colorRing));
            textColor = typedArray.getColor(R.styleable.CircleProgressbar_textColor, getResources().getColor(R.color.colorRingText));
            duration = typedArray.getInt(R.styleable.CircleProgressbar_duration, DEFAULT_DURATION);
        }finally {
            typedArray.recycle();
        }

        circleSolidPaint = new Paint();
        ringPaint = new Paint();
        textPaint = new Paint();
        rect = new Rect();
        rectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;   //半径设置为较小的那个
        ringWidth = (int)((double)radius * 0.1);    //圆环的宽度设置为半径的1/10
        textWidth = ringWidth;  //设置字体宽度和圆环宽度相等

        //某些情况下会在xml中写wrap_content或者match_parent
        //所以我们需要自己去设置尺寸
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Return the visible drawing bounds of your view.
        getDrawingRect(rect);

        //绘制实心圆
        circleSolidPaint.setAntiAlias(true);    //设置抗锯齿
        circleSolidPaint.setStyle(Paint.Style.FILL);    //设置实心填充
        circleSolidPaint.setColor(circleSolidColor);    //设置颜色
        canvas.drawCircle(rect.centerX(), rect.centerY(), radius - ringWidth, circleSolidPaint);

        //绘制文字
        textPaint.setAntiAlias(true);   //设置抗锯齿
        textPaint.setTextAlign(Paint.Align.CENTER); //设置在中心绘制
        textPaint.setColor(textColor);  //设置颜色
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(textWidth);    //设置宽度
        canvas.drawText(TEXT, rect.centerX(), rect.centerY() - (textPaint.descent() + textPaint.ascent()) / 2, textPaint);

        //绘制进度条
        ringPaint.setColor(ringColor);  //设置颜色
        ringPaint.setStyle(Paint.Style.STROKE); //设置画笔样式
        ringPaint.setStrokeWidth(ringWidth);    //设置画笔宽度
        rectF.set(rect.centerX() - radius + ringWidth, rect.centerY() - radius + ringWidth,
                rect.centerX() + radius - ringWidth, rect.centerY() + radius - ringWidth);
        canvas.drawArc(rectF, 0, 360 * progress / 100, false, ringPaint);
    }

    /**
     * 倒计时开始
     */
    public void start(){
        //public boolean post (Runnable action):Causes the Runnable to be added to the message queue.
        //The runnable will be run on the user interface thread.
        post(progressChangeTask);
    }

    /**
     * 倒计时结束
     */
    public void stop(){
        //Removes the specified Runnable from the message queue.
        removeCallbacks(progressChangeTask);
    }

    //进度条更新线程
    private Runnable progressChangeTask = new Runnable() {
        @Override
        public void run() {
            progress += 1;
            if (progress >= 0 && progress <= 100) {
                invalidate();
                postDelayed(progressChangeTask, duration / 60);
            }else{
                if (onProgressListener != null) {
                    onProgressListener.onProgress();
                }else{
                    progress = 100;
                }
            }
        }
    };
}
