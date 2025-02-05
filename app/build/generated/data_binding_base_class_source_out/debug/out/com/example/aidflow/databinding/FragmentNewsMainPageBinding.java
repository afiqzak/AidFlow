// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNewsMainPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView NewsTopTitle;

  @NonNull
  public final MaterialButton cameraButton;

  @NonNull
  public final FragmentContainerView fragmentContainerViewNews;

  @NonNull
  public final FragmentContainerView fragmentContainerViewStory;

  @NonNull
  public final RadioButton newsButton;

  @NonNull
  public final RadioButton projectButton;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final RadioGroup toggleNews;

  private FragmentNewsMainPageBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView NewsTopTitle, @NonNull MaterialButton cameraButton,
      @NonNull FragmentContainerView fragmentContainerViewNews,
      @NonNull FragmentContainerView fragmentContainerViewStory, @NonNull RadioButton newsButton,
      @NonNull RadioButton projectButton, @NonNull RelativeLayout relativeLayout,
      @NonNull RadioGroup toggleNews) {
    this.rootView = rootView;
    this.NewsTopTitle = NewsTopTitle;
    this.cameraButton = cameraButton;
    this.fragmentContainerViewNews = fragmentContainerViewNews;
    this.fragmentContainerViewStory = fragmentContainerViewStory;
    this.newsButton = newsButton;
    this.projectButton = projectButton;
    this.relativeLayout = relativeLayout;
    this.toggleNews = toggleNews;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewsMainPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewsMainPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_news_main_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewsMainPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.NewsTopTitle;
      TextView NewsTopTitle = ViewBindings.findChildViewById(rootView, id);
      if (NewsTopTitle == null) {
        break missingId;
      }

      id = R.id.cameraButton;
      MaterialButton cameraButton = ViewBindings.findChildViewById(rootView, id);
      if (cameraButton == null) {
        break missingId;
      }

      id = R.id.fragmentContainerViewNews;
      FragmentContainerView fragmentContainerViewNews = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainerViewNews == null) {
        break missingId;
      }

      id = R.id.fragmentContainerViewStory;
      FragmentContainerView fragmentContainerViewStory = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainerViewStory == null) {
        break missingId;
      }

      id = R.id.newsButton;
      RadioButton newsButton = ViewBindings.findChildViewById(rootView, id);
      if (newsButton == null) {
        break missingId;
      }

      id = R.id.projectButton;
      RadioButton projectButton = ViewBindings.findChildViewById(rootView, id);
      if (projectButton == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.toggleNews;
      RadioGroup toggleNews = ViewBindings.findChildViewById(rootView, id);
      if (toggleNews == null) {
        break missingId;
      }

      return new FragmentNewsMainPageBinding((ConstraintLayout) rootView, NewsTopTitle,
          cameraButton, fragmentContainerViewNews, fragmentContainerViewStory, newsButton,
          projectButton, relativeLayout, toggleNews);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
