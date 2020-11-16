package com.robindrew.codegenerator.api.identity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IdentityLockSet {

	private final int partitions;
	private final ReentrantReadWriteLock[] locks;

	public IdentityLockSet(int partitions) {
		this(partitions, false);
	}

	public IdentityLockSet(int partitions, boolean fair) {
		if (partitions < 1) {
			throw new IllegalArgumentException("partitions=" + partitions);
		}
		this.partitions = partitions;
		this.locks = new ReentrantReadWriteLock[partitions];

		// Populate
		for (int i = 0; i < partitions; i++) {
			this.locks[i] = new ReentrantReadWriteLock(fair);
		}
	}

	public ReentrantReadWriteLock getLock(IIdentity identity) {
		return getLock(identity.getId());
	}

	public ReentrantReadWriteLock getLock(int id) {
		int index = id % partitions;
		return locks[index];
	}

	public Lock getReadLock(IIdentity identity) {
		return getReadLock(identity.getId());
	}

	public Lock getReadLock(int id) {
		return getLock(id).readLock();
	}

	public Lock getWriteLock(IIdentity identity) {
		return getWriteLock(identity.getId());
	}

	public Lock getWriteLock(int id) {
		return getLock(id).writeLock();
	}

}
