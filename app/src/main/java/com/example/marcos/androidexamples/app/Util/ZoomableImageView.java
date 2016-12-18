package com.example.marcos.androidexamples.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by marcos on 11/12/2016.
 */

public class ZoomableImageView extends ImageView implements View.OnTouchListener {

    private Matrix matrix = new Matrix();

    // Constant modes
    public static final int NONE_MODE = 0;
    public static final int DRAG_MODE = 1;
    public static final int ZOOM_MODE = 2;
    public static final int CLICK_MODE = 3;
    private int mode = NONE_MODE;

    // Points
    private PointF lastPoint = new PointF();
    private PointF startPoint = new PointF();

    private float minScale = 1f;
    private float maxScale = 4f;
    private float[] matrixValues;

    private float redundantXSpace, redundantYSpace;
    private float width, height;
    private float saveScale = 1f;
    private float right;
    private float bottom;
    private float origWidth;
    private float origHeight;
    private float bitmapWidth;
    private float bitmapHeight;

    private ScaleGestureDetector scaleGestureDetector;
    private Context context;

    public ZoomableImageView(Context context, AttributeSet attr) {
        super(context, attr);
        super.setClickable(true);
        this.context = context;
        scaleGestureDetector = new ScaleGestureDetector(context, onScaleGesture());
        matrix.setTranslate(1f, 1f);
        matrixValues = new float[9];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(this);
    }

    private ScaleGestureDetector.OnScaleGestureListener onScaleGesture() {
        return new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float mScaleFactor = scaleGestureDetector.getScaleFactor();
                float origScale = saveScale;
                saveScale *= mScaleFactor;
                if (saveScale > maxScale) {
                    saveScale = maxScale;
                    mScaleFactor = maxScale / origScale;
                } else if (saveScale < minScale) {
                    saveScale = minScale;
                    mScaleFactor = minScale / origScale;
                }

                right = width * saveScale - width - (2 * redundantXSpace * saveScale);
                bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

                if (origWidth * saveScale <= width || origHeight * saveScale <= height) {
                    matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);
                    if (mScaleFactor < 1) {
                        matrix.getValues(matrixValues);
                        float x = matrixValues[Matrix.MTRANS_X];
                        float y = matrixValues[Matrix.MTRANS_Y];
                        if (mScaleFactor < 1) {
                            if (Math.round(origWidth * saveScale) < width) {
                                if (y < -bottom)
                                    matrix.postTranslate(0, -(y + bottom));
                                else if (y > 0)
                                    matrix.postTranslate(0, -y);
                            } else {
                                if (x < -right)
                                    matrix.postTranslate(-(x + right), 0);
                                else if (x > 0)
                                    matrix.postTranslate(-x, 0);
                            }
                        }
                    }
                }
                else {
                    matrix.postScale(mScaleFactor, mScaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    matrix.getValues(matrixValues);
                    float x = matrixValues[Matrix.MTRANS_X];
                    float y = matrixValues[Matrix.MTRANS_Y];
                    if (mScaleFactor < 1) {
                        if (x < -right)
                            matrix.postTranslate(-(x + right), 0);
                        else if (x > 0)
                            matrix.postTranslate(-x, 0);
                        if (y < -bottom)
                            matrix.postTranslate(0, -(y + bottom));
                        else if (y > 0)
                            matrix.postTranslate(0, -y);
                    }
                }
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                mode = ZOOM_MODE;
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

            }
        };
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        // Getting bitmap metrics
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
    }

    public void setMaxZoom(float x) {
        maxScale = x;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);

        matrix.getValues(matrixValues);
        float x = matrixValues[Matrix.MTRANS_X];
        float y = matrixValues[Matrix.MTRANS_Y];
        PointF currentPoint = new PointF(motionEvent.getX(), motionEvent.getY());

        int actionEvent = motionEvent.getAction();

        if(actionEvent == MotionEvent.ACTION_DOWN) {
            // When one finger is touchin, set the mode to DRAG_MODE
            lastPoint.set(motionEvent.getX(), motionEvent.getY());
            startPoint.set(lastPoint);
            mode = DRAG_MODE;
        } else if(actionEvent == MotionEvent.ACTION_POINTER_DOWN) {
            // When two fingers are touchin, set the mode to ZOOM_MODE
            lastPoint.set(motionEvent.getX(), motionEvent.getY());
            startPoint.set(lastPoint);
            mode = ZOOM_MODE;
        } else if(actionEvent == MotionEvent.ACTION_MOVE) {
            // if the mode is ZOOM_MODE or if the mode is DRAG_MODE and already zoomed
            if (mode == ZOOM_MODE || (mode == DRAG_MODE && saveScale > minScale)) {
                float deltaX = currentPoint.x - lastPoint.x;// x difference
                float deltaY = currentPoint.y - lastPoint.y;// y difference
                float scaleWidth = Math.round(origWidth * saveScale);// width after applying current scale
                float scaleHeight = Math.round(origHeight * saveScale);// height after applying current scale
                //if scaleWidth is smaller than the views width
                //in other words if the image width fits in the view
                //limit left and right movement
                if (scaleWidth < width) {
                    deltaX = 0;

                    if (y + deltaY > 0)
                        deltaY = -y;
                    else if (y + deltaY < -bottom)
                        deltaY = -(y + bottom);
                }
                //if scaleHeight is smaller than the views height
                //in other words if the image height fits in the view
                //limit up and down movement
                else if (scaleHeight < height) {
                    deltaY = 0;
                    if (x + deltaX > 0)
                        deltaX = -x;
                    else if (x + deltaX < -right)
                        deltaX = -(x + right);
                }
                //if the image doesnt fit in the width or height
                //limit both up and down and left and right
                else {
                    if (x + deltaX > 0)
                        deltaX = -x;
                    else if (x + deltaX < -right)
                        deltaX = -(x + right);

                    if (y + deltaY > 0)
                        deltaY = -y;
                    else if (y + deltaY < -bottom)
                        deltaY = -(y + bottom);
                }
                // Move the image with the matrix
                matrix.postTranslate(deltaX, deltaY);
                // Set the lastPoint touch location to the current
                lastPoint.set(currentPoint.x, currentPoint.y);
            }
        } else if(actionEvent == MotionEvent.ACTION_UP) {
            // first finger is lifted
            mode = NONE_MODE;
            int xDiff = (int) Math.abs(currentPoint.x - startPoint.x);
            int yDiff = (int) Math.abs(currentPoint.y - startPoint.y);
            if (xDiff < CLICK_MODE && yDiff < CLICK_MODE)
                performClick();
        } else if(actionEvent == MotionEvent.ACTION_POINTER_UP) {
            // second finger is lifted
            mode = NONE_MODE;
        }

        setImageMatrix(matrix);
        invalidate();
        return true;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //Fit to screen.
        float scale;
        float scaleX =  width / bitmapWidth;
        float scaleY = height / bitmapHeight;
        scale = Math.min(scaleX, scaleY);
        matrix.setScale(scale, scale);
        setImageMatrix(matrix);
        saveScale = 1f;

        // Center the image
        redundantYSpace = height - (scale * bitmapHeight) ;
        redundantXSpace = width - (scale * bitmapWidth);
        redundantYSpace /= 2;
        redundantXSpace /= 2;

        matrix.postTranslate(redundantXSpace, redundantYSpace);

        origWidth = width - 2 * redundantXSpace;
        origHeight = height - 2 * redundantYSpace;
        right = width * saveScale - width - (2 * redundantXSpace * saveScale);
        bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
        setImageMatrix(matrix);
    }
}
