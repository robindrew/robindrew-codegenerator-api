package com.robindrew.codegenerator.api.identity;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Exceptionally fast map implementation where keys are expected to be non-negative {@link IIdentity} objects starting
 * at zero. Be aware that {@link #keySet()} and {@link #entrySet()} methods will not work unless {@link #createKey(int)}
 * is implemented.
 */
public class IdentityArrayMap<K extends IIdentity, V> implements Map<K, V> {

	private static final int MIN_CAPACITY = 128;

	@SuppressWarnings("unchecked")
	private static final <V> V[] newArray(int length) {
		return (V[]) new Object[length];
	}

	private static final int getId(Object key) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		if (key instanceof IIdentity) {
			int id = ((IIdentity) key).getId();
			if (id < 0) {
				throw new IllegalArgumentException("negative id for key: " + key);
			}
			return id;
		}
		throw new IllegalArgumentException("Invalid key: " + key + "(" + key.getClass() + ")");
	}

	private V[] values = newArray(MIN_CAPACITY);
	private int size = 0;

	private void ensureCapacity(int id) {
		if (id < values.length) {
			return;
		}

		int capacity = id * 2;
		if (capacity < MIN_CAPACITY) {
			capacity = MIN_CAPACITY;
		}
		V[] newValues = newArray(capacity);
		if (size > 0) {
			System.arraycopy(values, 0, newValues, 0, values.length);
		}
		this.values = newValues;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (size == 0) {
			return false;
		}

		int id = getId(key);
		if (id >= values.length) {
			return false;
		}
		return values[id] != null;
	}

	@Override
	public boolean containsValue(Object value) {
		if (size == 0) {
			return false;
		}

		if (value == null) {
			throw new NullPointerException("value");
		}
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				if (values[i].equals(value)) {
					return true;
				}
				count++;
				if (count == size) {
					break;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		if (size == 0) {
			return null;
		}

		int id = getId(key);
		if (id >= values.length) {
			return null;
		}
		return values[id];
	}

	@Override
	public V put(K key, V value) {
		if (value == null) {
			throw new NullPointerException("value");
		}
		int id = getId(key);
		ensureCapacity(id);
		V oldValue = values[id];
		if (oldValue == null) {
			size++;
		}
		values[id] = value;
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		if (size == 0) {
			return null;
		}

		int id = getId(key);
		if (id >= values.length) {
			return null;
		}
		V oldValue = values[id];
		if (oldValue != null) {
			values[id] = null;
			size--;
		}
		return oldValue;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		values = newArray(MIN_CAPACITY);
		size = 0;
	}

	@Override
	public Collection<V> values() {
		if (size == 0) {
			return Collections.emptyList();
		}
		List<V> valueList = new ArrayList<>(size);
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				valueList.add(values[i]);
				count++;
				if (count == size) {
					break;
				}
			}
		}
		return valueList;
	}

	@Override
	public Set<K> keySet() {
		if (size == 0) {
			return Collections.emptySet();
		}
		Set<K> set = new LinkedHashSet<>();
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				K key = createKey(i);
				set.add(key);
				count++;
				if (count == size) {
					break;
				}
			}
		}
		return set;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		if (size == 0) {
			return Collections.emptySet();
		}
		Set<Entry<K, V>> set = new LinkedHashSet<>();
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				K key = createKey(i);
				set.add(new SimpleEntry<K, V>(key, values[i]));
				count++;
				if (count == size) {
					break;
				}
			}
		}
		return set;
	}

	protected K createKey(int id) {
		throw new UnsupportedOperationException("createKey(int)");
	}

}
