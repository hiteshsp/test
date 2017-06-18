package com.manipal.service;

import java.util.List;

import com.manipal.entity.Movie;
import com.manipal.entity.Show;

public interface MovieService {

	public void addMovie(Movie movie) throws ServiceException;

	public Movie getMovie(int id) throws ServiceException;

	public void updateMovie(Movie movie) throws ServiceException;

	public void deleteMovie(int movieId) throws ServiceException;

	public List<Movie> getAllMovies() throws ServiceException;

	public Show getShow(int showId) throws ServiceException;

	public void addShow(Show show) throws ServiceException;

	public int deleteShow(int showId) throws ServiceException;

	public List<Show> getAllShows() throws ServiceException;

	public void updateShow(Show show) throws ServiceException;

}
