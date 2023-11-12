package com.nanvir.templateapps.common.nonrealm;

public class ViewConstants {

    private int id;
    private String title;
    private int resId;
    private int viewGroupId;

    public enum ViewPosition {
        Above(0),
        Left(0),
        Right(0),
        Below(0);

        private int linkedViewId;

        ViewPosition(int i) {
        }

        public int getLinkedViewId() {
            return linkedViewId;
        }

        public void setLinkedViewId(int linkedViewId) {
            this.linkedViewId = linkedViewId;
        }
    }

    public ViewConstants(int id, String title, int resId, int viewGroupId) {
        this.id = id;
        this.title = title;
        this.resId = resId;
        this.viewGroupId = viewGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getViewGroupId() {
        return viewGroupId;
    }

    public void setViewGroupId(int viewGroupId) {
        this.viewGroupId = viewGroupId;
    }

    private static final String BUTTON_SAVE = "SAVE";
    private static final String BUTTON_DELETE = "DELETE";
    private static final String BUTTON_YES = "YES";
    private static final String BUTTON_NO = "NO";
    private static final String BUTTON_OKAY = "OKAY";
    private static final String BUTTON_BACK = "BACK";
    private static final String BUTTON_CONTINUE = "CONTINUE";
    private static final String BUTTON_RESET = "RESET";

    public enum Buttons {
        Save(BUTTON_SAVE),
        Delete(BUTTON_DELETE),
        Yes(BUTTON_YES),
        No(BUTTON_NO),
        Okay(BUTTON_OKAY),
        Back(BUTTON_BACK),
        Continue(BUTTON_CONTINUE),
        Reset(BUTTON_RESET);

        private final String buttonTitle;

        Buttons(String buttonTitle) {
            this.buttonTitle = buttonTitle;
        }

        public String getButtonTitle() {
            return buttonTitle;
        }
    }
}
