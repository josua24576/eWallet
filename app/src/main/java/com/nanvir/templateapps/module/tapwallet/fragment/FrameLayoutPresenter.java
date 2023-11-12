package com.nanvir.templateapps.module.tapwallet.fragment;

import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_VIEW;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Account;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.History;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Home;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Store;
import static com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants.Fragments.Transaction;
import static com.nanvir.templateapps.module.tapwallet.layout.LayoutUtils.BUTTON;
import static com.nanvir.templateapps.module.tapwallet.layout.LayoutUtils.CARD_VIEW;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.nanvir.templateapps.R;
import com.nanvir.templateapps.common.nonrealm.ViewComponents;
import com.nanvir.templateapps.common.nonrealm.ViewConstants;
import com.nanvir.templateapps.module.tapwallet.constants.FragmentConstants;
import com.nanvir.templateapps.common.nonrealm.ViewConstants.Buttons;
import com.nanvir.templateapps.module.tapwallet.layout.LayoutUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FrameLayoutPresenter extends MvpBasePresenter<FrameLayoutView> implements LayoutUtils.ViewOnClickListener {

    private Context context;
    private Realm realm;
    private int action;
    private FragmentConstants.Fragments frags;
    private List<View> childComponents = new ArrayList<>();

    public FrameLayoutPresenter(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
    }

    @SuppressLint("ResourceType")
    public void loadFragments(FragmentConstants.Fragments frags, RelativeLayout relativeLayout, int action) {
        setCurrentAction(action);
        this.frags = frags;
        LayoutUtils layoutUtils = new LayoutUtils(relativeLayout, context);


        if (frags == Home) {
            childComponents.add(layoutUtils.addRelativeLayout(relativeLayout, action,this, new ViewComponents[]{
                    layoutUtils.addBelow(Buttons.Save, action,this),
                    layoutUtils.addBelow(Buttons.Continue, action,this),
                    layoutUtils.addBelow(Buttons.Okay, action,this)
            }, 10));
//            childComponents.add(layoutUtils.addRelativeLayout(relativeLayout, action, this, new ViewComponents[]{
//                    layoutUtils.addBelow(Buttons.Save, action,this),
//                    layoutUtils.addBelow(Buttons.Continue, action,this),
//                    layoutUtils.addBelow(Buttons.Continue, action,this)
//            }));
//            childComponents.add(layoutUtils.addRelativeLayout(relativeLayout, action, this, new ViewComponents[]{
//                    layoutUtils.addBelow(Buttons.Save, action,this),
//                    layoutUtils.addBelow(Buttons.Okay, action,this),
//                    layoutUtils.addBelow(Buttons.Okay, action,this)
//            }));


//            childComponents.add(layoutUtils.addRelativeLayout(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
//            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Yes,this));
//            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.No,this));
//            childComponents.add(layoutUtils.addButton(relativeLayout, ACTION_VIEW, Buttons.Back,this));
//            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Delete,this));
//            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Save,this));
        }

        if (frags == History) {
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action,  this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Back,this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Continue,this));
        }
        if (frags == Transaction) {
            childComponents.add(layoutUtils.addCardView(relativeLayout, action,  this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action,  this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action,  this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Yes,this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.No,this));
        }
        if (frags == Store) {
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Reset,this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Continue,this));
        }
        if (frags == Account) {
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addCardView(relativeLayout, action, 240, 300, this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Save,this));
            childComponents.add(layoutUtils.addButton(relativeLayout, action, Buttons.Okay,this));
        }
    }

    private void setCurrentAction(int action) {
        this.action = action;
    }

    @Override
    public void onClickListener(int id, String text) {
        if (frags == Home) {
            switch (id) {
                case 1: //BUTTON SAVE
                    break;
                case 2: //
                    break;
            }
        }
        if (frags == History) {
            switch (id) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        if (frags == Transaction) {
            switch (id) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        if (frags == Store) {
            switch (id) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        if (frags == Account) {
            switch (id) {
                case 1:
                    break;
                case 2:
                    break;
            }
        }


        Log.d("ItemClick", "onClickListener: " + frags.getTitle() + " || " + id + " || " + text);
    }

    @Override
    public void onClickListener(View view) {
        Button v = (Button) view;
        Log.d("ItemClick", "onClickListener: " + frags.getTitle() + " || " + view.getId() + " || " + v.getText().toString());
    }
}
