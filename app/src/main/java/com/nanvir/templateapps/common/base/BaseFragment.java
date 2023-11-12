package com.nanvir.templateapps.common.base;

import static com.nanvir.templateapps.common.base.BaseConstanta.PROGRESS_DIALOG_LENGTH;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends Fragment implements MvpView,
        com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback<V, P> {

    //region Variable Global
    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;
    protected boolean hasInitializedRootView = false;
    protected View rootView;
    //endregion

    //region Mosby Variable
    protected ActivityMvpDelegate mvpDelegate;
    protected P presenter;
    protected boolean retainInstance;
    //endregion

    //region General Method
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = createPersistentView(inflater, container, savedInstanceState, getFragmentLayout());
        getMvpDelegate().onCreate(savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasInitializedRootView) {
            hasInitializedRootView = true;

            onInitView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }
    //endregion

    //region Mosby MVP
    @NonNull
    public abstract P createPresenter();

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(getActivity(), this, true);
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
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                }
            }, PROGRESS_DIALOG_LENGTH);
        }
    }

    protected abstract int getFragmentLayout();

    public abstract void onInitView();

    public View createPersistentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int layout) {
        if (this.rootView == null) {
            this.rootView = inflater.inflate(layout, container, false);
        } else {
            ViewGroup viewGroupParent = (ViewGroup) this.rootView.getParent();
            if (viewGroupParent != null) {
                viewGroupParent.removeView(this.rootView);
            }

            if (container != null) {
                container.removeView(this.rootView);
            }
        }

        return this.rootView;
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    //endregion
}