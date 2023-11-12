package com.nanvir.templateapps.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity implements MvpView,
        com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback<V, P> {

    //region Variable Global
    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    //endregion

    //region Mosby Variable
    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;
    //endregion

    //region General Method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mUnbinder = ButterKnife.bind(this);
        getMvpDelegate().onCreate(savedInstanceState);

        //setRequestedOrientation(SharedPreferencesUtil.getPengaturanOrientasi(this));

        onViewReady(savedInstanceState, getIntent());
        initValue();
        initFunction();
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        // To be used by child activities
    }

    @CallSuper
    protected void initFunction() {
    }

    @CallSuper
    protected void initValue() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);

    }
    //endregion

    //region Mosby MVP
    @NonNull
    public abstract P createPresenter();

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this, this, true);
        }

        return mvpDelegate;
    }

    @NonNull
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }
    //endregion

    //region Self Method
    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public static AlertDialog.Builder getDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        if (title == null)
            alertDialog.setTitle(null);
        else
            alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        return alertDialog;
    }

    protected void setFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected abstract int getContentView();
    //endregion
}
