package com.example.aidflow;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class NewsFullPageArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private NewsFullPageArgs() {
  }

  @SuppressWarnings("unchecked")
  private NewsFullPageArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static NewsFullPageArgs fromBundle(@NonNull Bundle bundle) {
    NewsFullPageArgs __result = new NewsFullPageArgs();
    bundle.setClassLoader(NewsFullPageArgs.class.getClassLoader());
    if (bundle.containsKey("newsTitle")) {
      String newsTitle;
      newsTitle = bundle.getString("newsTitle");
      if (newsTitle == null) {
        throw new IllegalArgumentException("Argument \"newsTitle\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("newsTitle", newsTitle);
    } else {
      throw new IllegalArgumentException("Required argument \"newsTitle\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("newsDesc")) {
      String newsDesc;
      newsDesc = bundle.getString("newsDesc");
      if (newsDesc == null) {
        throw new IllegalArgumentException("Argument \"newsDesc\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("newsDesc", newsDesc);
    } else {
      throw new IllegalArgumentException("Required argument \"newsDesc\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static NewsFullPageArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
    NewsFullPageArgs __result = new NewsFullPageArgs();
    if (savedStateHandle.contains("newsTitle")) {
      String newsTitle;
      newsTitle = savedStateHandle.get("newsTitle");
      if (newsTitle == null) {
        throw new IllegalArgumentException("Argument \"newsTitle\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("newsTitle", newsTitle);
    } else {
      throw new IllegalArgumentException("Required argument \"newsTitle\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("newsDesc")) {
      String newsDesc;
      newsDesc = savedStateHandle.get("newsDesc");
      if (newsDesc == null) {
        throw new IllegalArgumentException("Argument \"newsDesc\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("newsDesc", newsDesc);
    } else {
      throw new IllegalArgumentException("Required argument \"newsDesc\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getNewsTitle() {
    return (String) arguments.get("newsTitle");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getNewsDesc() {
    return (String) arguments.get("newsDesc");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("newsTitle")) {
      String newsTitle = (String) arguments.get("newsTitle");
      __result.putString("newsTitle", newsTitle);
    }
    if (arguments.containsKey("newsDesc")) {
      String newsDesc = (String) arguments.get("newsDesc");
      __result.putString("newsDesc", newsDesc);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("newsTitle")) {
      String newsTitle = (String) arguments.get("newsTitle");
      __result.set("newsTitle", newsTitle);
    }
    if (arguments.containsKey("newsDesc")) {
      String newsDesc = (String) arguments.get("newsDesc");
      __result.set("newsDesc", newsDesc);
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
    NewsFullPageArgs that = (NewsFullPageArgs) object;
    if (arguments.containsKey("newsTitle") != that.arguments.containsKey("newsTitle")) {
      return false;
    }
    if (getNewsTitle() != null ? !getNewsTitle().equals(that.getNewsTitle()) : that.getNewsTitle() != null) {
      return false;
    }
    if (arguments.containsKey("newsDesc") != that.arguments.containsKey("newsDesc")) {
      return false;
    }
    if (getNewsDesc() != null ? !getNewsDesc().equals(that.getNewsDesc()) : that.getNewsDesc() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getNewsTitle() != null ? getNewsTitle().hashCode() : 0);
    result = 31 * result + (getNewsDesc() != null ? getNewsDesc().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "NewsFullPageArgs{"
        + "newsTitle=" + getNewsTitle()
        + ", newsDesc=" + getNewsDesc()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull NewsFullPageArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String newsTitle, @NonNull String newsDesc) {
      if (newsTitle == null) {
        throw new IllegalArgumentException("Argument \"newsTitle\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsTitle", newsTitle);
      if (newsDesc == null) {
        throw new IllegalArgumentException("Argument \"newsDesc\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsDesc", newsDesc);
    }

    @NonNull
    public NewsFullPageArgs build() {
      NewsFullPageArgs result = new NewsFullPageArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setNewsTitle(@NonNull String newsTitle) {
      if (newsTitle == null) {
        throw new IllegalArgumentException("Argument \"newsTitle\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsTitle", newsTitle);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setNewsDesc(@NonNull String newsDesc) {
      if (newsDesc == null) {
        throw new IllegalArgumentException("Argument \"newsDesc\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsDesc", newsDesc);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getNewsTitle() {
      return (String) arguments.get("newsTitle");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getNewsDesc() {
      return (String) arguments.get("newsDesc");
    }
  }
}
