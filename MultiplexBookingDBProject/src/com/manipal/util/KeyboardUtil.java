package com.manipal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("resource")
public class KeyboardUtil {

	public static String getString(String message) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(message);
		return scanner.nextLine().trim();
	}

	public static int getInt(String message) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print(message);
			return scanner.nextInt();
		} catch (Exception e) {
			throw new InputMismatchException(
					"Input was expected to be an integer value");
		}
	}

	public static double getDouble(String message) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print(message);
			return scanner.nextDouble();
		} catch (Exception e) {
			throw new InputMismatchException(
					"Input was expected to be a decimal number");
		}
	}

	public static Date getDate(String message) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(message);
		String input = scanner.nextLine();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			return sdf.parse(input);
		} catch (Exception e) {
			throw new InputMismatchException(
					"Date is expected in yyyy-MM-dd format");
		}
	}
}
