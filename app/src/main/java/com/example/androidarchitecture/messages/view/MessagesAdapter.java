package com.example.androidarchitecture.messages.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidarchitecture.R;
import com.example.domain.messages.model.Message;

import java.sql.PreparedStatement;
import java.util.List;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private static final int SENT = 0;
    private static final int RECEIVED = 1;

    private String username;
    private List <Message> chats;

    public MessagesAdapter(String username, List<Message> chats)
    {
        this.username = username;
        this.chats = chats;
    }





    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@Nonnull ViewGroup parent, int viewType)
    {
        View view;
        if(viewType == SENT)
        {

            view = LayoutInflater.from(parent.getContext()).inflate(

                            R.layout.item_message_sent,
                            parent,
                            false
                    );

        } else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received, parent, false);
        }

        return new MessageViewHolder(view);
    }






    @Override
    public void onBindViewHolder(@Nonnull MessageViewHolder holder, int position)
    {
        holder.bind(chats.get(position));
    }



    @Override
    public int getItemViewType(int position)
    {
        if(chats.get(position).getSender().contentEquals(username))
        {
            return SENT;
        }
        else
        {
            return RECEIVED;
        }
    }




    @Override
    public int getItemCount(){return chats.size();}


    public void updateDate(List<Message> chats)
    {
        this.chats = chats;
        notifyDataSetChanged();
    }




    class MessageViewHolder extends RecyclerView.ViewHolder
    {
        TextView chatMessage;
        TextView chatSender;

        public MessageViewHolder(View itemView) {
            super(itemView);
            chatMessage = itemView.findViewById(R.id.chat_message);
            chatSender = itemView.findViewById(R.id.chat_sender);
        }



        public void bind(Message chat)
        {
            chatMessage.setText(chat.getMessage());

            if(!username.contentEquals(chat.getSender()))
            {
                chatSender.setText(chat.getSender());
            }
        }



    }








}
