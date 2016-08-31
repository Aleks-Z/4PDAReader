package org.alex_z.app.a4pdareader.presenter.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.AppComponent;
import org.alex_z.app.a4pdareader.presenter.app.App;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import viper.Router;
import viper.ViewCallbacks;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements ViewCallbacks {
    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;
    private Unbinder unbinder;

    public BaseFragment() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return null;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        View view = inflater.inflate(layout.id(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        if (layout.menuId() == 0) return;
        inflater.inflate(layout.menuId(), menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        //noinspection unchecked
        getPresenter().takeView(this);
        getPresenter().takeRouter(getRouter());
    }

    //TODO КОСТЫЛЬ. Router должен проявиться из DI (Dagger). ЗАРЭЖУ.
    private Router getRouter() {
        return (Router) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        getPresenter().dropRouter(getRouter());
        super.onDestroyView();
    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }

    @NonNull
    protected abstract T getPresenter();

    public AppComponent getDaggerAppComponent() {
        return ((App) getActivity().getApplication()).getComponent();
    }

    public abstract void inject();
}