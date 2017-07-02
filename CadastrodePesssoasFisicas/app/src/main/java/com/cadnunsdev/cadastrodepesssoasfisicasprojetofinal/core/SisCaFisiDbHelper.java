//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Tiago Silva do Nascimento
//******************************************************
package com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.R;
import com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.core.entidades.PessoaFisica;

import java.util.ArrayList;

/**
 * Created by Tiago Silva on 26/06/2017.
 */
// OBJETIVO.......: classe responsável pelo acesso ao banco de dados
public class SisCaFisiDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "siscafisi_db.db";
    private static final int VERSION = 1;
    private final String initialSQL;
    private final String upgradeSQL;

    public SisCaFisiDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        initialSQL = context.getResources().getString(R.string.init_db);
        upgradeSQL = context.getResources().getString(R.string.updt_db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(initialSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(upgradeSQL);
        onCreate(db);
    }



    public static class PessoaFisicaRepository{
        private static final String TABLE_NAME = "CadastroPessoasFisicas" ;
        private static final String[] TABLE_COLUNAS = new String[]{"id", "nome", "cpf", "idade", "telefone", "email"};

        private static PessoaFisicaRepository _repositorio;
        private Context context;
        private SisCaFisiDbHelper dbHelper;
        private SQLiteDatabase db;
        private SQLiteStatement insertSQL;

        private PessoaFisicaRepository(){

        }
        public static PessoaFisicaRepository getInstance(Context context){
            if (_repositorio == null){
                _repositorio = new PessoaFisicaRepository();
                _repositorio.context = context;
                _repositorio.dbHelper = new SisCaFisiDbHelper(context);
                _repositorio.db = _repositorio.dbHelper.getWritableDatabase();
                _repositorio.insertSQL = _repositorio.db.compileStatement(context.getString(R.string.insert_pf));
            }
            return _repositorio;
        }
        public long insert(PessoaFisica pessoaFisica) {

            insertSQL.bindAllArgsAsStrings(
                    new String[]{
                            pessoaFisica.getNome(),
                            pessoaFisica.getCpf(),
                            String.valueOf(pessoaFisica.getIdade()),
                            pessoaFisica.getTelefone(),
                            pessoaFisica.getEmail()
                    }
            );
            pessoaFisica.setId((int) insertSQL.executeInsert());
            return pessoaFisica.getId();
        }

        public ArrayList<PessoaFisica> listAll() {
            Cursor itens =  db.query(
                    TABLE_NAME,
                    TABLE_COLUNAS,null,null,null,null,null,null);
            ArrayList<PessoaFisica> lista = new ArrayList<>();
            if(!itens.moveToFirst()){
                return lista;
            }
            do{
                try {
                    PessoaFisica pessoaFisica = new PessoaFisica();
                    pessoaFisica.setId(itens.getInt(0));
                    pessoaFisica.setNome(itens.getString(1));
                    pessoaFisica.setCpf(itens.getString(2));
                    pessoaFisica.setIdade(itens.getInt(3));
                    pessoaFisica.setTelefone(itens.getString(4));
                    pessoaFisica.setEmail(itens.getString(5));
                    lista.add(pessoaFisica);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }while (itens.moveToNext());
            return lista;
        }


    }

}
