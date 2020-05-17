package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import cindodcindy.sirihpinang.prayernote.R;

public class ExpandLayout extends AppCompatActivity {

    private TextView txtContent, textView_date, textView_answ_pr;
    private ImageView imageView_edit_answ, imageView_del_answ;
    private Animation animationUp;
    private Animation animationDown;
    private CardView cardView_answ_pr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_layout);

        txtContent=findViewById(R.id.tv_prayer_answered_click);
        textView_date=findViewById(R.id.tv_date_ans);
        textView_answ_pr=findViewById(R.id.tv_answ_pr);
        imageView_edit_answ=findViewById(R.id.iv_edit_answ);
        imageView_del_answ=findViewById(R.id.iv_delete_answ);
        cardView_answ_pr=findViewById(R.id.cv_answ_pr);

        cardView_answ_pr.setVisibility(View.GONE);

        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cardView_answ_pr.isShown()){
                    cardView_answ_pr.setVisibility(View.GONE);
                    cardView_answ_pr.startAnimation(animationUp);
                }
                else{
                    cardView_answ_pr.setVisibility(View.VISIBLE);
                    cardView_answ_pr.startAnimation(animationDown);
                }

            }
        });

    }
}
