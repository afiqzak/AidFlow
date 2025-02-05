// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileOverviewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView IVMoney;

  @NonNull
  public final ImageView IVReport;

  @NonNull
  public final ImageView IVTime;

  @NonNull
  public final TextView TVAmounrDonate;

  @NonNull
  public final TextView TVAmounrReport;

  @NonNull
  public final TextView TVAmounrTime;

  @NonNull
  public final TextView TVTotDonate;

  @NonNull
  public final TextView TVTotReport;

  @NonNull
  public final TextView TVTotTime;

  @NonNull
  public final RelativeLayout relativeLayout2;

  @NonNull
  public final RelativeLayout relativeLayout3;

  private FragmentProfileOverviewBinding(@NonNull LinearLayout rootView, @NonNull ImageView IVMoney,
      @NonNull ImageView IVReport, @NonNull ImageView IVTime, @NonNull TextView TVAmounrDonate,
      @NonNull TextView TVAmounrReport, @NonNull TextView TVAmounrTime,
      @NonNull TextView TVTotDonate, @NonNull TextView TVTotReport, @NonNull TextView TVTotTime,
      @NonNull RelativeLayout relativeLayout2, @NonNull RelativeLayout relativeLayout3) {
    this.rootView = rootView;
    this.IVMoney = IVMoney;
    this.IVReport = IVReport;
    this.IVTime = IVTime;
    this.TVAmounrDonate = TVAmounrDonate;
    this.TVAmounrReport = TVAmounrReport;
    this.TVAmounrTime = TVAmounrTime;
    this.TVTotDonate = TVTotDonate;
    this.TVTotReport = TVTotReport;
    this.TVTotTime = TVTotTime;
    this.relativeLayout2 = relativeLayout2;
    this.relativeLayout3 = relativeLayout3;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileOverviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileOverviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile_overview, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileOverviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.IVMoney;
      ImageView IVMoney = ViewBindings.findChildViewById(rootView, id);
      if (IVMoney == null) {
        break missingId;
      }

      id = R.id.IVReport;
      ImageView IVReport = ViewBindings.findChildViewById(rootView, id);
      if (IVReport == null) {
        break missingId;
      }

      id = R.id.IVTime;
      ImageView IVTime = ViewBindings.findChildViewById(rootView, id);
      if (IVTime == null) {
        break missingId;
      }

      id = R.id.TVAmounrDonate;
      TextView TVAmounrDonate = ViewBindings.findChildViewById(rootView, id);
      if (TVAmounrDonate == null) {
        break missingId;
      }

      id = R.id.TVAmounrReport;
      TextView TVAmounrReport = ViewBindings.findChildViewById(rootView, id);
      if (TVAmounrReport == null) {
        break missingId;
      }

      id = R.id.TVAmounrTime;
      TextView TVAmounrTime = ViewBindings.findChildViewById(rootView, id);
      if (TVAmounrTime == null) {
        break missingId;
      }

      id = R.id.TVTotDonate;
      TextView TVTotDonate = ViewBindings.findChildViewById(rootView, id);
      if (TVTotDonate == null) {
        break missingId;
      }

      id = R.id.TVTotReport;
      TextView TVTotReport = ViewBindings.findChildViewById(rootView, id);
      if (TVTotReport == null) {
        break missingId;
      }

      id = R.id.TVTotTime;
      TextView TVTotTime = ViewBindings.findChildViewById(rootView, id);
      if (TVTotTime == null) {
        break missingId;
      }

      id = R.id.relativeLayout2;
      RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout2 == null) {
        break missingId;
      }

      id = R.id.relativeLayout3;
      RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout3 == null) {
        break missingId;
      }

      return new FragmentProfileOverviewBinding((LinearLayout) rootView, IVMoney, IVReport, IVTime,
          TVAmounrDonate, TVAmounrReport, TVAmounrTime, TVTotDonate, TVTotReport, TVTotTime,
          relativeLayout2, relativeLayout3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
