package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.Context;

import java.util.Optional;

public interface ContextDao {

	Optional<Context> findByName(String name);
	Context save(Context context);

}
