package bala.satish.com.bunkmate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BottomSheet extends BottomSheetDialogFragment {
    private TextView tv1,tv2,tvSatish,tvNaveen;
    private ImageView iv1,iv2,ivSatish,ivNaveen;
    private View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
            v = inflater.inflate(R.layout.bottomsheet, container, false);
        }
        else {
            v = inflater.inflate(R.layout.lightbottomsheet, container, false);
        }
        iv1 = (ImageView) v.findViewById(R.id.iv1);
        iv2 = (ImageView) v.findViewById(R.id.iv2);
        tv1 = (TextView) v.findViewById(R.id.tv1);
        tv2 = (TextView) v.findViewById(R.id.tv2);
        ivSatish = (ImageView) v.findViewById(R.id.ivSatish);
        ivNaveen= (ImageView) v.findViewById(R.id.ivNaveen);
        tvSatish = (TextView) v.findViewById(R.id.satish);
        tvNaveen = (TextView) v.findViewById(R.id.naveen);
        tvSatish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailSatish();
            }
        });
        tvNaveen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailNaveen();
            }
        });
        ivSatish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailSatish();
            }
        });
        ivNaveen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailNaveen();
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailSatish();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailSatish();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailNaveen();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MailNaveen();
            }
        });


        return v;
    }

    public void MailSatish(){
        Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","satishyadavbunny1@gmail.com", null));
        String subject="For general queries";
        mail.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(mail, "Choose an Email client :"));
    }

    public void MailNaveen(){
        Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","renatinaveen@gmail.com", null));
        String subject="For general queries";
        mail.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(mail, "Choose an Email client :"));
    }
}
