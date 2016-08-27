package org.alex_z.app.a4pdareader.presenter.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.google.common.base.Preconditions;


import org.alex_z.app.a4pdareader.R;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements IBaseRouter {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        unbinder = ButterKnife.bind(this);
        onStartRouter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return true;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        if (layout.menuId() == 0) return true;
        getMenuInflater().inflate(layout.menuId(), menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void showFragment(@IdRes int replaceId, BaseFragment fragment, boolean addBackStack) {
        Preconditions.checkNotNull(fragment);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(replaceId, fragment);
        if (addBackStack) tx.addToBackStack(fragment.getFragmentName());
        tx.commit();
    }
}