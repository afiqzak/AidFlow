// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentVolunteerProgBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView recyclerViewVolunteer;

  private FragmentVolunteerProgBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView recyclerViewVolunteer) {
    this.rootView = rootView;
    this.recyclerViewVolunteer = recyclerViewVolunteer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVolunteerProgBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVolunteerProgBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_volunteer_prog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVolunteerProgBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerView_volunteer;
      RecyclerView recyclerViewVolunteer = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewVolunteer == null) {
        break missingId;
      }

      return new FragmentVolunteerProgBinding((ConstraintLayout) rootView, recyclerViewVolunteer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
