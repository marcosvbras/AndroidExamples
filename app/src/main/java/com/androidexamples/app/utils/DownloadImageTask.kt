package com.androidexamples.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar

import com.androidexamples.app.interfaces.ImageDownloadListener

import java.util.ArrayList

/**
 * Created by marcos on 01/01/2017.
 */

class DownloadImageTask(private val progressBar: ProgressBar, private val imageDownloadListener: ImageDownloadListener, private val context: Context) : AsyncTask<String, Int, List<Bitmap>>() {

    /*
    * Primeiro argumento: Params. É passado como parâmetro no método execute() o qual chama o método doInBackground()
    * Segundo argumento: Progress. É passado como parâmetro no método onProgressUpdate()
    * Terceiro argumento: Result. É passado como parâmetro no método onPostExecute(). É o tipo de dado
    * que deseja retornar para a Activity.
    */

    private var listBitmap: MutableList<Bitmap>? = null

    override fun onPreExecute() {
        super.onPreExecute()
        // Primeiro método a ser chamado
        // Executa na thread principal, ideal para exibir alguma forma de carregamento como um ProgressDialog ou ProgressBar
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg parameters: String): List<Bitmap> {
        // Executa em segundo plano. O retorno é passado ao método onPostExecute()
        listBitmap = ArrayList()
        var progress: Int

        for (i in parameters.indices) {
            listBitmap!!.add(ImageHelper.downloadBitmap(parameters[i])!!)
            progress = ((i + 1) / parameters.size.toFloat() * 100).toInt()
            publishProgress(progress)

            if (isCancelled)
                break
        }

        return listBitmap as ArrayList<Bitmap>
    }

    override fun onProgressUpdate(vararg progress: Int?) {
        super.onProgressUpdate(*progress)
        // Responsável pelo progresso da execução. O valor do progresso deve ser enviado via método publicProgress(int) durante o doInBackground()
        progressBar.progress = progress[0]?: 0
    }

    override fun onPostExecute(listBitmap: List<Bitmap>) {
        super.onPostExecute(listBitmap)
        // Recebe o resultado do método doInBackground(). Este método executa na UI Thread e pode atualizar a view
        imageDownloadListener.onDownloadFinish(listBitmap)
        progressBar.visibility = View.GONE
    }

    override fun onCancelled() {
        super.onCancelled()
        progressBar.visibility = View.GONE
    }
}
