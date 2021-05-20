package com.example.connectivitycheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            // get default snackBar layout
            View parentLayout = MainActivity.instance.findViewById(android.R.id.content);
            if (noConnectivity) {
                // create an instance of the snackBar
                final Snackbar snackbar = Snackbar.make(parentLayout, "", Snackbar.LENGTH_INDEFINITE);
                // inflate the custom_snackBar_view created previously
                View customSnackView = MainActivity.instance.getLayoutInflater().inflate(R.layout.snackbar_disconnected, null);
                // set the background of the default snackBar as transparent
                snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                // now change the layout of the snackBar
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                // set padding of the all corners as 0
                snackbarLayout.setPadding(0, 0, 0, 0);
                // add the custom snack bar layout to snackbar layout
                snackbarLayout.addView(customSnackView, 0);
                ImageView icWiFi = customSnackView.findViewById(R.id.imageViewWiFi);
                icWiFi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            } else {
                // create an instance of the snackBar
                final Snackbar snackbar = Snackbar.make(parentLayout, "", Snackbar.LENGTH_SHORT);
                // inflate the custom_snackBar_view created previously
                View customSnackView = MainActivity.instance.getLayoutInflater().inflate(R.layout.snackbar_connected, null);
                // set the background of the default snackBar as transparent
                snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                // now change the layout of the snackBar
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                // set padding of the all corners as 0
                snackbarLayout.setPadding(0, 0, 0, 0);
                // add the custom snack bar layout to snackbar layout
                snackbarLayout.addView(customSnackView, 0);
                snackbar.show();
            }
        }
    }
}