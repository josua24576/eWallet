package com.nanvir.templateapps.module.tapwallet;

import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.ACCOUNT_FRAGMENT;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Account;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.History;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Home;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Store;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Transaction;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.HISTORY_FRAGMENT;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.HOME_FRAGMENT;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.STORE_FRAGMENT;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.TRANSACTION_FRAGMENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nanvir.templateapps.R;
import com.nanvir.templateapps.common.base.BaseActivity;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;
import com.nanvir.templateapps.module.tapwallet.fragment.FrameLayoutFragment;

import butterknife.BindView;

public class WalletMainActivity extends BaseActivity<WalletMainView, WalletMainPresenter> implements WalletMainView {

    ActionBar toolbar;
    FragmentConstants.Fragments[] f;

    @BindView(R2.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @NonNull
    @Override
    public WalletMainPresenter createPresenter() {
        return new WalletMainPresenter();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_wallet_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        frameLayout = findViewById(R.id.frame_layout);
        toolbar = getSupportActionBar();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        f = FragmentConstants.Fragments.values();
        toolbar.setTitle(R.string.menu_home);
        getPresenter().setupValue(frameLayout, toolbar, getSupportFragmentManager(), this);
        initNavigationMenu();

        initView();
        prepareNext();
    }

    private void initNavigationMenu() {
        getMenuInflater().inflate(R.menu.template_bottom_navigation_menu, bottomNavigationView.getMenu());
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < f.length; i++) {
            // [mGroupOrder] [mId] [mCategoryOrder] [title], [drawableIcon]
            if (f[i].isAvailable()) {
                menu.add(Menu.NONE, i, Menu.NONE, f[i].getTitle()).setIcon(f[i].getResId());
            }
        }
    }

    private void prepareNext() {
    }

    private void initView() {
        int action = getPresenter().getActionPage(Home);
        loadFragment(new FrameLayoutFragment(Home, action));
    }

    private void loadFragment(Fragment latestFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, latestFragment);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentConstants.Fragments frags = Home;

            switch (item.getItemId()) {
                case HOME_FRAGMENT:
                    frags = Home;
                    break;
                case HISTORY_FRAGMENT:
                    frags = History;
                    break;
                case TRANSACTION_FRAGMENT:
                    frags = Transaction;
                    break;
                case STORE_FRAGMENT:
                    frags = Store;
                    break;
                case ACCOUNT_FRAGMENT:
                    frags = Account;
                    break;
            }

            int action = getPresenter().getActionPage(frags);
            Fragment fragment = FrameLayoutFragment.newInstance(frags, action);
            toolbar.setTitle(frags.getTitle());
            loadFragment(fragment);
            return true;
        }
    };
}