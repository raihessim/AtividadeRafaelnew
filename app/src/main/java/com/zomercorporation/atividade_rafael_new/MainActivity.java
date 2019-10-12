package com.zomercorporation.atividade_rafael_new;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView id;
    private TextView name;
    private TextView year;
    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa();

        id = (TextView)findViewById(R.id.id);
        name = (TextView)findViewById(R.id.name);
        year = (TextView)findViewById(R.id.year);

        //Chama Async Task
        download.execute();
    }
    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            LigaJSONeObjeto util = new LigaJSONeObjeto();
            return util.getInformacao("https://reqres.in/api/user/1");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa){
            id.setText(pessoa.getId());
            name.setText(pessoa.getName());
            year.setText(pessoa.getYear());
            load.dismiss(); //deleta o dialog
        }
    }

}
