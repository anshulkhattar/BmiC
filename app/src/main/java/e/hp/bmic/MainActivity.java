package e.hp.bmic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    private EditText ht,wt;
    private TextView bmires,htunit,wtunit;
    private Button BMIcalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ht=(EditText)findViewById(R.id.height);
        wt=(EditText)findViewById(R.id.weight);
        bmires=(TextView)findViewById(R.id.bmi);
        BMIcalc=(Button)findViewById(R.id.calculateBMI);
        htunit=(TextView)findViewById(R.id.htUnit);
        wtunit=(TextView)findViewById(R.id.wtUnit);


        BMIcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalcBMI();
                ht.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });

        htunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(htunit.getText()=="cm"){htunit.setText("m");}
                else {htunit.setText("cm");}
            }
        });
        wtunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wtunit.getText()=="kg"){wtunit.setText("g");}
                else {wtunit.setText("kg");}
            }
        });
    }

    private void CalcBMI()
    {
        String res="";
        String htSTR= ht.getText().toString();
        String wtSTR= wt.getText().toString();
        float height=0,weight=0,bmi;

        if(!htSTR.isEmpty()  && !wtSTR.isEmpty() )
        {
            height= Float.parseFloat(htSTR)/100;
            weight= Float.parseFloat(wtSTR);

            if(htunit.getText()=="m" && wtunit.getText()=="kg")
            {
                height= Float.parseFloat(htSTR);
                weight= Float.parseFloat(wtSTR);
            }
            else if(htunit.getText()=="cm" && wtunit.getText()=="kg")
            {
                height= Float.parseFloat(htSTR)/100;
                weight= Float.parseFloat(wtSTR);
            }
            else if(htunit.getText()=="m" && wtunit.getText()=="g")
            {
                height= Float.parseFloat(htSTR);
                weight= Float.parseFloat(wtSTR)/1000;
            }
            else if(htunit.getText()=="cm" && wtunit.getText()=="g")
            {
                height= Float.parseFloat(htSTR)/100;
                weight= Float.parseFloat(wtSTR)/1000;
            }

            bmi= weight/(height*height);


            if (bmi<18.5f) {
                res = "Underweight";
            } else if (bmi>=18.5f && bmi<=24.9f) {
                res="Normal Weight";
            } else if (bmi>=25 && bmi<=29.9f) {
                res="overweight";
            } else if (bmi>=30f && bmi<=34.9f) {
                res="obese_class_i";
            } else if (bmi>=35f && bmi<=39.9f) {
                res="obese_class_ii";
            } else if (bmi>=40f){
                res="obese_class_iii";
            }
            res=bmi+"\n"+res;
            bmires.setText(res);
        }
        else
            bmires.setText("Invalid Input");
    }
}
