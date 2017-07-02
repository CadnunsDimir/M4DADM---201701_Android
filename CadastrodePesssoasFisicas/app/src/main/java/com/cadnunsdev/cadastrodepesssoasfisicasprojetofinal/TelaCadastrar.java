//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Tiago Silva do Nascimento
//******************************************************

package com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.core.SisCaFisiDbHelper;
import com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal.core.entidades.PessoaFisica;

import java.util.ArrayList;

// OBJETIVO.......: Tela responsável pelo registro e listagem dos itens cadastrados
public class TelaCadastrar extends AppCompatActivity {

    private static final int ERROR_CONVERT_TO_INT = -500;
    private Button btnVoltar;
    private View viewOpcoes;
    private View viewNovo;
    private View viewLista;
    private Button btnOpcoesNovo;
    private Button btnOpcoesListar;
    private EditText edtNome;
    private EditText edtCPF;
    private SisCaFisiDbHelper.PessoaFisicaRepository repositorio;
    private ArrayList<PessoaFisica> lista;
    private ArrayAdapter<PessoaFisica> adapter;
    private ListView lvPessoasFisicas;
    private EditText edtIdade;
    private EditText edtTelefone;
    private EditText edtEmail;
    private Button btnCadastrar;
    private boolean cadastro_valido = true;
    private Button btnNovo_Lista;
    private Button btnVoltar_Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnOpcoesNovo = (Button)findViewById(R.id.btnOpcoesNovo);
        btnOpcoesListar = (Button)findViewById(R.id.btnOpcoesListar);

        btnVoltar.setOnClickListener(btnVoltar_Click);
        btnOpcoesNovo.setOnClickListener(btnOpcoesNovo_Click);
        btnOpcoesListar.setOnClickListener(btnOpcoesListar_Click);

        viewOpcoes = findViewById(R.id.layout_opcoes);
        viewNovo = findViewById(R.id.layout_novo);
        viewLista = findViewById(R.id.layout_lista);

        viewNovo.setVisibility(View.GONE);
        viewLista.setVisibility(View.GONE);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(btnCadastrar_Click);

        repositorio = SisCaFisiDbHelper.PessoaFisicaRepository.getInstance(this);
        lista = repositorio.listAll();
        lvPessoasFisicas = (ListView) findViewById(R.id.lvPessoasFisicas);
        btnNovo_Lista = (Button) findViewById(R.id.btnNovo_Lista);
        btnVoltar_Lista = (Button) findViewById(R.id.btnVoltar_Lista);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        lvPessoasFisicas.setAdapter(adapter);
        btnNovo_Lista.setOnClickListener(btnOpcoesNovo_Click);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showOptions(){
        viewNovo.setVisibility(View.GONE);
        viewLista.setVisibility(View.GONE);
        viewOpcoes.setVisibility(View.VISIBLE);
        btnVoltar.setVisibility(View.VISIBLE);
    }

    private void showForm(){
        viewNovo.setVisibility(View.VISIBLE);
        viewLista.setVisibility(View.GONE);
        viewOpcoes.setVisibility(View.GONE);
        btnVoltar.setVisibility(View.VISIBLE);

        cadastro_valido = true;

        EditText[] edits = new EditText[]{edtNome, edtCPF,edtIdade,edtTelefone, edtEmail};

        for(EditText edt : edits){
            edt.setText("");
        }
    }

    private void showLista(){
        viewNovo.setVisibility(View.GONE);
        viewLista.setVisibility(View.VISIBLE);
        viewOpcoes.setVisibility(View.GONE);
        btnVoltar.setVisibility(View.GONE);

        lista.clear();
        lista.addAll(repositorio.listAll());

        adapter.notifyDataSetChanged();
    }

    private void cadastrarNovo() {
        PessoaFisica pessoa = new PessoaFisica();

        pessoa.setNome(get(edtNome));
        pessoa.setCpf(get(edtCPF));
        pessoa.setIdade(getInt(edtIdade));
        pessoa.setTelefone(get(edtTelefone));
        pessoa.setEmail(get(edtEmail));

        if (cadastro_valido){
            repositorio.insert(pessoa);
            showLista();
            Snackbar.make(viewNovo,"Usuário Salvo",5000).show();
        }
    }

    private int getInt(EditText editText) {
        try {
            return Integer.parseInt(get(editText));
        }catch (Exception ex){
            return ERROR_CONVERT_TO_INT;
        }

    }

    private String get(EditText editText) {
        return editText.getText().toString();
    }

    private View.OnClickListener btnVoltar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewOpcoes.getVisibility() != View.VISIBLE)
                showOptions();
            else
                startActivity(new Intent(TelaCadastrar.this, TelaInicial.class));
        }
    };

    private View.OnClickListener btnOpcoesNovo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showForm();
        }
    };
    private View.OnClickListener btnOpcoesListar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showLista();
        }
    };

    private View.OnClickListener btnCadastrar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cadastrarNovo();
        }
    };

}
