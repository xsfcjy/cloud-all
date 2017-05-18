package com.sfxie.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.xsonorg.XSON;

public class SerializeUtil {

	/**
	 * 序列化对象
	 * xsf
	 * 2015年7月1日下午2:32:49
	 * TODO
	 * byte[]
	 */
	public static byte[] serialize(Object object) {

		ObjectOutputStream oos = null;

		ByteArrayOutputStream baos = null;

		try {

			// 序列化

			baos = new ByteArrayOutputStream();

			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			byte[] bytes = baos.toByteArray();

			return bytes;

		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 反序列化对象
	 * xsf
	 * 2015年7月1日下午2:33:03
	 * TODO
	 * Object
	 */
	public static <T> T unserialize(Class<T> t ,byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {

			// 反序列化

			bais = new ByteArrayInputStream(bytes);

			ObjectInputStream ois = new ObjectInputStream(bais);

			return (T) ois.readObject();

		} catch (Exception e) {
			 e.printStackTrace();
		}

		return null;

	}

	public static byte[] serializeByXson(Object object){
		byte[] data = XSON.write(object);
		return data;
	}
	public static <T> T unserializeByXson(Class<T> t ,byte[] bytes) {
		return (T)XSON.parse(bytes);
	}
}