package com.manipal.service;

import com.manipal.service.impl.DefaultBookingService;
import com.manipal.service.impl.DefaultHallService;
import com.manipal.service.impl.DefaultMovieService;
import com.manipal.service.impl.DefaultUserService;

public class ServiceFactory {

	public static UserService getUserService() {
		return new DefaultUserService();
	}

	public static HallService getHallService() {
		return new DefaultHallService();
	}

	public static MovieService getMovieService() {
		return new DefaultMovieService();
	}

	public static BookingService getBookingService() {
		return new DefaultBookingService();
	}

}
