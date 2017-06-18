package com.manipal.service.impl;

import java.util.List;

import com.manipal.dao.DaoException;
import com.manipal.dao.DaoFactory;
import com.manipal.dao.MovieDao;
import com.manipal.entity.Movie;
import com.manipal.entity.Show;
import com.manipal.service.MovieService;
import com.manipal.service.ServiceException;

public class DefaultMovieService implements MovieService {

	@Override
	public void addMovie(Movie movie) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			dao.addMovie(movie);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Movie getMovie(int movieId) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			return dao.getMovie(movieId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateMovie(Movie movie) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			dao.updateMovie(movie);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteMovie(int movieId) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			dao.deleteMovie(movieId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Movie> getAllMovies() throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			return dao.getAllMovies();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Show getShow(int showId) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			return dao.getShow(showId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addShow(Show show) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			dao.addShow(show);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteShow(int showId) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			return dao.deleteShow(showId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Show> getAllShows() throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			return dao.getAllShows();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateShow(Show show) throws ServiceException {
		try {
			MovieDao dao = DaoFactory.getDaoFactory("file").getMovieDao();
			dao.updateShow(show);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
