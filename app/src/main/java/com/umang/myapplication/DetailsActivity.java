package com.umang.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class DetailsActivity extends BaseActivity {

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.dates)
    TextView dates;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.book)
    TextView book;

    @BindView(R.id.toolbar)
    TextView toolbar;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.icBackArrow)
    ImageView icBackArrow;

    ModelClass.Locations locations;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.layout_details);

        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            locations = getIntent().getParcelableExtra("details");
        }

        if (locations == null) {
            return;
        }

        icBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this,"Book Now Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        updateUI();
    }

    private void updateUI() {
        Glide.with(image.getContext())
                .load(locations.url)
                .centerCrop()
                .into(image);

        dates.setText(locations.date);
        description.setText(locations.description);
        price.setText(locations.rate + "");
        toolbar.setText(locations.place);
    }


}
