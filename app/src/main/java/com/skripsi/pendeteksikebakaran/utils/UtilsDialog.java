package com.skripsi.pendeteksikebakaran.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.skripsi.pendeteksikebakaran.R;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UtilsDialog {

    public static String checkErrorMessage(Activity act, String msg) {


        if (msg.contains("Failed to connect")) {
            return act.getString(R.string.check_connection);
        }

        if (msg.contains("Read error: ssl")) {
            return act.getString(R.string.check_connection);
        }

        if (msg.contains("Unable to resolve host")) {
            return act.getString(R.string.check_connection);
        }

        if (msg.contains("SocketException")) {
            return act.getString(R.string.check_connection);
        }

        if (msg.contains("UnknownHostException")) {
            return act.getString(R.string.check_connection);
        }

        if (msg.contains("EOFException")) {
            return act.getString(R.string.check_server_connection);
        }

        if (msg.contains("ConnectException")) {
            return act.getString(R.string.check_server_connection);
        }
        if (msg.contains("SSLHandshakeException")) {
            return act.getString(R.string.check_connection);
        }
        if (msg.contains("SocketTimeoutException")) {
            return act.getString(R.string.check_connection);
        }
        return msg;
    }

    public static void dismissLoading(ProgressDialog pDialog) {
        if (pDialog != null && pDialog.isShowing()) {
            try {
                pDialog.dismiss();
            } catch (Exception e) {

            }
        }
    }

    public static Dialog showCustomBasicDialog(final Activity act, String positive_name, String msg) {
        final Dialog dialog = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.custom_basic_dialog);
        msg = checkErrorMessage(act, msg);

        ImageView ivDialogImage = (ImageView) dialog.findViewById(R.id.ivDialogImage);
        TextView tvDialogMessage = (TextView) dialog.findViewById(R.id.tvDialogMessage);
        Button tvDialogClose = (Button) dialog.findViewById(R.id.btDialogClose);
        tvDialogMessage.setText(Html.fromHtml(msg));
        ivDialogImage.setVisibility(View.GONE);
//        tvDialogClose.setText(positive_name);
        tvDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static Dialog showCustomBasicDialogIcon(final Activity act, String msg) {
        final Dialog dialog = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.custom_basic_dialog);
        msg = checkErrorMessage(act, msg);

        ImageView ivDialogImage = (ImageView) dialog.findViewById(R.id.ivDialogImage);
        TextView tvDialogMessage = (TextView) dialog.findViewById(R.id.tvDialogMessage);
        Button tvDialogClose = (Button) dialog.findViewById(R.id.btDialogClose);
        tvDialogMessage.setText(Html.fromHtml(msg));
        ivDialogImage.setVisibility(View.VISIBLE);
//        tvDialogClose.setText(positive_name);
        tvDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static AlertDialog showBasicDialog(final Activity act, String positive_name, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);
        try {
            msg = checkErrorMessage(act, msg);
            builder.setMessage(Html.fromHtml(msg))
                    .setCancelable(false)
                    .setPositiveButton(positive_name,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();

                                }
                            });
            final AlertDialog a = builder.create();
            if (positive_name.toLowerCase().equals("error") || positive_name.toLowerCase().equals("mock detected")) {
                a.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        a.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(act.getResources().getColor(R.color.colorBlack));
                    }
                });
            }

            return a;
        }
        catch (Exception e) {

        }

        return null;
    }

    public static AlertDialog showDialog (final Activity act, String positive_name, String msg, boolean status) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);
        try {
            msg = checkErrorMessage(act, msg);
            builder.setMessage(Html.fromHtml(msg))
                    .setCancelable(false)
                    .setPositiveButton(positive_name,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                    if (!status) {
                                        act.finish();
                                        Intent intent = new Intent(act, act.getClass());
                                        act.startActivity(intent);
                                    }
                                }
                            });
            final AlertDialog a = builder.create();
            return a;
        }
        catch (Exception e) {

        }
        return null;
    }

    public static AlertDialog showBasicDialogKonfirmation(Activity act, String msg, String mNegativeText, String mPositiveText) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(act);

            msg = checkErrorMessage(act, msg);
            builder.setMessage(Html.fromHtml(msg))
                    .setCancelable(false)
                    .setNegativeButton(mNegativeText,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();

                                }
                            })
                    .setPositiveButton(mPositiveText,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();

                                }
                            });
            AlertDialog a = builder.create();
            String aplikasi = act.getResources().getString(R.string.app_name);
            a.setTitle(aplikasi);
            a.setIcon(R.mipmap.ic_launcher);
            return a;
        }
        catch (Exception e) {

        }
        return null;
    }

    public static ProgressDialog showLoading(Activity act, ProgressDialog pDialog) {
        try {
            if (pDialog != null) {
                pDialog.dismiss();
            }
            pDialog = new ProgressDialog(act);
            pDialog.setTitle("Processing");
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.setIndeterminate(true);
            pDialog.show();
            return pDialog;
        }
        catch (Exception e) {

        }
        return null;
    }

}