package com.cadnunsdev.lojadelivrosvirtual.infra;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tiago Silva on 03/06/2017.
 */




public class CheckBoxGroup {
    private final List<CheckBox> lista;

    private CompoundButton.OnCheckedChangeListener evt = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            setAll(false);
            buttonView.setChecked(isChecked);
        }
    };
    private TextView log;

    public static CheckBoxGroupBuilder init(){
        return new CheckBoxGroupBuilder();
    }

    private CheckBoxGroup(CheckBox checkBoxList[]){
        lista = Arrays.asList(checkBoxList);

        for (CheckBox cb :lista) {
            cb.setOnCheckedChangeListener(evt);
        }
    }

    public void setAll(boolean state){
        for (CheckBox cb :lista) {
            cb.setChecked(state);
        }
    }

    public CheckBox getSelected (){
        for (CheckBox cb :lista) {

            logInfo("info from checkbox: "+ cb.getText()+", state : "+cb.isChecked());

           if(cb.isChecked()){
               return cb;
           }
        }
        return null;
    }

    private void logInfo(String info) {
        if(log != null){
            log.append(info+"\n");
        }
    }

    public void setLog(TextView log) {
        this.log = log;
    }

    public static class CheckBoxGroupBuilder{


        private final ArrayList<CheckBox> listaTemp;


        private CheckBoxGroupBuilder(){
            listaTemp = new ArrayList<>();
        }

        public CheckBoxGroupBuilder AddCheckBox(CheckBox checkBox){
            listaTemp.add(checkBox);
            return this;
        }

        public CheckBoxGroup build(){
            return new CheckBoxGroup(listaTemp.toArray(new CheckBox[]{}));
        }
    }
}
