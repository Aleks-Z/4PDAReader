package org.alex_z.app.a4pdareader.additional;

import android.content.res.Resources;

import org.alex_z.app.a4pdareader.R;

public class StringProvider {
    private static StringProvider instance;

    public static StringProvider getInstance() {
        if (instance == null) throw new NullPointerException();
        return instance;
    }

    public static void initialization(Resources resources) {
        if (instance == null) instance = new StringProvider(resources);
    }

    public static void release() {
        instance = null;
    }

    private StringProvider(Resources resources) {
        LINK_SOURCE = resources.getString(R.string.link_source);
    }

    public final String LINK_SOURCE;
}
