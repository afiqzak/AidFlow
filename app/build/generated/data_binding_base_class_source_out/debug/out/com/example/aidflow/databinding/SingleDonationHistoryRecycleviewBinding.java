// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class SingleDonationHistoryRecycleviewBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView PICHistory;

  @NonNull
  public final TextView amountHistory;

  @NonNull
  public final TextView categoryHistory;

  @NonNull
  public final TextView donationNameHistory;

  @NonNull
  public final TextView paymentMethodHistory;

  @NonNull
  public final View urgencyIndicator;

  private SingleDonationHistoryRecycleviewBinding(@NonNull CardView rootView,
      @NonNull TextView PICHistory, @NonNull TextView amountHistory,
      @NonNull TextView categoryHistory, @NonNull TextView donationNameHistory,
      @NonNull TextView paymentMethodHistory, @NonNull View urgencyIndicator) {
    this.rootView = rootView;
    this.PICHistory = PICHistory;
    this.amountHistory = amountHistory;
    this.categoryHistory = categoryHistory;
    this.donationNameHistory = donationNameHistory;
    this.paymentMethodHistory = paymentMethodHistory;
    this.urgencyIndicator = urgencyIndicator;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleDonationHistoryRecycleviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleDonationHistoryRecycleviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_donation_history_recycleview, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleDonationHistoryRecycleviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PIC_history;
      TextView PICHistory = ViewBindings.findChildViewById(rootView, id);
      if (PICHistory == null) {
        break missingId;
      }

      id = R.id.amount_history;
      TextView amountHistory = ViewBindings.findChildViewById(rootView, id);
      if (amountHistory == null) {
        break missingId;
      }

      id = R.id.category_history;
      TextView categoryHistory = ViewBindings.findChildViewById(rootView, id);
      if (categoryHistory == null) {
        break missingId;
      }

      id = R.id.donationName_history;
      TextView donationNameHistory = ViewBindings.findChildViewById(rootView, id);
      if (donationNameHistory == null) {
        break missingId;
      }

      id = R.id.paymentMethod_history;
      TextView paymentMethodHistory = ViewBindings.findChildViewById(rootView, id);
      if (paymentMethodHistory == null) {
        break missingId;
      }

      id = R.id.urgencyIndicator;
      View urgencyIndicator = ViewBindings.findChildViewById(rootView, id);
      if (urgencyIndicator == null) {
        break missingId;
      }

      return new SingleDonationHistoryRecycleviewBinding((CardView) rootView, PICHistory,
          amountHistory, categoryHistory, donationNameHistory, paymentMethodHistory,
          urgencyIndicator);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
