package com.mnjy.hackmit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnjy on 9/19/2015.
 */
public class ListFragment extends Fragment {
    private ListView postsListView;

    public ListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        postsListView = (ListView) view.findViewById(R.id.posts);
        // Populate list
        postsListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_fragment, getPostsInArea()));
        return view;
    }

    private ArrayList<Post> getPostsInArea(){
        ArrayList<Post> posts = new ArrayList<>();

        ParseQuery query = new ParseQuery("Post");
        query.include("location");
        query.orderByDescending("score");
        query.setLimit(20); //for now

        try {
            List<ParseObject> queries = query.find();

            for (ParseObject obj : queries){
                Post post = (Post) obj.get("post");
                if (post != null){
                    posts.add(post);
                }
            }

        } catch (ParseException e) {
        }
        return posts;
    }
}
