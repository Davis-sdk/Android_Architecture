package com.example.androidarchitecture.auth.view;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidarchitecture.R;
import com.example.androidarchitecture.auth.viewmodel.AuthViewModel;
import com.example.androidarchitecture.auth.viewmodel.AuthViewModelFactory;
import com.example.androidarchitecture.core.Injector;
import com.example.androidarchitecture.util.InputUtil;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.disposables.CompositeDisposable;

public class LoginFragment extends Fragment {

    private AuthViewModelFactory factory = Injector.get().authViewModelFactory();
    private AuthViewModel viemModel;

    private MainActivityFragmentsListener callback;

    private EditText username;
    private EditText password;
    private TextView usernameError;
    private TextView passwordError;
    private TextView signUp;
    private Button login;

    private CompositeDisposable disposables;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        disposables = new CompositeDisposable();

        viemModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()),factory).get(AuthViewModel.class);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);


        try
        {
            callback = (MainActivityFragmentsListener) context;
        } catch (ClassCastException e)
        {
            throw new ClassCastException("Activity must implament MainActivityFragmentListener");





        }



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        usernameError = view.findViewById(R.id.username_error);
        passwordError = view.findViewById(R.id.password_error);

        signUp = view.findViewById(R.id.sign_up);
        signUp.setPaintFlags(signUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callback.onSignUpClick();
            }


        });

        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!hasErrors())
                {
                    InputUtil.hideKeyboard();
                }as
            }


        });



    }











}
