package com.nanvir.templateapps;

import static com.nanvir.templateapps.common.base.BaseConstanta.DATABASE_NAME;
import static com.nanvir.templateapps.common.base.BaseConstanta.SPLASH_DISPLAY_LENGTH;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nanvir.templateapps.common.app.AppMigration;
import com.nanvir.templateapps.common.base.BaseActivity;
import com.nanvir.templateapps.common.realm.User;
import com.nanvir.templateapps.module.tapwallet.WalletMainActivity;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;
import com.nanvir.templateapps.module.tapwallet.constants.UserConstants;

import java.io.File;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashScreenActivity extends BaseActivity<SplashScreenView, SplashScreenPresenter> implements SplashScreenView {

    private Realm realm;

    @NonNull
    @Override
    public SplashScreenPresenter createPresenter() {
        return new SplashScreenPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initPermission();
        initRealmDatabase();
        initView();
    }

    private void initPermission() {

    }

    private void initRealmDatabase() {
        Realm.init(SplashScreenActivity.this);
        final String pathRealmDb;
        pathRealmDb = getExternalFilesDir(null) + File.separator + BuildConfig.FOLDER_NAME + File.separator;
        RealmConfiguration builder = new RealmConfiguration.Builder()
                .directory(new File(pathRealmDb))
                .name(DATABASE_NAME)
                .schemaVersion(4)
                .migration(new AppMigration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(builder);
        realm = Realm.getDefaultInstance();
    }

    private void prepareNext() {
        onNext();
    }

    private void onNext() {
        Intent intent = new Intent(this, WalletMainActivity.class);
        startActivity(intent);
    }

    private void initView() {
        User user = Realm.getDefaultInstance().where(User.class).findFirst();
        if (user != null) {
            UserConstants.Users userConstants = UserConstants.Users.getByValue(user.getGroupPositionId());
            getPresenter().instantiateRoleBasedAction(user, userConstants);
            prepareNext();
        }
    }
}