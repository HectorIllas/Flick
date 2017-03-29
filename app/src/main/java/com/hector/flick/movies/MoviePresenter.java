package com.hector.flick.movies;

import com.hector.flick.MoviesFilterType;
import com.hector.flick.movies.domain.model.Movie;

/**
 * Created by hector on 3/28/17.
 */

public class MoviePresenter implements MoviesContract.Presenter {

    private final MoviesContract.View mMovieView;

    @Override
    public MoviesFilterType getFiltering() {
        return null;
    }

    @Override
    public void loadMovies(boolean forceUpdate) {

    }

    @Override
    public void openMovieDetails(Movie requestedMovie) {

    }

    @Override
    public void popularMovie(Movie popularMovie) {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void setFiltering(MoviesFilterType requestType) {

    }

    @Override
    public void topRatedMovie(Movie topRatedMovie) {

    }

    @Override
    public void start() {

    }
}
