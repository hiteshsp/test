package com.manipal.dao;

import java.util.Date;
import java.util.List;

import com.manipal.entity.Movie;
import com.manipal.entity.Show;

public interface MovieDao {
	// Entities involved: Movie, Show

	// CRUD

	public void addMovie(Movie movie) throws DaoException;

	public Movie getMovie(int movieId) throws DaoException;

	public void updateMovie(Movie movie) throws DaoException;

	public void deleteMovie(int movieId) throws DaoException;

	public void addShow(Show show) throws DaoException;

	public Show getShow(int showId) throws DaoException;

	public void updateShow(Show show) throws DaoException;

	public int deleteShow(int showId) throws DaoException;

	// Queries

	public List<Movie> getAllMovies() throws DaoException;

	public List<Movie> getMovies(String token) throws DaoException;

	public List<Show> getShowsByHall(int hallId) throws DaoException;

	public List<Show> getShowsByMovie(int movieId) throws DaoException;

	public List<Show> getShowsBySlot(int slot) throws DaoException;

	public List<Show> getShowsByDate(Date fromDate, Date toDate)
			throws DaoException;

	public List<Show> getAllShows() throws DaoException;

}
