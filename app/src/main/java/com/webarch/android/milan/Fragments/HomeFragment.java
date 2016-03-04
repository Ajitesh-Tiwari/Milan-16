package com.webarch.android.milan.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.webarch.android.milan.MainActivity;
import com.webarch.android.milan.R;

/**
 * Created by ajitesh on 11/2/16.
 */
public class HomeFragment extends Fragment {
    View view;
    ImageView tapItem1,tapItem2,tapItem3,tapItem4;
    TapBarMenu tapBarMenu;
    TextView tvHome;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        bindViews();
        setTapIcons();
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
                /*Toast.makeText(getActivity(),v.getId()+"",Toast.LENGTH_SHORT).show();
                switch (v.getId()){
                    case R.id.item1:
                        Toast.makeText(getActivity(),"Facebook",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(getActivity(),"YouTube",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(getActivity(),"Twitter",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(getActivity(),"Share",Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }
        });
        tvHome.setText(R.string.milan_desc);
        return view;
    }
    public void bindViews()
    {
        tapItem1= (ImageView) view.findViewById(R.id.item1);
        tapItem2= (ImageView) view.findViewById(R.id.item2);
        //tapItem3= (ImageView) view.findViewById(R.id.item3);
        tapItem4= (ImageView) view.findViewById(R.id.item4);
        tvHome=(TextView)view.findViewById(R.id.tvHome);
        tapBarMenu=(TapBarMenu)view.findViewById(R.id.tapBarMenu);

    }
    public void setTapIcons()
    {
        tapItem1.setImageDrawable(new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_facebook_f).color(Color.WHITE).sizeDp(24));
        tapItem2.setImageDrawable(new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_youtube_play).color(Color.WHITE).sizeDp(24));
        //tapItem3.setImageDrawable(new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_twitter).color(Color.WHITE).sizeDp(24));
        tapItem4.setImageDrawable(new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_share_alt).color(Color.WHITE).sizeDp(24));
    }


}
