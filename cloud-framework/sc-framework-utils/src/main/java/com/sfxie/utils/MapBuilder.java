package com.sfxie.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

	private Map<K, V> map;

	public MapBuilder(K key, V value) {
		if (null == this.map)
			this.map = new HashMap<K, V>();

	}

	public MapBuilder() {
		if (null == this.map)
			this.map = new HashMap<K, V>();

	}

	public MapBuilder<K, V> put(K key, V value) {
		this.map.put(key, value);
		return this;
	}

	public Map<K, V> map() {
		return this.map;
	}
}
