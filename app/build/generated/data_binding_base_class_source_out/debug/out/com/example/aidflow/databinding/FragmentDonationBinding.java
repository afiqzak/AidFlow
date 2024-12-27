// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDonationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button DonationButton;

  @NonNull
  public final FrameLayout FCVDonation;

  @NonNull
  public final Button HistoryButton;

  @NonNull
  public final LinearLayout LLDonationTitle;

  @NonNull
  public final TextView donationName;

  @NonNull
  public final FloatingActionButton fabDonatefilter;

  @NonNull
  public final FloatingActionButton fabHistory;

  @NonNull
  public final LinearLayout linearLayout;

  private FragmentDonationBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button DonationButton, @NonNull FrameLayout FCVDonation,
      @NonNull Button HistoryButton, @NonNull LinearLayout LLDonationTitle,
      @NonNull TextView donationName, @NonNull FloatingActionButton fabDonatefilter,
      @NonNull FloatingActionButton fabHistory, @NonNull LinearLayout linearLayout) {
    this.rootView = rootView;
    this.DonationButton = DonationButton;
    this.FCVDonation = FCVDonation;
    this.HistoryButton = HistoryButton;
    this.LLDonationTitle = LLDonationTitle;
    this.donationName = donationName;
    this.fabDonatefilter = fabDonatefilter;
    this.fabHistory = fabHistory;
    this.linearLayout = linearLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDonationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDonationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_donation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDonationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Donation_button;
      Button DonationButton = ViewBindings.findChildViewById(rootView, id);
      if (DonationButton == null) {
        break missingId;
      }

      id = R.id.FCVDonation;
      FrameLayout FCVDonation = ViewBindings.findChildViewById(rootView, id);
      if (FCVDonation == null) {
        break missingId;
      }

      id = R.id.History_button;
      Button HistoryButton = ViewBindings.findChildViewById(rootView, id);
      if (HistoryButton == null) {
        break missingId;
      }

      id = R.id.LLDonationTitle;
      LinearLayout LLDonationTitle = ViewBindings.findChildViewById(rootView, id);
      if (LLDonationTitle == null) {
        break missingId;
      }

      id = R.id.donationName;
      TextView donationName = ViewBindings.findChildViewById(rootView, id);
      if (donationName == null) {
        break missingId;
      }

      id = R.id.fab_donatefilter;
      FloatingActionButton fabDonatefilter = ViewBindings.findChildViewById(rootView, id);
      if (fabDonatefilter == null) {
        break missingId;
      }

      id = R.id.fab_history;
      FloatingActionButton fabHistory = ViewBindings.findChildViewById(rootView, id);
      if (fabHistory == null) {
        break missingId;
      }

      id = R.id.linear_layout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      return new FragmentDonationBinding((ConstraintLayout) rootView, DonationButton, FCVDonation,
          HistoryButton, LLDonationTitle, donationName, fabDonatefilter, fabHistory, linearLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}