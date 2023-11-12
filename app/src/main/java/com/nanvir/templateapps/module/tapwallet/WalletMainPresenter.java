package com.nanvir.templateapps.module.tapwallet;

import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_EDIT;

import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;

public class WalletMainPresenter extends MvpBasePresenter<WalletMainView> {

    private FrameLayout frameLayout;
    private ActionBar toolbar;
    private FragmentManager supportFragmentManager;
    private WalletMainActivity walletMainActivity;

    public void setupValue(FrameLayout frameLayout, ActionBar toolbar, FragmentManager supportFragmentManager, WalletMainActivity walletMainActivity) {
        this.frameLayout = frameLayout;
        this.toolbar = toolbar;
        this.supportFragmentManager = supportFragmentManager;
        this.walletMainActivity = walletMainActivity;
    }

    public int getActionPage(FragmentConstants.Fragments frags) {
        frags.isAvailable();
        return ACTION_EDIT;
    }
}
