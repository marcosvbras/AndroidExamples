package com.example.marcos.androidexamples.app.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.marcos.androidexamples.app.interfaces.ImageDownloadListener;

/**
 * Created by marcos on 01/01/2017.
 */

public class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {

    private Bitmap bitmap;
    private ProgressBar progressBar;
    private String urlImage;
    private ImageDownloadListener imageDownloadListener;

    public DownloadImageTask(ProgressBar progressBar, String urlImage, ImageDownloadListener imageDownloadListener) {
        this.progressBar = progressBar;
        this.urlImage = urlImage;
        this.imageDownloadListener = imageDownloadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Executa na thread principal, ideal para exibir alguma forma de carregamento como um ProgressDialog ou ProgressBar
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        // Executa em segundo plano. O retorno é passado ao método onPostExecute()
        bitmap = ImageHelper.downloadBitmap(urlImage);
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // Responsável pelo progresso da execução. O valor do progresso deve ser enviado via método publicProgress(int) durante o doInBackground()
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        // Recebe o resultado do método doInBackground(). Este método executa na UI Thread e pode atualizar a view
        imageDownloadListener.onDownloadFinish(bitmap);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        progressBar.setVisibility(View.GONE);
    }
}
