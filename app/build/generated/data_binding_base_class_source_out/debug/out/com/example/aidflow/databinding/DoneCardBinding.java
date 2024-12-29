// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DoneCardBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView donePlace;

  @NonNull
  public final ImageButton doneStarButton;

  @NonNull
  public final TextView doneTitle;

  private DoneCardBinding(@NonNull CardView rootView, @NonNull TextView donePlace,
      @NonNull ImageButton doneStarButton, @NonNull TextView doneTitle) {
    this.rootView = rootView;
    this.donePlace = donePlace;
    this.doneStarButton = doneStarButton;
    this.doneTitle = doneTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DoneCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DoneCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.done_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DoneCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.done_place;
      TextView donePlace = ViewBindings.findChildViewById(rootView, id);
      if (donePlace == null) {
        break missingId;
      }

      id = R.id.doneStarButton;
      ImageButton doneStarButton = ViewBindings.findChildViewById(rootView, id);
      if (doneStarButton == null) {
        break missingId;
      }

      id = R.id.done_title;
      TextView doneTitle = ViewBindings.findChildViewById(rootView, id);
      if (doneTitle == null) {
        break missingId;
      }

      return new DoneCardBinding((CardView) rootView, donePlace, doneStarButton, doneTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
