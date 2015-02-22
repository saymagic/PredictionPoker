package cn.saymagic.magicbox.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

import cn.saymagic.magicbox.R;
import cn.saymagic.magicbox.ui.fragment.PredictionPokerFragment;


public class MainActivity extends ActionBarActivity implements PredictionPokerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PredictionPokerFragment())
                    .commit();
        }
        getSupportActionBar().hide();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
