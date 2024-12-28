package com.example.aidflow;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class WaterFragmentDirections {
  private WaterFragmentDirections() {
  }

  @NonNull
  public static NavDirections toRating() {
    return new ActionOnlyNavDirections(R.id.toRating);
  }

  @NonNull
  public static NavDirections toReport() {
    return new ActionOnlyNavDirections(R.id.toReport);
  }
}
