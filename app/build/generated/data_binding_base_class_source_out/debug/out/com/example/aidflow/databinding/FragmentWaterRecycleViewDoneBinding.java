// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentWaterRecycleViewDoneBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RecyclerView recyclerViewDone;

  private FragmentWaterRecycleViewDoneBinding(@NonNull FrameLayout rootView,
      @NonNull RecyclerView recyclerViewDone) {
    this.rootView = rootView;
    this.recyclerViewDone = recyclerViewDone;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentWaterRecycleViewDoneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentWaterRecycleViewDoneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_water_recycle_view_done, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentWaterRecycleViewDoneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerViewDone;
      RecyclerView recyclerViewDone = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewDone == null) {
        break missingId;
      }

      return new FragmentWaterRecycleViewDoneBinding((FrameLayout) rootView, recyclerViewDone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
