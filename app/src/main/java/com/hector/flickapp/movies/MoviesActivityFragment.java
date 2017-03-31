package com.hector.flickapp.movies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hector.flickapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment {

    public MoviesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }
}
