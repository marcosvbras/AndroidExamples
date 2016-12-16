package com.example.marcos.androidexamples.app.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.example.marcos.androidexamples.R;

/**
 * Created by marco on 16/12/2016.
 */

public class TouchScreenView extends View {

    private static final String TAG = "got";
    private Drawable drawableImage;
    private int x, y;
    private boolean selected;
    private int screenWidth, screenHeight;
    private int imageWidth, imageHeight;

    public TouchScreenView(Context context) {
        super(context, null);

        drawableImage = context.getResources().getDrawable(R.drawable.stark);
        imageWidth = drawableImage.getIntrinsicWidth();
        imageHeight = drawableImage.getIntrinsicHeight();

        // Configura a view para receber foco e tratar eventos de teclado
        setFocusable(true);
    }

    @Override // Chamado quando a tela é redimensionada ou iniciada
    protected void onSizeChanged(int currentWidth, int currentHeight, int oldWidth, int oldHeight) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight);
        this.screenWidth = currentWidth;
        this.screenHeight = currentHeight;
        x = currentWidth / 2 - (imageWidth / 2);
        y = currentHeight / 2 - (imageHeight / 2);
    }

    @Override // Desenha a tela
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Fundo Branco
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
        // Define os limites/área para desenhar
        drawableImage.setBounds(x, y, x + imageWidth, y + imageHeight);
        // Desenha a imagem
        drawableImage.draw(canvas);
    }

    @Override // Move a Imagem
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Inicia o movimento se pressinou a imagem
                selected = drawableImage.copyBounds().contains((int)x, (int)y);
                break;
            case MotionEvent.ACTION_MOVE:
                // Arrasta a imagem
                if(selected) {
                    this.x = (int)x - (imageWidth / 2);
                    this.y = (int)y - (imageHeight / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                // Finaliza o movimento
                selected = false;
                break;
        }
        // O invalidate vai chamar o método OnDraw(canvas) novamente
        invalidate();
        return true;
    }
}
