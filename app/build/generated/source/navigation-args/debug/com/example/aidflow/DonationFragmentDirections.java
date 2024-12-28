package com.example.aidflow;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class DonationFragmentDirections {
  private DonationFragmentDirections() {
  }

  @NonNull
  public static NavDirections toFrom() {
    return new ActionOnlyNavDirections(R.id.toFrom);
  }
}
