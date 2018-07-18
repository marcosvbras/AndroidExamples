package com.androidexamples.app.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import com.androidexamples.app.utils.Converter

/**
 * Created by marcos on 17/12/2016.
 */

class MyView : View {

    private var redPaint: Paint? = null
    private var blackPaint: Paint? = null
    private var bluePaint: Paint? = null

    /*
    * Construtor chamado quando a view via API
    * */
    constructor(context: Context) : super(context, null) {}

    /*
    * Contrutor chamado quando a view é criada via XML
    * O parâmetro do tipo AttributeSet guarda os atributos da view definidos no arquivo XML
    * */
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setBackgroundColor(Color.LTGRAY)

        redPaint = Paint()
        redPaint!!.setARGB(255, 255, 0, 0)

        blackPaint = Paint()
        blackPaint!!.setARGB(255, 0, 0, 0)

        bluePaint = Paint()
        bluePaint!!.setARGB(255, 0, 0, 255)

        // Configura a view para receber foco e tratar eventos de teclado
        isFocusable = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Desenha um quadrado
        canvas.drawRect(Converter.dipToPixels(context, 20f), Converter.dipToPixels(context, 20f),
                Converter.dipToPixels(context, 200f), Converter.dipToPixels(context, 200f), bluePaint)
        // Desenha uma linha
        canvas.drawLine(Converter.dipToPixels(context, 200f), Converter.dipToPixels(context, 200f),
                Converter.dipToPixels(context, 400f), Converter.dipToPixels(context, 400f), blackPaint)
        // Desenha uma círculo
        canvas.drawCircle(Converter.dipToPixels(context, 400f), Converter.dipToPixels(context, 400f),
                Converter.dipToPixels(context, 100f), redPaint)
    }
}
