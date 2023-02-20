package com.example.scollege.ui.home;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.scollege.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
public class HomeFragment extends Fragment {
    private SliderLayout sliderlayout;
    private ImageView map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderlayout = view.findViewById(R.id.slider);
        sliderlayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderlayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderlayout.setScrollTimeInSec(3);    //Sec to change new to image
        setSliderViews();
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }
    private void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=Guru Nanak Khalsa College of Arts, Science & Commerce (Autonomous)");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
    private void setSliderViews() {
        for(int i=0;i<5;i++){                   //loop will run 5 times
            DefaultSliderView sliderView=new DefaultSliderView(getContext());
            switch(i){       //setting values for images
                case 0:
                    sliderView.setDescription("");   //image description
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/scollege-72440.appspot.com/o/SliderImages%2Fimages.jpeg?alt=media&token=3f4e0b8f-f6e8-4a62-9e25-d8157a1f1706");     //Image link
                    break;       //setting values for images
                case 1:
                    sliderView.setDescription("");   //image description
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/scollege-72440.appspot.com/o/SliderImages%2Fimages%20(1).jpeg?alt=media&token=c6cf6189-54f5-46de-be16-c5d2f3357a11");     //Image link
                    break;       //setting values for images
                case 2:
                    sliderView.setDescription("");   //image description
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/scollege-72440.appspot.com/o/SliderImages%2FKhalsa_College_Matunga.jpg?alt=media&token=03fc318c-8688-4546-830e-53f043e34557");     //Image link
                    break;       //setting values for images
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderlayout.addSliderView(sliderView);
        }
    }
}