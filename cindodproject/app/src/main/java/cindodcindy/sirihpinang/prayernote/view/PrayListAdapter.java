package cindodcindy.sirihpinang.prayernote.view;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.WriteDoa;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class PrayListAdapter extends RecyclerView.Adapter<PrayListAdapter.PrayAdapterChild> {

    private Context context;
    private List<PrayPojo> prayPojoList;

    private Animation animationUp, animationDown;

    private final int COUNTDOWN_RUNNING_TIME = 500;

    private LayoutInflater layoutInflater;


    public PrayListAdapter(Context context, Animation animationUp, Animation animationDown) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
    }

    public PrayListAdapter(Context context) {
        this.context = context;
        this.prayPojoList = new ArrayList<>();
    }

    public void setNotes(List<PrayPojo> prayPojoList) {
        this.prayPojoList = prayPojoList;
        notifyDataSetChanged();
    }






    @NonNull
    @Override
    public PrayAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_adapter, parent, false);
        return new PrayAdapterChild(v);


           }

    @Override
    public void onBindViewHolder(@NonNull final PrayAdapterChild holder, int position) {
        final PrayPojo prayPojo = prayPojoList.get(position);


        holder.textView_date.setText(prayPojo.getDate());
        holder.textView_pray.setText(prayPojo.getPray());
        holder.cardView_pray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WriteDoa.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("noteID", prayPojo.getPrayId());
                context.startActivity(intent);
            }
        });

        holder.txtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.startAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            holder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();


                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.startAnimation(animationDown);


                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return prayPojoList.size();
    }

    public class PrayAdapterChild extends RecyclerView.ViewHolder{

        public TextView textView_date, textView_pray;
        public ImageView imageView_edit, imageView_delete;
        public CardView cardView_pray;
        public View contentLayout;
        private TextView txtContent;
        private CardView cardView_answ_pr;



        public PrayAdapterChild(@NonNull View itemView) {
            super(itemView);
            textView_date=itemView.findViewById(R.id.tv_pray_date);
            textView_pray=itemView.findViewById(R.id.tv_pray);
            imageView_edit=itemView.findViewById(R.id.iv_edit_pray);
            imageView_delete=itemView.findViewById(R.id.iv_delete_pray);
            cardView_pray=itemView.findViewById(R.id.cv_pray_content);
            txtContent=itemView.findViewById(R.id.tv_prayer_answered_click);
            cardView_answ_pr=itemView.findViewById(R.id.cv_answ_pr);




        }
    }
}
