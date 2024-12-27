// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public final class FragmentVolunteerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton BadgesButton;

  @NonNull
  public final MaterialButton UpcomingButton;

  @NonNull
  public final MaterialButton VolunteerButton;

  @NonNull
  public final Spinner districtSpinner;

  @NonNull
  public final FrameLayout fragmentButton;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout1;

  @NonNull
  public final LinearLayout selectLayout;

  @NonNull
  public final Spinner stateSpinner;

  private FragmentVolunteerBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton BadgesButton, @NonNull MaterialButton UpcomingButton,
      @NonNull MaterialButton VolunteerButton, @NonNull Spinner districtSpinner,
      @NonNull FrameLayout fragmentButton, @NonNull LinearLayout linearLayout,
      @NonNull LinearLayout linearLayout1, @NonNull LinearLayout selectLayout,
      @NonNull Spinner stateSpinner) {
    this.rootView = rootView;
    this.BadgesButton = BadgesButton;
    this.UpcomingButton = UpcomingButton;
    this.VolunteerButton = VolunteerButton;
    this.districtSpinner = districtSpinner;
    this.fragmentButton = fragmentButton;
    this.linearLayout = linearLayout;
    this.linearLayout1 = linearLayout1;
    this.selectLayout = selectLayout;
    this.stateSpinner = stateSpinner;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVolunteerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVolunteerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_volunteer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVolunteerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Badges_button;
      MaterialButton BadgesButton = ViewBindings.findChildViewById(rootView, id);
      if (BadgesButton == null) {
        break missingId;
      }

      id = R.id.Upcoming_button;
      MaterialButton UpcomingButton = ViewBindings.findChildViewById(rootView, id);
      if (UpcomingButton == null) {
        break missingId;
      }

      id = R.id.Volunteer_button;
      MaterialButton VolunteerButton = ViewBindings.findChildViewById(rootView, id);
      if (VolunteerButton == null) {
        break missingId;
      }

      id = R.id.district_spinner;
      Spinner districtSpinner = ViewBindings.findChildViewById(rootView, id);
      if (districtSpinner == null) {
        break missingId;
      }

      id = R.id.fragment_button;
      FrameLayout fragmentButton = ViewBindings.findChildViewById(rootView, id);
      if (fragmentButton == null) {
        break missingId;
      }

      id = R.id.linear_layout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linear_layout1;
      LinearLayout linearLayout1 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout1 == null) {
        break missingId;
      }

      id = R.id.select_layout;
      LinearLayout selectLayout = ViewBindings.findChildViewById(rootView, id);
      if (selectLayout == null) {
        break missingId;
      }

      id = R.id.state_spinner;
      Spinner stateSpinner = ViewBindings.findChildViewById(rootView, id);
      if (stateSpinner == null) {
        break missingId;
      }

      return new FragmentVolunteerBinding((ConstraintLayout) rootView, BadgesButton, UpcomingButton,
          VolunteerButton, districtSpinner, fragmentButton, linearLayout, linearLayout1,
          selectLayout, stateSpinner);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}