// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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

public final class FragmentWaterRatingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView IVRating;

  @NonNull
  public final LinearLayout RLEffective;

  @NonNull
  public final LinearLayout RLService;

  @NonNull
  public final LinearLayout RLTime;

  @NonNull
  public final SeekBar SBEffective;

  @NonNull
  public final SeekBar SBService;

  @NonNull
  public final SeekBar SBTime;

  @NonNull
  public final TextView TVEffective;

  @NonNull
  public final TextView TVService;

  @NonNull
  public final TextView TVTime;

  @NonNull
  public final MaterialButton btnBack2;

  @NonNull
  public final Button btnSubmit;

  private FragmentWaterRatingBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView IVRating, @NonNull LinearLayout RLEffective,
      @NonNull LinearLayout RLService, @NonNull LinearLayout RLTime, @NonNull SeekBar SBEffective,
      @NonNull SeekBar SBService, @NonNull SeekBar SBTime, @NonNull TextView TVEffective,
      @NonNull TextView TVService, @NonNull TextView TVTime, @NonNull MaterialButton btnBack2,
      @NonNull Button btnSubmit) {
    this.rootView = rootView;
    this.IVRating = IVRating;
    this.RLEffective = RLEffective;
    this.RLService = RLService;
    this.RLTime = RLTime;
    this.SBEffective = SBEffective;
    this.SBService = SBService;
    this.SBTime = SBTime;
    this.TVEffective = TVEffective;
    this.TVService = TVService;
    this.TVTime = TVTime;
    this.btnBack2 = btnBack2;
    this.btnSubmit = btnSubmit;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentWaterRatingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentWaterRatingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_water_rating, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentWaterRatingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.IVRating;
      ImageView IVRating = ViewBindings.findChildViewById(rootView, id);
      if (IVRating == null) {
        break missingId;
      }

      id = R.id.RLEffective;
      LinearLayout RLEffective = ViewBindings.findChildViewById(rootView, id);
      if (RLEffective == null) {
        break missingId;
      }

      id = R.id.RLService;
      LinearLayout RLService = ViewBindings.findChildViewById(rootView, id);
      if (RLService == null) {
        break missingId;
      }

      id = R.id.RLTime;
      LinearLayout RLTime = ViewBindings.findChildViewById(rootView, id);
      if (RLTime == null) {
        break missingId;
      }

      id = R.id.SBEffective;
      SeekBar SBEffective = ViewBindings.findChildViewById(rootView, id);
      if (SBEffective == null) {
        break missingId;
      }

      id = R.id.SBService;
      SeekBar SBService = ViewBindings.findChildViewById(rootView, id);
      if (SBService == null) {
        break missingId;
      }

      id = R.id.SBTime;
      SeekBar SBTime = ViewBindings.findChildViewById(rootView, id);
      if (SBTime == null) {
        break missingId;
      }

      id = R.id.TVEffective;
      TextView TVEffective = ViewBindings.findChildViewById(rootView, id);
      if (TVEffective == null) {
        break missingId;
      }

      id = R.id.TVService;
      TextView TVService = ViewBindings.findChildViewById(rootView, id);
      if (TVService == null) {
        break missingId;
      }

      id = R.id.TVTime;
      TextView TVTime = ViewBindings.findChildViewById(rootView, id);
      if (TVTime == null) {
        break missingId;
      }

      id = R.id.btnBack2;
      MaterialButton btnBack2 = ViewBindings.findChildViewById(rootView, id);
      if (btnBack2 == null) {
        break missingId;
      }

      id = R.id.btnSubmit;
      Button btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
        break missingId;
      }

      return new FragmentWaterRatingBinding((ConstraintLayout) rootView, IVRating, RLEffective,
          RLService, RLTime, SBEffective, SBService, SBTime, TVEffective, TVService, TVTime,
          btnBack2, btnSubmit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
