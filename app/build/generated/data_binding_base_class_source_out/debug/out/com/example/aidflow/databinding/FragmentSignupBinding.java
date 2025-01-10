// Generated by view binder compiler. Do not edit!
package com.example.aidflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aidflow.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSignupBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button BtnSignup;

  @NonNull
  public final EditText ETEmail;

  @NonNull
  public final EditText ETFirstName;

  @NonNull
  public final EditText ETLastName;

  @NonNull
  public final EditText ETPhone;

  @NonNull
  public final EditText ETUsername;

  @NonNull
  public final ShapeableImageView IVProfile;

  @NonNull
  public final TextView IVUsername;

  @NonNull
  public final TextInputEditText TIETConfirmPass;

  @NonNull
  public final TextInputEditText TIETPassword;

  @NonNull
  public final TextInputLayout TILConfirmPass;

  @NonNull
  public final TextInputLayout TILPassword;

  @NonNull
  public final TextView TVConfirmPass;

  @NonNull
  public final TextView TVEmail;

  @NonNull
  public final TextView TVFirstName;

  @NonNull
  public final TextView TVLastName;

  @NonNull
  public final TextView TVPassRequi;

  @NonNull
  public final TextView TVPassword;

  @NonNull
  public final TextView TVPhone;

  @NonNull
  public final RelativeLayout frameLayout4;

  @NonNull
  public final ShapeableImageView imageButton;

  private FragmentSignupBinding(@NonNull RelativeLayout rootView, @NonNull Button BtnSignup,
      @NonNull EditText ETEmail, @NonNull EditText ETFirstName, @NonNull EditText ETLastName,
      @NonNull EditText ETPhone, @NonNull EditText ETUsername,
      @NonNull ShapeableImageView IVProfile, @NonNull TextView IVUsername,
      @NonNull TextInputEditText TIETConfirmPass, @NonNull TextInputEditText TIETPassword,
      @NonNull TextInputLayout TILConfirmPass, @NonNull TextInputLayout TILPassword,
      @NonNull TextView TVConfirmPass, @NonNull TextView TVEmail, @NonNull TextView TVFirstName,
      @NonNull TextView TVLastName, @NonNull TextView TVPassRequi, @NonNull TextView TVPassword,
      @NonNull TextView TVPhone, @NonNull RelativeLayout frameLayout4,
      @NonNull ShapeableImageView imageButton) {
    this.rootView = rootView;
    this.BtnSignup = BtnSignup;
    this.ETEmail = ETEmail;
    this.ETFirstName = ETFirstName;
    this.ETLastName = ETLastName;
    this.ETPhone = ETPhone;
    this.ETUsername = ETUsername;
    this.IVProfile = IVProfile;
    this.IVUsername = IVUsername;
    this.TIETConfirmPass = TIETConfirmPass;
    this.TIETPassword = TIETPassword;
    this.TILConfirmPass = TILConfirmPass;
    this.TILPassword = TILPassword;
    this.TVConfirmPass = TVConfirmPass;
    this.TVEmail = TVEmail;
    this.TVFirstName = TVFirstName;
    this.TVLastName = TVLastName;
    this.TVPassRequi = TVPassRequi;
    this.TVPassword = TVPassword;
    this.TVPhone = TVPhone;
    this.frameLayout4 = frameLayout4;
    this.imageButton = imageButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BtnSignup;
      Button BtnSignup = ViewBindings.findChildViewById(rootView, id);
      if (BtnSignup == null) {
        break missingId;
      }

      id = R.id.ETEmail;
      EditText ETEmail = ViewBindings.findChildViewById(rootView, id);
      if (ETEmail == null) {
        break missingId;
      }

      id = R.id.ETFirstName;
      EditText ETFirstName = ViewBindings.findChildViewById(rootView, id);
      if (ETFirstName == null) {
        break missingId;
      }

      id = R.id.ETLastName;
      EditText ETLastName = ViewBindings.findChildViewById(rootView, id);
      if (ETLastName == null) {
        break missingId;
      }

      id = R.id.ETPhone;
      EditText ETPhone = ViewBindings.findChildViewById(rootView, id);
      if (ETPhone == null) {
        break missingId;
      }

      id = R.id.ETUsername;
      EditText ETUsername = ViewBindings.findChildViewById(rootView, id);
      if (ETUsername == null) {
        break missingId;
      }

      id = R.id.IVProfile;
      ShapeableImageView IVProfile = ViewBindings.findChildViewById(rootView, id);
      if (IVProfile == null) {
        break missingId;
      }

      id = R.id.IVUsername;
      TextView IVUsername = ViewBindings.findChildViewById(rootView, id);
      if (IVUsername == null) {
        break missingId;
      }

      id = R.id.TIETConfirmPass;
      TextInputEditText TIETConfirmPass = ViewBindings.findChildViewById(rootView, id);
      if (TIETConfirmPass == null) {
        break missingId;
      }

      id = R.id.TIETPassword;
      TextInputEditText TIETPassword = ViewBindings.findChildViewById(rootView, id);
      if (TIETPassword == null) {
        break missingId;
      }

      id = R.id.TILConfirmPass;
      TextInputLayout TILConfirmPass = ViewBindings.findChildViewById(rootView, id);
      if (TILConfirmPass == null) {
        break missingId;
      }

      id = R.id.TILPassword;
      TextInputLayout TILPassword = ViewBindings.findChildViewById(rootView, id);
      if (TILPassword == null) {
        break missingId;
      }

      id = R.id.TVConfirmPass;
      TextView TVConfirmPass = ViewBindings.findChildViewById(rootView, id);
      if (TVConfirmPass == null) {
        break missingId;
      }

      id = R.id.TVEmail;
      TextView TVEmail = ViewBindings.findChildViewById(rootView, id);
      if (TVEmail == null) {
        break missingId;
      }

      id = R.id.TVFirstName;
      TextView TVFirstName = ViewBindings.findChildViewById(rootView, id);
      if (TVFirstName == null) {
        break missingId;
      }

      id = R.id.TVLastName;
      TextView TVLastName = ViewBindings.findChildViewById(rootView, id);
      if (TVLastName == null) {
        break missingId;
      }

      id = R.id.TVPassRequi;
      TextView TVPassRequi = ViewBindings.findChildViewById(rootView, id);
      if (TVPassRequi == null) {
        break missingId;
      }

      id = R.id.TVPassword;
      TextView TVPassword = ViewBindings.findChildViewById(rootView, id);
      if (TVPassword == null) {
        break missingId;
      }

      id = R.id.TVPhone;
      TextView TVPhone = ViewBindings.findChildViewById(rootView, id);
      if (TVPhone == null) {
        break missingId;
      }

      RelativeLayout frameLayout4 = (RelativeLayout) rootView;

      id = R.id.imageButton;
      ShapeableImageView imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      return new FragmentSignupBinding((RelativeLayout) rootView, BtnSignup, ETEmail, ETFirstName,
          ETLastName, ETPhone, ETUsername, IVProfile, IVUsername, TIETConfirmPass, TIETPassword,
          TILConfirmPass, TILPassword, TVConfirmPass, TVEmail, TVFirstName, TVLastName, TVPassRequi,
          TVPassword, TVPhone, frameLayout4, imageButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
