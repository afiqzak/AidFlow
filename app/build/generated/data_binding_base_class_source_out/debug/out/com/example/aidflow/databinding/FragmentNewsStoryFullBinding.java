// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNewsStoryFullBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnBackNews;

  @NonNull
  public final ConstraintLayout frameLayout;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView storyDesc;

  @NonNull
  public final ImageView storyImage;

  @NonNull
  public final ImageView userImage;

  @NonNull
  public final TextView userName;

  private FragmentNewsStoryFullBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnBackNews, @NonNull ConstraintLayout frameLayout,
      @NonNull LinearLayout linearLayout, @NonNull TextView storyDesc,
      @NonNull ImageView storyImage, @NonNull ImageView userImage, @NonNull TextView userName) {
    this.rootView = rootView;
    this.btnBackNews = btnBackNews;
    this.frameLayout = frameLayout;
    this.linearLayout = linearLayout;
    this.storyDesc = storyDesc;
    this.storyImage = storyImage;
    this.userImage = userImage;
    this.userName = userName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewsStoryFullBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewsStoryFullBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_news_story_full, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewsStoryFullBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBackNews;
      MaterialButton btnBackNews = ViewBindings.findChildViewById(rootView, id);
      if (btnBackNews == null) {
        break missingId;
      }

      ConstraintLayout frameLayout = (ConstraintLayout) rootView;

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.story_desc;
      TextView storyDesc = ViewBindings.findChildViewById(rootView, id);
      if (storyDesc == null) {
        break missingId;
      }

      id = R.id.story_image;
      ImageView storyImage = ViewBindings.findChildViewById(rootView, id);
      if (storyImage == null) {
        break missingId;
      }

      id = R.id.user_image;
      ImageView userImage = ViewBindings.findChildViewById(rootView, id);
      if (userImage == null) {
        break missingId;
      }

      id = R.id.user_name;
      TextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      return new FragmentNewsStoryFullBinding((ConstraintLayout) rootView, btnBackNews, frameLayout,
          linearLayout, storyDesc, storyImage, userImage, userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
