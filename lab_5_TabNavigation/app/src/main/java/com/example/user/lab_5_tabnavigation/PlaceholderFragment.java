package com.example.user.lab_5_tabnavigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 28/02/2018.
 */

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private String page;
    private String imageURL;
    private int color;
    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(String page,String imageURL,int color) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("fragment_page",page);
        args.putString("image_url",imageURL);
        args.putInt("back_color",color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getString("fragment_page");
        imageURL=getArguments().getString("image_url");
        color=getArguments().getInt("back_color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) layout.findViewById(R.id.text_instance);
        textView.setText(page);
        ImageView imageView=(ImageView) layout.findViewById(R.id.imageView);
        Picasso.with(getContext()).load(imageURL).resize(400,400).into(imageView);
        layout.setBackgroundColor(color);
        return layout;
    }
}
