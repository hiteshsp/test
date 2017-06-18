package com.manipal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {

	@SuppressWarnings("unchecked")
	public static <T> Set<T> getContent(String filename)
			throws FileUtilException {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			Set<T> set = (Set<T>) in.readObject();
			in.close();
			file.close();
			return set;
		} catch (FileNotFoundException e) {
			Set<T> set = new HashSet<T>();
			putContent(filename, set);
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException(e);
		}
	}

	public static <T> void putContent(String filename, Set<T> set)
			throws FileUtilException {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(set);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException(e);
		}
	}

}
