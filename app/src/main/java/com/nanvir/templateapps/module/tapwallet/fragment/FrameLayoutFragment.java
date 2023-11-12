package com.nanvir.templateapps.module.tapwallet.fragment;

import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_EDIT;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.nanvir.templateapps.R;
import com.nanvir.templateapps.common.base.BaseFragment;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;

import butterknife.BindView;


public class FrameLayoutFragment extends BaseFragment<FrameLayoutView, FrameLayoutPresenter> implements FrameLayoutView {

    @BindView(R2.id.fragmentrl)
    RelativeLayout relativeLayout;
    @BindView(R2.id.nsv)
    NestedScrollView nestedScrollView;
    private FragmentConstants.Fragments frags;
    private int action;

    public FrameLayoutFragment(FragmentConstants.Fragments frags, int action) {
        super();
        this.frags = frags;
        this.action = action;
    }

    @NonNull
    @Override
    public FrameLayoutPresenter createPresenter() {
        return new FrameLayoutPresenter(getActivity());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.tap_wallet_home_fragment;
    }

    public static FrameLayoutFragment newInstance(FragmentConstants.Fragments frags, int action) {
        Bundle args = new Bundle();
        args.putParcelable("frags", frags);
        args.putInt("action", action);
        FrameLayoutFragment fragment = new FrameLayoutFragment(frags, action);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onInitView() {
        nestedScrollView.setFillViewport(true);
        getPresenter().loadFragments(frags, relativeLayout, action);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
