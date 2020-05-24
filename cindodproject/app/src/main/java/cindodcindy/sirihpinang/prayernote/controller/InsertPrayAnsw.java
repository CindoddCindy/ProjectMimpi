package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.view.AnswPrayList;

public class InsertPrayAnsw extends AppCompatActivity {

    public Calendar calendar;

    private DataPrayAnsw dataPrayAnsw;

    private TextView tv_tgl_1, tv_tgl_2;
    private EditText er_isi_1, et_isi_2;
    private Button btn_batal, btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pray_answ);
        tv_tgl_1=findViewById(R.id.Answ_tv_1_insert);
        tv_tgl_2=findViewById(R.id.Answ_tv_2_insert);
        er_isi_1=findViewById(R.id.Answ_et_1_insert);
        et_isi_2=findViewById(R.id.Answ_et_2_insert);
        btn_batal=findViewById(R.id.Answ_btn_batal_answ_insert);
        btn_save=findViewById(R.id.Answ_btn_save_answ_insert);


        calendar = Calendar.getInstance();

        dataPrayAnsw = new DataPrayAnsw(this);


        tv_tgl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InsertPrayAnsw.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        tv_tgl_1.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();




            }
        });

        tv_tgl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InsertPrayAnsw.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        tv_tgl_2.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();




            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_1=tv_tgl_1.getText().toString();
                String e_1=er_isi_1.getText().toString();
                String t_2=tv_tgl_2.getText().toString();
                String e_2=et_isi_2.getText().toString();
                if(TextUtils.isEmpty(t_1)){
                    tv_tgl_1.setError("tanggal doa kosong");
                }else if(TextUtils.isEmpty(e_1)){
                    er_isi_1.setError("Doa Kosong");
                }else if(TextUtils.isEmpty(t_2)){
                    tv_tgl_2.setError("Tanggal Doa Dijawab Kosong");
                }else if(TextUtils.isEmpty(e_2)){
                    et_isi_2.setError("Doa Dijawab Kosong");
                }else {
                    dataPrayAnsw.insertDataAnsw(tv_tgl_1.getText().toString(), tv_tgl_2.getText().toString(), er_isi_1.getText().toString(), et_isi_2.getText().toString());
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InsertPrayAnsw.this, AnswPrayList.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertPrayAnsw.this, AnswPrayList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
