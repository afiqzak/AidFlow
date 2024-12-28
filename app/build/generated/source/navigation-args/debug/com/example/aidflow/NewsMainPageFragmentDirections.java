package com.example.aidflow;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class NewsMainPageFragmentDirections {
  private NewsMainPageFragmentDirections() {
  }

  @NonNull
  public static ActionNewsMainPageFragmentToDestNewsFull actionNewsMainPageFragmentToDestNewsFull(
      @NonNull String newsTitle, @NonNull String newsDesc) {
    return new ActionNewsMainPageFragmentToDestNewsFull(newsTitle, newsDesc);
  }

  @NonNull
  public static NavDirections actionNewsMainPageFragmentToStoryFullFragment() {
    return new ActionOnlyNavDirections(R.id.action_newsMainPageFragment_to_storyFullFragment);
  }

  @NonNull
  public static NavDirections actionNewsMainPageFragmentToProjectsFullPage() {
    return new ActionOnlyNavDirections(R.id.action_newsMainPageFragment_to_projectsFullPage);
  }

  @NonNull
  public static NavDirections actionNewsMainPageFragmentToMainActivity() {
    return new ActionOnlyNavDirections(R.id.action_newsMainPageFragment_to_mainActivity);
  }

  @NonNull
  public static NavDirections actionNewsMainPageFragmentToCamFragment() {
    return new ActionOnlyNavDirections(R.id.action_newsMainPageFragment_to_camFragment);
  }

  public static class ActionNewsMainPageFragmentToDestNewsFull implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionNewsMainPageFragmentToDestNewsFull(@NonNull String newsTitle,
        @NonNull String newsDesc) {
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
    @SuppressWarnings("unchecked")
    public ActionNewsMainPageFragmentToDestNewsFull setNewsTitle(@NonNull String newsTitle) {
      if (newsTitle == null) {
        throw new IllegalArgumentException("Argument \"newsTitle\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsTitle", newsTitle);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionNewsMainPageFragmentToDestNewsFull setNewsDesc(@NonNull String newsDesc) {
      if (newsDesc == null) {
        throw new IllegalArgumentException("Argument \"newsDesc\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("newsDesc", newsDesc);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_newsMainPageFragment_to_destNewsFull;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionNewsMainPageFragmentToDestNewsFull that = (ActionNewsMainPageFragmentToDestNewsFull) object;
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
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getNewsTitle() != null ? getNewsTitle().hashCode() : 0);
      result = 31 * result + (getNewsDesc() != null ? getNewsDesc().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionNewsMainPageFragmentToDestNewsFull(actionId=" + getActionId() + "){"
          + "newsTitle=" + getNewsTitle()
          + ", newsDesc=" + getNewsDesc()
          + "}";
    }
  }
}
