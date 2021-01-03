package com.mardefasma.influaction_java;

import android.view.View;
import android.widget.ProgressBar;

public class Utils {
    public void showDialog(ProgressBar progressBar) {
        progressBar.setIndeterminate(true);

        if(progressBar.getVisibility() != View.VISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    public void hideDialog(ProgressBar progressBar) {
        if(progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.GONE);
    }
}
