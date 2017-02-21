package com.androidexamples.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by marcosvbras on 19/02/2017.
 */

public class CircularImageView extends ImageView {

    private int borderWidth;
    private Paint paintBorder;

    public CircularImageView(Context context) {
        super(context);

        if (this.isInEditMode())
            return;
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (this.isInEditMode())
            return;
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.requestLayout();
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    public void addShadow() {
        setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = drawableToBitmap(getDrawable());

        if(bitmap == null)
            return;

        int width = getWidth();
        int height = getHeight();

        Bitmap roundBitmap = getCroppedBitmap(bitmap, width);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    public static Bitmap getCroppedBitmap(Bitmap originalBitmap, int radius) {
        Bitmap sbmp;

        if (originalBitmap.getWidth() != radius || originalBitmap.getHeight() != radius) {
            float smallest = Math.min(originalBitmap.getWidth(), originalBitmap.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(originalBitmap, (int)(originalBitmap.getWidth() / factor), (int)(originalBitmap.getHeight() / factor), false);
        } else {
            sbmp = originalBitmap;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        Rect rectSrc = new Rect(sbmp.getWidth()/2 - radius/2, sbmp.getHeight()/2 - radius/2,
                sbmp.getWidth()/2 + radius/2, sbmp.getHeight()/2 + radius/2
        );

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rectSrc, rect, paint);

        return output;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null)
            return null;
        else if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
