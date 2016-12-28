package com.example.marcos.androidexamples.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.marcos.androidexamples.app.util.Converter;

/**
 * Created by marcos on 17/12/2016.
 */

public class MyView extends View {

    private Paint redPaint;
    private Paint blackPaint;
    private Paint bluePaint;

    /*
    * Construtor chamado quando a view via API
    * */
    public MyView(Context context) {
        super(context, null);
    }

    /*
    * Contrutor chamado quando a view é criada via XML
    * O parâmetro do tipo AttributeSet guarda os atributos da view definidos no arquivo XML
    * */
    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(Color.LTGRAY);

        redPaint = new Paint();
        redPaint.setARGB(255, 255, 0, 0);

        blackPaint = new Paint();
        blackPaint.setARGB(255, 0, 0, 0);

        bluePaint = new Paint();
        bluePaint.setARGB(255, 0, 0, 255);

        // Configura a view para receber foco e tratar eventos de teclado
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Desenha um quadrado
        canvas.drawRect(Converter.toPixels(getContext(), 20), Converter.toPixels(getContext(), 20),
                Converter.toPixels(getContext(), 200), Converter.toPixels(getContext(), 200), bluePaint);
        // Desenha uma linha
        canvas.drawLine(Converter.toPixels(getContext(), 200), Converter.toPixels(getContext(), 200),
                Converter.toPixels(getContext(), 400), Converter.toPixels(getContext(), 400), blackPaint);
        // Desenha uma círculo
        canvas.drawCircle(Converter.toPixels(getContext(), 400), Converter.toPixels(getContext(), 400),
                Converter.toPixels(getContext(), 100), redPaint);
    }
}
