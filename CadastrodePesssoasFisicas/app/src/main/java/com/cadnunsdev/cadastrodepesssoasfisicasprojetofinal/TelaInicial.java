//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Tiago Silva do Nascimento
//******************************************************
package com.cadnunsdev.cadastrodepesssoasfisicasprojetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// OBJETIVO.......: Mostrar tela inicial
public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, TelaCadastrar.class));
    }
}
