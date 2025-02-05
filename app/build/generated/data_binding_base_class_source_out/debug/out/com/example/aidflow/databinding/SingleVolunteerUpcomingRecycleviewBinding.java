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

public final class SingleVolunteerUpcomingRecycleviewBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView TVUpcomingContact;

  @NonNull
  public final TextView TVUpcomingDate;

  @NonNull
  public final TextView TVUpcomingLoc;

  @NonNull
  public final TextView TVUpcomingTitle;

  @NonNull
  public final TextView organizerUpcoming;

  @NonNull
  public final CardView upcomingCardView;

  private SingleVolunteerUpcomingRecycleviewBinding(@NonNull CardView rootView,
      @NonNull TextView TVUpcomingContact, @NonNull TextView TVUpcomingDate,
      @NonNull TextView TVUpcomingLoc, @NonNull TextView TVUpcomingTitle,
      @NonNull TextView organizerUpcoming, @NonNull CardView upcomingCardView) {
    this.rootView = rootView;
    this.TVUpcomingContact = TVUpcomingContact;
    this.TVUpcomingDate = TVUpcomingDate;
    this.TVUpcomingLoc = TVUpcomingLoc;
    this.TVUpcomingTitle = TVUpcomingTitle;
    this.organizerUpcoming = organizerUpcoming;
    this.upcomingCardView = upcomingCardView;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleVolunteerUpcomingRecycleviewBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleVolunteerUpcomingRecycleviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_volunteer_upcoming_recycleview, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleVolunteerUpcomingRecycleviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TVUpcomingContact;
      TextView TVUpcomingContact = ViewBindings.findChildViewById(rootView, id);
      if (TVUpcomingContact == null) {
        break missingId;
      }

      id = R.id.TVUpcomingDate;
      TextView TVUpcomingDate = ViewBindings.findChildViewById(rootView, id);
      if (TVUpcomingDate == null) {
        break missingId;
      }

      id = R.id.TVUpcomingLoc;
      TextView TVUpcomingLoc = ViewBindings.findChildViewById(rootView, id);
      if (TVUpcomingLoc == null) {
        break missingId;
      }

      id = R.id.TVUpcomingTitle;
      TextView TVUpcomingTitle = ViewBindings.findChildViewById(rootView, id);
      if (TVUpcomingTitle == null) {
        break missingId;
      }

      id = R.id.organizer_upcoming;
      TextView organizerUpcoming = ViewBindings.findChildViewById(rootView, id);
      if (organizerUpcoming == null) {
        break missingId;
      }

      CardView upcomingCardView = (CardView) rootView;

      return new SingleVolunteerUpcomingRecycleviewBinding((CardView) rootView, TVUpcomingContact,
          TVUpcomingDate, TVUpcomingLoc, TVUpcomingTitle, organizerUpcoming, upcomingCardView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
