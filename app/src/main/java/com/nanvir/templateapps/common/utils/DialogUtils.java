package com.nanvir.templateapps.common.utils;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.nanvir.templateapps.R;

public class DialogUtils {

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

    public static void errorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        if (title == null)
            alertDialog.setTitle(null);
        else
            alertDialog.setTitle(title);
        alertDialog.setMessage(message);
//        alertDialog.setView()
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(context.getString(R.string.button_ui_okay), (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }
}
