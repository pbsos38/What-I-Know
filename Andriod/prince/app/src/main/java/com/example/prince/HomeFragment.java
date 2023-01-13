package com.example.prince;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    HashMap<String,String> HashMapForURL;
   // private DashBoardViewModel dashBoardViewModel;
    private HomeViewModel homeViewModel;
    private SliderLayout slider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView=root.findViewById(R.id.recyclerView);
        slider=root.findViewById(R.id.slider);
        HashMapForURL = new HashMap<String, String>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));


        homeViewModel.GetSlider().observe(getViewLifecycleOwner(),sliderBeans -> {
            for (int i = 0; i <sliderBeans.size(); i++) {
                HashMapForURL.put(sliderBeans.get(i).getTitle(),
                        sliderBeans.get(i).getImageName());
            }
            for (String name : HashMapForURL.keySet()) {
                TextSliderView textSliderView = new TextSliderView(getContext());
                textSliderView
                        .description(name)
                        .image(HashMapForURL.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(HomeFragment.this);
                slider.addSlider(textSliderView);
            }
            slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slider.setCustomAnimation(new DescriptionAnimation());
            slider.setDuration(3000);
            slider.addOnPageChangeListener(HomeFragment.this);
        });
        return root;
    }




    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}