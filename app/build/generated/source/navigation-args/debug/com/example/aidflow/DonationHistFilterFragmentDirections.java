package com.example.aidflow;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DonationHistFilterFragmentDirections {
  private DonationHistFilterFragmentDirections() {
  }

  @NonNull
  public static HistFiltDonate histFiltDonate() {
    return new HistFiltDonate();
  }

  public static class HistFiltDonate implements NavDirections {
    private final HashMap arguments = new HashMap();

    private HistFiltDonate() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public HistFiltDonate setHistFilt(boolean histFilt) {
      this.arguments.put("histFilt", histFilt);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("histFilt")) {
        boolean histFilt = (boolean) arguments.get("histFilt");
        __result.putBoolean("histFilt", histFilt);
      } else {
        __result.putBoolean("histFilt", false);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.histFiltDonate;
    }

    @SuppressWarnings("unchecked")
    public boolean getHistFilt() {
      return (boolean) arguments.get("histFilt");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      HistFiltDonate that = (HistFiltDonate) object;
      if (arguments.containsKey("histFilt") != that.arguments.containsKey("histFilt")) {
        return false;
      }
      if (getHistFilt() != that.getHistFilt()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getHistFilt() ? 1 : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "HistFiltDonate(actionId=" + getActionId() + "){"
          + "histFilt=" + getHistFilt()
          + "}";
    }
  }
}
