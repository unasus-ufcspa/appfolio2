package com.ufcspa.unasus.appportfolio.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Criado pelo tutorial: http://diegorubin.com/2012/02/19/desenvolvimento-para-android-carregando-um-banco-criado-externamente
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    /**
     * Este é o endereço onde o android salva os bancos de dados criado pela aplicação,
     * /data/data/<namespace da aplicacao>/databases/
     */
    private static String DBPATH = "/data/data/com.ufcspa.unasus.appportfolio.DataBase/databases/";

    // Este é o nome do banco de dados que iremos utilizar
    private static String DBNAME ="db_portfolio.sqlite";

    private Context context;

    /**
     * O construtor necessita do contexto da aplicação
     */
    public DatabaseHelper(Context context) {
    /* O primeiro argumento é o contexto da aplicacao
     * O segundo argumento é o nome do banco de dados
     * O terceiro é um pondeiro para manipulação de dados, não precisaremos dele.
     * O quarto é a versão do banco de dados
     */
        super(context, DBNAME, null, 1);
        this.context = context;
    }

    /**
     * Os métodos onCreate e onUpgrade precisam ser sobreescrito
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    /*
     * Estamos utilizando o banco do assets, por isso o
     * código antigo deste método não é mais necessário.
     */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    /*
     * Estamos criando a primeira versão do nosso banco de dados,
     * então não precisamos fazer nenhuma alteração neste método.
     *
     */
    }

    /**
     * Método auxiliar que verifica a existencia do banco
     * da aplicação.
     */
    private boolean checkDataBase() {

        SQLiteDatabase db = null;

        try
        {
            String path = DBPATH + DBNAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        }
        catch (SQLiteException e)
        {
            System.out.println("O banco não existe, mas será criado! Não entre em pânico.");
        }

        // Retorna verdadeiro se o banco existir, pois o ponteiro irá existir,
        // se não houver referencia é porque o banco não existe
        return db != null;
    }

    private void createDataBase() throws Exception {
        // Primeiro temos que verificar se o banco da aplicação
        // já foi criado
        boolean exists = checkDataBase();

        if(!exists)
        {
            // Chamaremos esse método para que o android
            // crie um banco vazio e o diretório onde iremos copiar
            // no banco que está no assets.
            this.getReadableDatabase();

            // Se o banco de dados não existir iremos copiar o nosso
            // arquivo em /assets para o local onde o android os salva
            try
            {
                copyDatabase();
            }
            catch (IOException e)
            {
                throw new Error("Não foi possível copiar o arquivo");
            }

        }
    }

    /**
     * Esse método é responsável por copiar o banco do diretório
     * assets para o diretório padrão do android.
     */
    private void copyDatabase() throws IOException
    {
        String dbPath = context.getDatabasePath(DBNAME).getPath() ;//DBPATH + DBNAME;

        // Abre o arquivo o destino para copiar o banco de dados
        OutputStream dbStream = new FileOutputStream(dbPath);

        // Abre Stream do nosso arquivo que esta no assets
        InputStream dbInputStream = context.getAssets().open(DBNAME);

        byte[] buffer = new byte[1024];
        int length;

        while((length = dbInputStream.read(buffer)) > 0)
        {
            dbStream.write(buffer, 0, length);
        }

        dbInputStream.close();

        dbStream.flush();
        dbStream.close();

    }

    public SQLiteDatabase getDatabase() {

        try
        {
            // Verificando se o banco já foi criado e se não foi o
            // mesmo é criado.
            createDataBase();

            // Abrindo database
            String path = DBPATH + DBNAME;

            return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch (Exception e)
        {
            // Se não conseguir copiar o banco um novo será retornado
            return getWritableDatabase();
        }

    }
}
