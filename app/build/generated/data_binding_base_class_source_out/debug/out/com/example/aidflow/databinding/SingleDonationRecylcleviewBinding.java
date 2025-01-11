// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

public final class SingleDonationRecylcleviewBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ProgressBar PBDonation;

  @NonNull
  public final TextView TVDonationCategory;

  @NonNull
  public final TextView TVDonationDueDate;

  @NonNull
  public final TextView TVDonationProgress;

  @NonNull
  public final TextView TVDonationTitle;

  @NonNull
  public final CardView donationCardView;

  @NonNull
  public final View urgencyIndicator;

  private SingleDonationRecylcleviewBinding(@NonNull CardView rootView,
      @NonNull ProgressBar PBDonation, @NonNull TextView TVDonationCategory,
      @NonNull TextView TVDonationDueDate, @NonNull TextView TVDonationProgress,
      @NonNull TextView TVDonationTitle, @NonNull CardView donationCardView,
      @NonNull View urgencyIndicator) {
    this.rootView = rootView;
    this.PBDonation = PBDonation;
    this.TVDonationCategory = TVDonationCategory;
    this.TVDonationDueDate = TVDonationDueDate;
    this.TVDonationProgress = TVDonationProgress;
    this.TVDonationTitle = TVDonationTitle;
    this.donationCardView = donationCardView;
    this.urgencyIndicator = urgencyIndicator;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleDonationRecylcleviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleDonationRecylcleviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_donation_recylcleview, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleDonationRecylcleviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PBDonation;
      ProgressBar PBDonation = ViewBindings.findChildViewById(rootView, id);
      if (PBDonation == null) {
        break missingId;
      }

      id = R.id.TVDonationCategory;
      TextView TVDonationCategory = ViewBindings.findChildViewById(rootView, id);
      if (TVDonationCategory == null) {
        break missingId;
      }

      id = R.id.TVDonationDueDate;
      TextView TVDonationDueDate = ViewBindings.findChildViewById(rootView, id);
      if (TVDonationDueDate == null) {
        break missingId;
      }

      id = R.id.TVDonationProgress;
      TextView TVDonationProgress = ViewBindings.findChildViewById(rootView, id);
      if (TVDonationProgress == null) {
        break missingId;
      }

      id = R.id.TVDonationTitle;
      TextView TVDonationTitle = ViewBindings.findChildViewById(rootView, id);
      if (TVDonationTitle == null) {
        break missingId;
      }

      CardView donationCardView = (CardView) rootView;

      id = R.id.urgencyIndicator;
      View urgencyIndicator = ViewBindings.findChildViewById(rootView, id);
      if (urgencyIndicator == null) {
        break missingId;
      }

      return new SingleDonationRecylcleviewBinding((CardView) rootView, PBDonation,
          TVDonationCategory, TVDonationDueDate, TVDonationProgress, TVDonationTitle,
          donationCardView, urgencyIndicator);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
