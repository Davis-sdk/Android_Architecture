package com.example.androidarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.OverScroller;

import com.example.androidarchitecture.auth.view.LoginFragment;
import com.example.androidarchitecture.auth.view.MainActivityFragmentsListener;
import com.example.androidarchitecture.auth.view.SignUpFragment;
import com.example.androidarchitecture.messages.view.MessagesFragment;

import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity implements MainActivityFragmentsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onLoginClick(){showLoginFragment()}




    private void showLoginFragment()
    {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1)
        {
            getSupportFragmentManager().popBackStack();
        }

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                        R.animator.slide_in_from_left, R.animator.fade_out)
                .replace(R.id.fragment_container, new LoginFragment())
                .addToBackStack(null)
                .commit();


    }





    private void showSignUpFragment()
    {
        if(getSupportFragmentManager().getBackStackEntryCount() >1)
        {
            getSupportFragmentManager().popBackStack();
        }

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                        R.animator.slide_in_from_left, R.animator.fade_out)
                .replace(R.id.fragment_container, new SignUpFragment())
                .addToBackStack(null)
                .commit();

    }


    private void showChatFragment(String username)
    {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1)
        {
            getSupportFragmentManager().popBackStack();
        }

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                        R.animator.slide_in_from_left, R.animator.fade_out)
                .replace(R.id.fragment_container, MessagesFragment.newInstance(username), "MessagesFragment")
                .addToBackStack(null)
                .commit();
    }







    @Override
    public void onBackPressed()
    {
        if(getSupportFragmentManager().findFragmentByTag("MessagesFragment") != null)
        {
            showSignUpFragment();
            return;
        }

        if(getSupportFragmentManager().getBackStackEntryCount() > 1)
        {
            getSupportFragmentManager().popBackStack();
        }
        else
        {
            finish();
        }


    }



}
