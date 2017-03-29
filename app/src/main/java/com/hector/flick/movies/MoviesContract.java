package com.hector.flick.movies;

import com.hector.flick.BasePresenter;
import com.hector.flick.BaseView;
import com.hector.flick.MoviesFilterType;
import com.hector.flick.movies.domain.model.Movie;

import java.util.List;

/**
 * This interface specifies the contract between the view and the presenter.
 */

public interface MoviesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showActiveFilterLabel();

        void showLoadingMovieError();

        void showMovieDetailsUi(String movieId);

        void showMovies(List<Movie>movies);

        void showPopularMoviesFilter();

        void showTopRatedMoviesFilter();

    }

    interface Presenter extends BasePresenter {

        MoviesFilterType getFiltering();

        void loadMovies(boolean forceUpdate);

        void openMovieDetails(Movie requestedMovie);

        void popularMovie(Movie popularMovie);

        void result(int requestCode, int resultCode);

        void setFiltering(MoviesFilterType requestType);

        void topRatedMovie(Movie topRatedMovie);

    }
}
