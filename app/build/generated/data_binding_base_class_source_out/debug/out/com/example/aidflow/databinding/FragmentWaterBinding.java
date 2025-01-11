// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentWaterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RelativeLayout PageFragment;

  @NonNull
  public final TextView TVNoti;

  @NonNull
  public final RadioButton doneProjects;

  @NonNull
  public final FragmentContainerView fragmentContainerView;

  @NonNull
  public final TextView notiTitle;

  @NonNull
  public final RadioButton pendingProjects;

  @NonNull
  public final Button reportbutton;

  @NonNull
  public final RadioGroup toggle;

  @NonNull
  public final ImageView waterQualityImage;

  private FragmentWaterBinding(@NonNull ConstraintLayout rootView,
      @NonNull RelativeLayout PageFragment, @NonNull TextView TVNoti,
      @NonNull RadioButton doneProjects, @NonNull FragmentContainerView fragmentContainerView,
      @NonNull TextView notiTitle, @NonNull RadioButton pendingProjects,
      @NonNull Button reportbutton, @NonNull RadioGroup toggle,
      @NonNull ImageView waterQualityImage) {
    this.rootView = rootView;
    this.PageFragment = PageFragment;
    this.TVNoti = TVNoti;
    this.doneProjects = doneProjects;
    this.fragmentContainerView = fragmentContainerView;
    this.notiTitle = notiTitle;
    this.pendingProjects = pendingProjects;
    this.reportbutton = reportbutton;
    this.toggle = toggle;
    this.waterQualityImage = waterQualityImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentWaterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentWaterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_water, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentWaterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PageFragment;
      RelativeLayout PageFragment = ViewBindings.findChildViewById(rootView, id);
      if (PageFragment == null) {
        break missingId;
      }

      id = R.id.TVNoti;
      TextView TVNoti = ViewBindings.findChildViewById(rootView, id);
      if (TVNoti == null) {
        break missingId;
      }

      id = R.id.done_projects;
      RadioButton doneProjects = ViewBindings.findChildViewById(rootView, id);
      if (doneProjects == null) {
        break missingId;
      }

      id = R.id.fragmentContainerView;
      FragmentContainerView fragmentContainerView = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainerView == null) {
        break missingId;
      }

      id = R.id.noti_title;
      TextView notiTitle = ViewBindings.findChildViewById(rootView, id);
      if (notiTitle == null) {
        break missingId;
      }

      id = R.id.pending_projects;
      RadioButton pendingProjects = ViewBindings.findChildViewById(rootView, id);
      if (pendingProjects == null) {
        break missingId;
      }

      id = R.id.reportbutton;
      Button reportbutton = ViewBindings.findChildViewById(rootView, id);
      if (reportbutton == null) {
        break missingId;
      }

      id = R.id.toggle;
      RadioGroup toggle = ViewBindings.findChildViewById(rootView, id);
      if (toggle == null) {
        break missingId;
      }

      id = R.id.water_quality_image;
      ImageView waterQualityImage = ViewBindings.findChildViewById(rootView, id);
      if (waterQualityImage == null) {
        break missingId;
      }

      return new FragmentWaterBinding((ConstraintLayout) rootView, PageFragment, TVNoti,
          doneProjects, fragmentContainerView, notiTitle, pendingProjects, reportbutton, toggle,
          waterQualityImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
