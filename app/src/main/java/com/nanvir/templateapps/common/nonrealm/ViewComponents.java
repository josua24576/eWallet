package com.nanvir.templateapps.common.nonrealm;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ViewComponents {

    private View view;

    private List<ViewComponents> childComponents = new ArrayList<>();

    private ViewConstants.Buttons buttons;

    public ViewComponents() {
    }

    public ViewConstants.Buttons getButtons() {
        return buttons;
    }

    public void setButtons(ViewConstants.Buttons buttons) {
        this.buttons = buttons;
    }

    public ViewComponents(View view) {
        this.view = view;
    }

    public ViewComponents(List<ViewComponents> childComponents) {
        this.childComponents = childComponents;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }


    public List<ViewComponents> getChildComponents() {
        return childComponents;
    }

    public void setChildComponents(List<ViewComponents> childComponents) {
        this.childComponents = childComponents;
    }
}
