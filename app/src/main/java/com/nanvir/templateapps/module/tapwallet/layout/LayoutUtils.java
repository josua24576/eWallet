package com.nanvir.templateapps.module.tapwallet.layout;

import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_CREATE;
import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_EDIT;
import static com.nanvir.templateapps.common.base.BaseConstanta.ACTION_VIEW;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nanvir.templateapps.R;
import com.nanvir.templateapps.common.nonrealm.ViewComponents;
import com.nanvir.templateapps.common.nonrealm.ViewConstants;

import java.util.ArrayList;
import java.util.List;

public class LayoutUtils {

    public static final int BUTTON = 0;
    public static final int CARD_VIEW = 1;
    public static final int TEXT_VIEW = 2;
    public static final int EDIT_TEXT = 3;

    private final Context context;
    private final RelativeLayout relativeLayout;
//    private final ViewComponents components;
    private final List<ViewComponents> childComponents = new ArrayList<>();

    private ViewOnClickListener onClickListener;

    public LayoutUtils(RelativeLayout relativeLayout, Context context) {
        this.relativeLayout = relativeLayout;
        this.context = context;
    }

    public ViewGroup.LayoutParams getLayoutParams(View parentLayout, Integer width, Integer height) {
        if (width == null || height == null || width < -2 || height < -2) {
            return getLayoutParam(parentLayout);
        } else {
            return getCustomLayoutParams(parentLayout, width, height);
        }
    }

    public ViewGroup.LayoutParams getLayoutParams(View parentLayout) {
        return getLayoutParam(parentLayout);
    }

    private ViewGroup.LayoutParams getCustomLayoutParams(View parentLayout, int width, int height) {
        if (parentLayout instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
            layoutParams.addRule(RelativeLayout.BELOW, childComponents.size());
            return layoutParams;
        }
        if (parentLayout instanceof LinearLayout) {
            return new LinearLayout.LayoutParams(width, height);
        }
        if (parentLayout instanceof ConstraintLayout) {
            return new ConstraintLayout.LayoutParams(width, height);
        }
        if (parentLayout instanceof CardView) {
            return new CardView.LayoutParams(width, height);
        }
        return null;
    }

    private ViewGroup.LayoutParams getLayoutParam(View parentLayout) {
        if (parentLayout instanceof RelativeLayout) {
            RelativeLayout.LayoutParams a = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            a.addRule(RelativeLayout.BELOW, childComponents.size());
            return a;
        }
        if (parentLayout instanceof LinearLayout) {
            return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        if (parentLayout instanceof ConstraintLayout) {
            return new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        }
        if (parentLayout instanceof CardView) {
            return new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        }
        return null;
    }

    public View addButton(View parentLayout, int action, ViewConstants.Buttons buttons, ViewOnClickListener listener) {
        ViewGroup.LayoutParams a = getLayoutParams(parentLayout);
        onClickListener = listener;

        if (parentLayout instanceof RelativeLayout) {
            a = (RelativeLayout.LayoutParams) getLayoutParams(parentLayout);
        }
        if (parentLayout instanceof LinearLayout) {
            a = (ViewGroup.LayoutParams) getLayoutParams(parentLayout);
        }
        if (parentLayout instanceof ConstraintLayout) {
            a = (ViewGroup.LayoutParams) getLayoutParams(parentLayout);
        }

        Button myButton = new Button(context);  // create a new Button
        myButton.setText(buttons.getButtonTitle()); // set Text in the Button
        myButton.setLayoutParams(a); // set defined layout params to Button
        myButton.setTextColor(Color.WHITE); // set white color for the text of Button
        myButton.setId(childComponents.size() + 1); // set id for Button
        myButton.setBackgroundColor(action == ACTION_VIEW ? Color.parseColor("#EEAB0C") : Color.parseColor("#95C03C")); // set Button's background color
        myButton.setEnabled(action == ACTION_EDIT || action == ACTION_CREATE);
        relativeLayout.addView(myButton); // add Button in RelativeLayout
        childComponents.add(new ViewComponents(myButton));
        if (listener != null) {
            myButton.setOnClickListener(view -> onClickListener.onClickListener(view.getId(), "Button " + buttons.getButtonTitle())
            );
        }
        return myButton;
    }

    public View addCardView(View parentLayout, int action, ViewOnClickListener listener) {
        return addCardView(parentLayout, action, null, null, listener);
    }

    public View addCardView(View parentLayout, int action, Integer x, Integer y, ViewOnClickListener listener) {
        ViewGroup.LayoutParams a = getLayoutParams(parentLayout);
        onClickListener = listener;
        int margins = dpToPx(20);

        if (parentLayout instanceof RelativeLayout) {
            RelativeLayout.LayoutParams b = (RelativeLayout.LayoutParams) a;
            b.setMargins(margins,margins,margins,margins);
            a = (ViewGroup.LayoutParams) b;
        } else if (parentLayout instanceof LinearLayout) {
            LinearLayout.LayoutParams b = (LinearLayout.LayoutParams) a;
            b.setMargins(margins,margins,margins,margins);
            a = (ViewGroup.LayoutParams) b;
        } else if (parentLayout instanceof ConstraintLayout) {
            a = (ConstraintLayout.LayoutParams) getLayoutParams(parentLayout, x, y);
            ConstraintLayout.LayoutParams b = (ConstraintLayout.LayoutParams) a;
            b.setMargins(margins,margins,margins,margins);
            a = (ViewGroup.LayoutParams) b;
        } else if (parentLayout instanceof CardView) {
            a = (CardView.LayoutParams) getLayoutParams(parentLayout, x, y);
            CardView.LayoutParams b = (CardView.LayoutParams) a;
            b.setMargins(margins,margins,margins,margins);
            a = (ViewGroup.LayoutParams) b;
        } else {
            a = getLayoutParams(parentLayout);
        }

        CardView cardView = new CardView(context);  // create a new Button
        cardView.setCardElevation(dpToPx(10));
        cardView.setLayoutParams(a); // set defined layout params to Button

        cardView.setId(childComponents.size() + 1); // set id for Button
        cardView.setBackgroundColor(Color.parseColor("#ABABEE")); // set Button's background color

        cardView.setEnabled(action == ACTION_EDIT || action == ACTION_CREATE);

        TextView cardViewTextView = new TextView(context);
        cardViewTextView.setLayoutParams(a);
        cardViewTextView.setText("Test CardView");
        cardView.addView(cardViewTextView);

        childComponents.add(new ViewComponents(cardView));

        if (listener != null) {
            cardView.setOnClickListener(view -> onClickListener.onClickListener(view.getId(), "CardView " + view.getId()));
        }
        return cardView;
    }

    public View addRelativeLayout(View parentLayout, int action, ViewOnClickListener listener, ViewComponents[] childView) {
        return addRelativeLayout(parentLayout, action, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, listener, childView, null);
    }

    public View addRelativeLayout(View parentLayout, int action, ViewOnClickListener listener, ViewComponents[] childView, Integer margins) {
        return addRelativeLayout(parentLayout, action, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, listener, childView, margins);
    }

    public View addRelativeLayout(View parentLayout, int action, Integer x, Integer y, ViewOnClickListener listener, ViewComponents[] childView, Integer margins) {
        ViewGroup.LayoutParams a = getLayoutParams(parentLayout, x, y);
        setMargins(margins, a);
        
        RelativeLayout.LayoutParams b = (RelativeLayout.LayoutParams) a;
        setMargins(margins,b);

        onClickListener = listener;

        RelativeLayout myRelativeLayout = new RelativeLayout(context);
        myRelativeLayout.setLayoutParams(a);
        myRelativeLayout.setBackgroundColor(context.getResources().getColor(R.color.purple_200));

        myRelativeLayout.setId(childComponents.size() + 1); // set id for Button
        myRelativeLayout.setEnabled(action == ACTION_EDIT || action == ACTION_CREATE);

        childComponents.add(new ViewComponents(myRelativeLayout));
        List<ViewComponents> child = new ArrayList<>();

        int id = 0;
        for (ViewComponents v : childView) {
            id ++;
//            addChildView(childComponents.get(childComponents.size() - 1), myRelativeLayout, v, listener);
            Button buttonView = (Button) v.getView();
            buttonView.setText(v.getButtons().getButtonTitle());
            buttonView.setId(id);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, id - 1);
            buttonView.setLayoutParams(params);
            buttonView.setOnClickListener(view -> onClickListener.onClickListener(view));
            myRelativeLayout.addView(v.getView());
        }

        TextView cardViewTextView = new TextView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700);
        params.addRule(RelativeLayout.BELOW, 0);
        params.setMargins(margins,margins,margins,margins);
        cardViewTextView.setLayoutParams(params);
        cardViewTextView.setBackgroundColor(Color.BLUE);
        cardViewTextView.setText("Test CardView");
        cardViewTextView.setOnClickListener(view -> onClickListener.onClickListener(view.getId(), "CardView " + view.getId()));

        myRelativeLayout.addView(cardViewTextView);
        myRelativeLayout.setBackgroundColor(Color.BLACK);


        if (listener != null) {
            myRelativeLayout.setOnClickListener(view -> onClickListener.onClickListener(view.getId(), "RelativeLayout " + view.getId()));
        }
        relativeLayout.addView(myRelativeLayout);
        return myRelativeLayout;
    }

    private void setMargins(Integer margins, ViewGroup.LayoutParams a) {
        if (margins != null) {
            RelativeLayout.LayoutParams b = (RelativeLayout.LayoutParams) a;
            b.setMargins(margins,margins,margins,margins);
        }
    }

    private void addChildView(ViewComponents viewComponents, View parentLayout, View childView, LayoutUtils.ViewOnClickListener listener) {
        viewComponents.getChildComponents().add(new ViewComponents(childView));

        ViewComponents lastComponent = viewComponents.getChildComponents().get(viewComponents.getChildComponents().size() - 1);
        Button button = (Button) lastComponent.getView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, lastComponent.getChildComponents().size() - 1);
        button.setLayoutParams(layoutParams);

        button.setId(lastComponent.getChildComponents().size() + 1);
        button.setText("BUTTON " + lastComponent.getView().getId());
        button.setOnClickListener(view -> listener.onClickListener(view.getId(), "Child Button " + view.getId()));
//        childView.setId(viewComponents.getChildComponents().size() + 1);
//        viewComponents.getChildComponents().get(viewComponents.getChildComponents().size() - 1).setView(childView);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.addRule(RelativeLayout.BELOW, viewComponents.getChildComponents().get(viewComponents.getChildComponents().size() - 1).getView().getId());
//        childView.setLayoutParams(layoutParams);
        RelativeLayout a = (RelativeLayout) parentLayout;
        a.addView(button);
    }

    public void prepareView(View[] views) {
        int id = 0;
        for (View v : views) {
            CardView b = (CardView) v;
            b.setId(id);
            RelativeLayout a = (RelativeLayout) childComponents.get(childComponents.size()-1).getView();
            a.addView(b);
        }
    }

    public ViewComponents addBelow(ViewConstants.Buttons button, int action, ViewOnClickListener listener) {
        return addBelow(button, action, null, null, listener);
    }

    public ViewComponents addBelow(ViewConstants.Buttons button, int action, Integer i, Integer i1, ViewOnClickListener listener) {
        ViewComponents v = new ViewComponents(new Button(context));
        v.setButtons(button);
        return v;
    }

    public interface ViewOnClickListener {
        void onClickListener(int id, String text);

        void onClickListener(View view);
    }

    public int pxToDp(int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(px / density);
    }

    public int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
