package com.manipal.entity;

import java.io.Serializable;

public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int movieId;
	private String movieName;

	public Movie() {
	}

	public Movie(int movieId, String movieName) {
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Movie)) {
			return false;
		}
		Movie other = (Movie) obj;
		if (movieId != other.movieId) {
			return false;
		}
		return true;
	}

}
