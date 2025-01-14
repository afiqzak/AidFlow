// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public final class FragmentNewsCamBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnBackNews;

  @NonNull
  public final ImageView clickImage;

  @NonNull
  public final ConstraintLayout frameLayout2;

  @NonNull
  public final Button postButton;

  @NonNull
  public final EditText userCaption;

  private FragmentNewsCamBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnBackNews, @NonNull ImageView clickImage,
      @NonNull ConstraintLayout frameLayout2, @NonNull Button postButton,
      @NonNull EditText userCaption) {
    this.rootView = rootView;
    this.btnBackNews = btnBackNews;
    this.clickImage = clickImage;
    this.frameLayout2 = frameLayout2;
    this.postButton = postButton;
    this.userCaption = userCaption;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewsCamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewsCamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_news_cam, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewsCamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBackNews;
      MaterialButton btnBackNews = ViewBindings.findChildViewById(rootView, id);
      if (btnBackNews == null) {
        break missingId;
      }

      id = R.id.click_image;
      ImageView clickImage = ViewBindings.findChildViewById(rootView, id);
      if (clickImage == null) {
        break missingId;
      }

      ConstraintLayout frameLayout2 = (ConstraintLayout) rootView;

      id = R.id.post_button;
      Button postButton = ViewBindings.findChildViewById(rootView, id);
      if (postButton == null) {
        break missingId;
      }

      id = R.id.userCaption;
      EditText userCaption = ViewBindings.findChildViewById(rootView, id);
      if (userCaption == null) {
        break missingId;
      }

      return new FragmentNewsCamBinding((ConstraintLayout) rootView, btnBackNews, clickImage,
          frameLayout2, postButton, userCaption);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}