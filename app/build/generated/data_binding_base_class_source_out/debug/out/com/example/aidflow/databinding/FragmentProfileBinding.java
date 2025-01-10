// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ShapeableImageView IVProfile;

  @NonNull
  public final RadioButton RBBadges;

  @NonNull
  public final RadioButton RBOverview;

  @NonNull
  public final TextView TVFName;

  @NonNull
  public final TextView TVLName;

  @NonNull
  public final TextView TVUserEmail;

  @NonNull
  public final TextView TVUserPhone;

  @NonNull
  public final TextView TVUsername;

  @NonNull
  public final ConstraintLayout frameLayout5;

  @NonNull
  public final RadioGroup toggleProfile;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull ShapeableImageView IVProfile, @NonNull RadioButton RBBadges,
      @NonNull RadioButton RBOverview, @NonNull TextView TVFName, @NonNull TextView TVLName,
      @NonNull TextView TVUserEmail, @NonNull TextView TVUserPhone, @NonNull TextView TVUsername,
      @NonNull ConstraintLayout frameLayout5, @NonNull RadioGroup toggleProfile) {
    this.rootView = rootView;
    this.IVProfile = IVProfile;
    this.RBBadges = RBBadges;
    this.RBOverview = RBOverview;
    this.TVFName = TVFName;
    this.TVLName = TVLName;
    this.TVUserEmail = TVUserEmail;
    this.TVUserPhone = TVUserPhone;
    this.TVUsername = TVUsername;
    this.frameLayout5 = frameLayout5;
    this.toggleProfile = toggleProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.IVProfile;
      ShapeableImageView IVProfile = ViewBindings.findChildViewById(rootView, id);
      if (IVProfile == null) {
        break missingId;
      }

      id = R.id.RBBadges;
      RadioButton RBBadges = ViewBindings.findChildViewById(rootView, id);
      if (RBBadges == null) {
        break missingId;
      }

      id = R.id.RBOverview;
      RadioButton RBOverview = ViewBindings.findChildViewById(rootView, id);
      if (RBOverview == null) {
        break missingId;
      }

      id = R.id.TVFName;
      TextView TVFName = ViewBindings.findChildViewById(rootView, id);
      if (TVFName == null) {
        break missingId;
      }

      id = R.id.TVLName;
      TextView TVLName = ViewBindings.findChildViewById(rootView, id);
      if (TVLName == null) {
        break missingId;
      }

      id = R.id.TVUserEmail;
      TextView TVUserEmail = ViewBindings.findChildViewById(rootView, id);
      if (TVUserEmail == null) {
        break missingId;
      }

      id = R.id.TVUserPhone;
      TextView TVUserPhone = ViewBindings.findChildViewById(rootView, id);
      if (TVUserPhone == null) {
        break missingId;
      }

      id = R.id.TVUsername;
      TextView TVUsername = ViewBindings.findChildViewById(rootView, id);
      if (TVUsername == null) {
        break missingId;
      }

      ConstraintLayout frameLayout5 = (ConstraintLayout) rootView;

      id = R.id.toggleProfile;
      RadioGroup toggleProfile = ViewBindings.findChildViewById(rootView, id);
      if (toggleProfile == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, IVProfile, RBBadges,
          RBOverview, TVFName, TVLName, TVUserEmail, TVUserPhone, TVUsername, frameLayout5,
          toggleProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
