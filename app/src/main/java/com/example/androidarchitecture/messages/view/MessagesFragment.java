package com.example.androidarchitecture.messages.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidarchitecture.R;
import com.example.androidarchitecture.auth.view.MainActivityFragmentsListener;
import com.example.androidarchitecture.core.Injector;
import com.example.androidarchitecture.messages.viewmodel.MessagesViewModelFactory;
import com.example.androidarchitecture.messages.viewmodel.MessagesViewModel;
import com.example.domain.messages.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

public class MessagesFragment extends Fragment{

    public static String ARG_USERNAME = "arg_username";

    private MessagesViewModelFactory factory = Injector.get().messagesViewModelFactory();
    private MessagesViewModel viewModel;

    private MainActivityFragmentsListener callback;

    private RecyclerView messagesList;
    private MessagesAdapter adapter;

    private Button logout;
    private Button sendMessage;
    private EditText message;

    private String username;

    private CompositeDisposable disposable;

    public static MessagesFragment newInstance(String username)
    {
        Bundle args = new Bundle();

        args.putString(ARG_USERNAME, username);

        MessagesFragment fragment = new MessagesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        disposable = new CompositeDisposable();

        viewModel = ViewModelProviders.of(this, factory).get(MessagesViewModel.class);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            callback = (MainActivityFragmentsListener) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException("Activity must implement MainAcitivityFragmnetListener");
        }


    }



    @Nullable
    @Override
    public View onCreateView(@Nonnull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_messages,container, false);

        Bundle args = getArguments();
        username = Objects.requireNonNull(args).getString(ARG_USERNAME);

        sendMessage = view.findViewById(R.id.send);
        message = view.findViewById(R.id.message);
        logout = view.findViewById(R.id.log_out);
        messagesList = view.findViewById(R.id.message_list);

        Context context;
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        manager.setReverseLayout(true);
        messagesList.setLayoutManager(manager);

        adapter = new MessagesAdapter(username, new ArrayList<Message>());
        messagesList.setAdapter(adapter);

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                callback.onLogoutClick();
            }


        });


        sendMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Message chatMessage = new Message();
                chatMessage.setMessage(message.getText().toString());
                chatMessage.setSender(username);
                chatMessage.setSent(System.currentTimeMillis());
                message.setText("");
                disposable.add(
                        viewModel.sendMessage(chatMessage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action()
                        {
                            @Override
                            public void run() throws Exception
                            {

                            }

                        }, new Consumer<Throwable>() {

                            @Override
                            public void accept(Throwable throwable) throws  Exception
                            {
                                showInternetError();
                            }
                        }));





            }

        });

        addMessageBoxTextListener();
        disposable.add(viewModel.getMessages()
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Message>>()
                {
                    @Override
                    public void accept(List<Message> messages) throws Exception
                    {
                        adapter.updateData(messages);
                    }
                }, new Consumer <Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception
                    {
                        showInternetError();
                    }
                })
        );

    return view;

    }


    private void showInternetError()
    {
        Toast.makeText(getContext() , "Please check you internet conection", Toast.LENGTH_SHORT).show();
    }


    private void addMessageBoxTextListener()
    {
        message.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int befoore, int count)
            {
                if(s.length() > 0)
                {
                    sendMessage.setEnabled(true);
                }
                else
                {
                    sendMessage.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }

        });

    }












    @Override
    public void onStop()
    {
        super.onStop();
        disposable.clear();
    }





}
