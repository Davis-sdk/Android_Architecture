package com.example.androidarchitecture.auth.view;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidarchitecture.R;
import com.example.androidarchitecture.auth.viewmodel.AuthViewModel;
import com.example.androidarchitecture.auth.viewmodel.AuthViewModelFactory;
import com.example.androidarchitecture.core.Injector;
import com.example.androidarchitecture.util.InputUtil;
import com.example.domain.user.model.User;

import java.util.Objects;
import java.util.function.Consumer;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class SignUpFragment extends Fragment {


    private AuthViewModelFactory factory = Injector.get().authViewModelFactory();
    private AuthViewModel viewModel;

    private MainActivityFragmentsListener callback;

    private EditText username;
    private EditText password;
    private TextView usernameError;
    private TextView passwordError;
    private TextView login;
    private Button signUp;

    private CompositeDisposable disposable;



    @Override
    public void onCreate(@NonNull Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();

        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), factory).get(AuthViewModel.class);

    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            callback = (MainActivityFragmentsListener) context;
        }

        catch (ClassCastException e)
        {
            throw new ClassCastException("Activity mus implement MainAcitivityFragmentListener");
        }


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        usernameError = view.findViewById(R.id.username_error);
        passwordError = view.findViewById(R.id.password_error);

        login = view.findViewById(R.id.login);
        login.setPaintFlags(login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onLoginClick();
            }
        });

        signUp = view.findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasError()) {
                    InputUtil.hideKeyboard(Objects.requireNonNull(getContext()), view);
                    disposable.add(
                            viewModel.signUp(username.getText().toString(), password.getText().toString())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<User>() {
                                        @Override
                                        public void accept(User user) throws Exception {
                                            enableSignUpButton();
                                            callback.onSignUpSuccess(user.getUsername());
                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            enableSignUpButton();
                                            showPasswordError();
                                            showUsernameError();
                                            Log.e("SignUpFragment", "Error: ", throwable);
                                        }
                                    })
                    );

                }


            }
        });

        return view;

    }







    @Override
    public void onStop()
    {
        super.onStop();
        disposable.clear();
    }







    private boolean hasError()
    {
        boolean hasError = false;

        String usernameValue = username.getText().toString();
        if(usernameValue.isEmpty()  || usernameValue.length() < 8)
        {
            hasError = true;
            showUsernameError(); }

        else
        {
            hideUsernameError();
        }

        String passwordValue = password.getText().toString();

        if(passwordValue.isEmpty() || passwordValue.length() < 8)
        {
            hasError = true;
            showPasswordError();
        }
            else
        {
            hidePasswordError();
        }




        return hasError;
    }







    private void showUsernameError(){usernameError.setVisibility(View.VISIBLE);}

    private void hideUsernameError(){usernameError.setVisibility(View.GONE);}

    private void showPasswordError(){passwordError.setVisibility(View.VISIBLE);}

    private void hidePasswordError(){passwordError.setVisibility(View.GONE);}

    private void enableSignUpButton(){signUp.setEnabled(true);}

    private void disableSignUpButton(){signUp.setEnabled(false);}

}
