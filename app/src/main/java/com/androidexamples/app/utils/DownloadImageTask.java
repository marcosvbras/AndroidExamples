package com.androidexamples.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.androidexamples.app.interfaces.ImageDownloadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 01/01/2017.
 */

public class DownloadImageTask extends AsyncTask<String, Integer, List<Bitmap>> {

    /*
    * Primeiro argumento: Params. É passado como parâmetro no método execute() o qual chama o método doInBackground()
    * Segundo argumento: Progress. É passado como parâmetro no método onProgressUpdate()
    * Terceiro argumento: Result. É passado como parâmetro no método onPostExecute(). É o tipo de dado
    * que deseja retornar para a Activity.
    */

    private List<Bitmap> listBitmap;
    private ProgressBar progressBar;
    private ImageDownloadListener imageDownloadListener;
    private Context context;

    public DownloadImageTask(ProgressBar progressBar, ImageDownloadListener imageDownloadListener, Context context) {
        this.progressBar = progressBar;
        this.imageDownloadListener = imageDownloadListener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Primeiro método a ser chamado
        // Executa na thread principal, ideal para exibir alguma forma de carregamento como um ProgressDialog ou ProgressBar
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Bitmap> doInBackground(String... parameters) {
        // Executa em segundo plano. O retorno é passado ao método onPostExecute()
        listBitmap = new ArrayList<>();
        int progress;

        for(int i = 0; i < parameters.length; i++) {
            listBitmap.add(ImageHelper.downloadBitmap(parameters[i]));
            progress = (int)((i+1) / (float)parameters.length * 100);
            publishProgress(progress);

            if(isCancelled())
                break;
        }

        return listBitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        // Responsável pelo progresso da execução. O valor do progresso deve ser enviado via método publicProgress(int) durante o doInBackground()
        progressBar.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(List<Bitmap> listBitmap) {
        super.onPostExecute(listBitmap);
        // Recebe o resultado do método doInBackground(). Este método executa na UI Thread e pode atualizar a view
        imageDownloadListener.onDownloadFinish(listBitmap);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressBar.setVisibility(View.GONE);
    }
}
