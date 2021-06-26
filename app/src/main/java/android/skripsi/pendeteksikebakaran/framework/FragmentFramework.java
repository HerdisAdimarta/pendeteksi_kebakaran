package android.skripsi.pendeteksikebakaran.framework;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class FragmentFramework extends Fragment {

    //    protected Realm mRealm;
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeSystem();
    }

    private void initializeSystem() {
        mActivity = getActivity();
    }

//    public void initRealm() {
//        mRealm = Realm.getDefaultInstance();
//    }

    @Override
    public void onStart() {
        super.onStart();
//        if (mRealm != null) {
//            initRealm();
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        if (mRealm != null) {
//            mRealm.close();
//        }
    }

}