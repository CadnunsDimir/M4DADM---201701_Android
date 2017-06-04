package com.cadnunsdev.lojadelivrosvirtual;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cadnunsdev.lojadelivrosvirtual.infra.BaseActivty;
import com.cadnunsdev.lojadelivrosvirtual.infra.CheckBoxGroup;

public class MainActivity extends BaseActivty {

    private Button btnOK;
    private CheckBox cbCsharp;
    private CheckBox cbJava;
    private CheckBox cbAndroid;
    private CheckBoxGroup group;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = findView(R.id.btnOK);
        cbCsharp = findView(R.id.ckboxCsharp);
        cbJava = findView(R.id.ckboxJava);
        cbAndroid = findView(R.id.ckboxAndroid);
        log = findView(R.id.log);

        group = CheckBoxGroup.init()
                .AddCheckBox(cbAndroid)
                .AddCheckBox(cbCsharp)
                .AddCheckBox(cbJava)
                .build();

//        group.setLog(log);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert =  new AlertDialog.Builder(MainActivity.this);
                if (group.getSelected() != null){
                    alert.setMessage(group.getSelected().getText());
                }else {
                    alert.setMessage("nada foi seleciodo");
                }

                alert.show();
            }
        });
    }
}
