package com.nanvir.templateapps;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.nanvir.templateapps.common.realm.User;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;
import com.nanvir.templateapps.module.tapwallet.constants.UserConstants;

public class SplashScreenPresenter extends MvpBasePresenter<SplashScreenView> {

    private Context context;

    public SplashScreenPresenter(Context context) {
        this.context = context;
    }

    public void instantiateRoleBasedAction(User user, UserConstants.Users userConstants) {
//        if (userConstants.isMasterUser()) {
//            for (FragmentConstants.Fragments f : FragmentConstants.Fragments.values()) {
//                f.setAvailable(true);
//            }
//        } else {
//            for (FragmentConstants.Fragments f : FragmentConstants.Fragments.values()) {
//                for (int i = 0; i < userConstants.getUserMenus().getUserMenu().length; i ++) {
//                    if (userConstants.getUserMenus().getUserMenu()[i] != null && f.getId() == userConstants.getUserMenus().getUserMenu()[i]) {
//                        f.setAvailable(true);
//                    }
//                }
//            }
//        }
    }
}
