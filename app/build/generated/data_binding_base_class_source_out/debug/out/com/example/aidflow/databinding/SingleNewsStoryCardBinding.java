// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SingleNewsStoryCardBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ShapeableImageView IVProfileStory;

  @NonNull
  public final ImageView storyImage;

  private SingleNewsStoryCardBinding(@NonNull CardView rootView,
      @NonNull ShapeableImageView IVProfileStory, @NonNull ImageView storyImage) {
    this.rootView = rootView;
    this.IVProfileStory = IVProfileStory;
    this.storyImage = storyImage;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleNewsStoryCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleNewsStoryCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_news_story_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleNewsStoryCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.IVProfileStory;
      ShapeableImageView IVProfileStory = ViewBindings.findChildViewById(rootView, id);
      if (IVProfileStory == null) {
        break missingId;
      }

      id = R.id.story_image;
      ImageView storyImage = ViewBindings.findChildViewById(rootView, id);
      if (storyImage == null) {
        break missingId;
      }

      return new SingleNewsStoryCardBinding((CardView) rootView, IVProfileStory, storyImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
