package cn.saymagic.magicbox.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.saymagic.magicbox.R;
import cn.saymagic.magicbox.ui.animation.DepthPageTransformer;

public class PredictionPokerFragment extends Fragment {

    @InjectView(R.id.vp_prediction_poker)
    ViewPager mVpPredictionPoker;

    private OnFragmentInteractionListener mListener;
    private String mTopTipArray[] = {"这是一个神奇的预言！","预言是，整副牌","其中，牌面向上的共","向上的牌黑色共","黑色牌都是","奇数牌都是","唯一的例外就是","Thanks for All"};
    private String mLeftTipArray[] = {"","乱七八糟","21","6","奇数","梅花","黑桃A",""};
    private String mRightTipArray[] = {"","","张","张","","","",""};
    private List<View> mPageViews = new ArrayList<View>();

    public static PredictionPokerFragment newInstance() {
        PredictionPokerFragment fragment = new PredictionPokerFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","onCreate");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_prediction_poker, container, false);
        ButterKnife.inject(this,view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        mVpPredictionPoker.setAdapter(new PokerAdapter(getActivity()));
        mVpPredictionPoker.setPageTransformer(true,new DepthPageTransformer());
        super.onViewCreated(view, savedInstanceState);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    static class ViewHolder{
        @InjectView(R.id.top_tip)
        TextView mTopTip;
        @InjectView(R.id.left_tip)
        TextView mLeftTip;
        @InjectView(R.id.right_tip)
        TextView mRightTip;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public class PokerAdapter extends PagerAdapter{
        private Context ctx;
        public PokerAdapter(Context ctx){
            this.ctx = ctx;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(ctx);
            View v = layoutInflater.inflate(R.layout.vp_pre_poker_item,null);
            ViewHolder holder = new ViewHolder(v);
            holder.mTopTip.setText(mTopTipArray[position]);
            holder.mLeftTip.setText(mLeftTipArray[position]);
            holder.mRightTip.setText(mRightTipArray[position]);
            mPageViews.add(v);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mPageViews.get(position));
        }


        @Override
        public int getCount() {
            return mTopTipArray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object ;
        }
    }

}
