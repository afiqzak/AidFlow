package com.example.aidflow;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DonationFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DonationFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private DonationFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DonationFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DonationFragmentArgs __result = new DonationFragmentArgs();
    bundle.setClassLoader(DonationFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("histFilt")) {
      boolean histFilt;
      histFilt = bundle.getBoolean("histFilt");
      __result.arguments.put("histFilt", histFilt);
    } else {
      __result.arguments.put("histFilt", false);
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DonationFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    DonationFragmentArgs __result = new DonationFragmentArgs();
    if (savedStateHandle.contains("histFilt")) {
      boolean histFilt;
      histFilt = savedStateHandle.get("histFilt");
      __result.arguments.put("histFilt", histFilt);
    } else {
      __result.arguments.put("histFilt", false);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public boolean getHistFilt() {
    return (boolean) arguments.get("histFilt");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("histFilt")) {
      boolean histFilt = (boolean) arguments.get("histFilt");
      __result.putBoolean("histFilt", histFilt);
    } else {
      __result.putBoolean("histFilt", false);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("histFilt")) {
      boolean histFilt = (boolean) arguments.get("histFilt");
      __result.set("histFilt", histFilt);
    } else {
      __result.set("histFilt", false);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DonationFragmentArgs that = (DonationFragmentArgs) object;
    if (arguments.containsKey("histFilt") != that.arguments.containsKey("histFilt")) {
      return false;
    }
    if (getHistFilt() != that.getHistFilt()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getHistFilt() ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DonationFragmentArgs{"
        + "histFilt=" + getHistFilt()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull DonationFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DonationFragmentArgs build() {
      DonationFragmentArgs result = new DonationFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setHistFilt(boolean histFilt) {
      this.arguments.put("histFilt", histFilt);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public boolean getHistFilt() {
      return (boolean) arguments.get("histFilt");
    }
  }
}
