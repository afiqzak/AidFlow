// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentVolunteerDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button BtnJoinCancel;

  @NonNull
  public final TextView TVEventContact;

  @NonNull
  public final TextView TVEventDate;

  @NonNull
  public final TextView TVEventDesc;

  @NonNull
  public final TextView TVEventLocation;

  @NonNull
  public final TextView TVEventTitle;

  @NonNull
  public final TextView TVPIC;

  @NonNull
  public final ImageView backIconVolunteer;

  @NonNull
  public final CardView volunteerCardView;

  private FragmentVolunteerDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button BtnJoinCancel, @NonNull TextView TVEventContact,
      @NonNull TextView TVEventDate, @NonNull TextView TVEventDesc,
      @NonNull TextView TVEventLocation, @NonNull TextView TVEventTitle, @NonNull TextView TVPIC,
      @NonNull ImageView backIconVolunteer, @NonNull CardView volunteerCardView) {
    this.rootView = rootView;
    this.BtnJoinCancel = BtnJoinCancel;
    this.TVEventContact = TVEventContact;
    this.TVEventDate = TVEventDate;
    this.TVEventDesc = TVEventDesc;
    this.TVEventLocation = TVEventLocation;
    this.TVEventTitle = TVEventTitle;
    this.TVPIC = TVPIC;
    this.backIconVolunteer = backIconVolunteer;
    this.volunteerCardView = volunteerCardView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVolunteerDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVolunteerDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_volunteer_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVolunteerDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BtnJoinCancel;
      Button BtnJoinCancel = ViewBindings.findChildViewById(rootView, id);
      if (BtnJoinCancel == null) {
        break missingId;
      }

      id = R.id.TVEventContact;
      TextView TVEventContact = ViewBindings.findChildViewById(rootView, id);
      if (TVEventContact == null) {
        break missingId;
      }

      id = R.id.TVEventDate;
      TextView TVEventDate = ViewBindings.findChildViewById(rootView, id);
      if (TVEventDate == null) {
        break missingId;
      }

      id = R.id.TVEventDesc;
      TextView TVEventDesc = ViewBindings.findChildViewById(rootView, id);
      if (TVEventDesc == null) {
        break missingId;
      }

      id = R.id.TVEventLocation;
      TextView TVEventLocation = ViewBindings.findChildViewById(rootView, id);
      if (TVEventLocation == null) {
        break missingId;
      }

      id = R.id.TVEventTitle;
      TextView TVEventTitle = ViewBindings.findChildViewById(rootView, id);
      if (TVEventTitle == null) {
        break missingId;
      }

      id = R.id.TVPIC;
      TextView TVPIC = ViewBindings.findChildViewById(rootView, id);
      if (TVPIC == null) {
        break missingId;
      }

      id = R.id.back_icon_volunteer;
      ImageView backIconVolunteer = ViewBindings.findChildViewById(rootView, id);
      if (backIconVolunteer == null) {
        break missingId;
      }

      id = R.id.volunteer_cardView;
      CardView volunteerCardView = ViewBindings.findChildViewById(rootView, id);
      if (volunteerCardView == null) {
        break missingId;
      }

      return new FragmentVolunteerDetailsBinding((ConstraintLayout) rootView, BtnJoinCancel,
          TVEventContact, TVEventDate, TVEventDesc, TVEventLocation, TVEventTitle, TVPIC,
          backIconVolunteer, volunteerCardView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
