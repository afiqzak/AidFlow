// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button BtnSignin;

  @NonNull
  public final EditText ETEmail;

  @NonNull
  public final TextInputEditText TIETPassword;

  @NonNull
  public final TextInputLayout TILPassword;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView3;

  private FragmentLoginBinding(@NonNull LinearLayout rootView, @NonNull Button BtnSignin,
      @NonNull EditText ETEmail, @NonNull TextInputEditText TIETPassword,
      @NonNull TextInputLayout TILPassword, @NonNull TextView textView,
      @NonNull TextView textView3) {
    this.rootView = rootView;
    this.BtnSignin = BtnSignin;
    this.ETEmail = ETEmail;
    this.TIETPassword = TIETPassword;
    this.TILPassword = TILPassword;
    this.textView = textView;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BtnSignin;
      Button BtnSignin = ViewBindings.findChildViewById(rootView, id);
      if (BtnSignin == null) {
        break missingId;
      }

      id = R.id.ETEmail;
      EditText ETEmail = ViewBindings.findChildViewById(rootView, id);
      if (ETEmail == null) {
        break missingId;
      }

      id = R.id.TIETPassword;
      TextInputEditText TIETPassword = ViewBindings.findChildViewById(rootView, id);
      if (TIETPassword == null) {
        break missingId;
      }

      id = R.id.TILPassword;
      TextInputLayout TILPassword = ViewBindings.findChildViewById(rootView, id);
      if (TILPassword == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentLoginBinding((LinearLayout) rootView, BtnSignin, ETEmail, TIETPassword,
          TILPassword, textView, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
