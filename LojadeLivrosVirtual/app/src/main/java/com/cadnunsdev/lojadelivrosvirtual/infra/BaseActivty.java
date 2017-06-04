package com.cadnunsdev.lojadelivrosvirtual.infra;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Tiago Silva on 03/06/2017.
 */

public class BaseActivty extends AppCompatActivity {
    public  <T extends View> T findView(int view_id){
        return (T)findViewById(view_id);
    }
}
