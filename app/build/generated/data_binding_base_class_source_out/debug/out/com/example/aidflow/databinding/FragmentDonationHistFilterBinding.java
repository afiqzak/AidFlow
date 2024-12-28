// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDonationHistFilterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button applyFilterButton;

  @NonNull
  public final ImageView closeIcon;

  @NonNull
  public final RadioGroup durationRadioGroup;

  @NonNull
  public final RadioButton rb1Year;

  @NonNull
  public final RadioButton rb30Days;

  @NonNull
  public final RadioButton rb60Days;

  @NonNull
  public final RadioButton rb90Days;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  private FragmentDonationHistFilterBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button applyFilterButton, @NonNull ImageView closeIcon,
      @NonNull RadioGroup durationRadioGroup, @NonNull RadioButton rb1Year,
      @NonNull RadioButton rb30Days, @NonNull RadioButton rb60Days, @NonNull RadioButton rb90Days,
      @NonNull TextView subtitle, @NonNull TextView title) {
    this.rootView = rootView;
    this.applyFilterButton = applyFilterButton;
    this.closeIcon = closeIcon;
    this.durationRadioGroup = durationRadioGroup;
    this.rb1Year = rb1Year;
    this.rb30Days = rb30Days;
    this.rb60Days = rb60Days;
    this.rb90Days = rb90Days;
    this.subtitle = subtitle;
    this.title = title;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDonationHistFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDonationHistFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_donation_hist_filter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDonationHistFilterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.apply_filter_button;
      Button applyFilterButton = ViewBindings.findChildViewById(rootView, id);
      if (applyFilterButton == null) {
        break missingId;
      }

      id = R.id.close_icon;
      ImageView closeIcon = ViewBindings.findChildViewById(rootView, id);
      if (closeIcon == null) {
        break missingId;
      }

      id = R.id.duration_radio_group;
      RadioGroup durationRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (durationRadioGroup == null) {
        break missingId;
      }

      id = R.id.rb_1_year;
      RadioButton rb1Year = ViewBindings.findChildViewById(rootView, id);
      if (rb1Year == null) {
        break missingId;
      }

      id = R.id.rb_30_days;
      RadioButton rb30Days = ViewBindings.findChildViewById(rootView, id);
      if (rb30Days == null) {
        break missingId;
      }

      id = R.id.rb_60_days;
      RadioButton rb60Days = ViewBindings.findChildViewById(rootView, id);
      if (rb60Days == null) {
        break missingId;
      }

      id = R.id.rb_90_days;
      RadioButton rb90Days = ViewBindings.findChildViewById(rootView, id);
      if (rb90Days == null) {
        break missingId;
      }

      id = R.id.subtitle;
      TextView subtitle = ViewBindings.findChildViewById(rootView, id);
      if (subtitle == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new FragmentDonationHistFilterBinding((ConstraintLayout) rootView, applyFilterButton,
          closeIcon, durationRadioGroup, rb1Year, rb30Days, rb60Days, rb90Days, subtitle, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
