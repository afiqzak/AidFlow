package com.example.aidflow;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class NewsMainPageFragmentDirections {
  private NewsMainPageFragmentDirections() {
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
}
