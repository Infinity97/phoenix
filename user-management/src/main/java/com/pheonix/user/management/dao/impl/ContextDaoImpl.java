package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.ContextDao;
import com.pheonix.user.management.dao.repository.ContextRepository;
import com.pheonix.user.management.model.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Slf4j
@RequiredArgsConstructor
public class ContextDaoImpl implements ContextDao {

	private final ContextRepository contextRepository;

	@Override
	public Optional<Context> findByName(String name) {
		return contextRepository.findByName(name);
	}

	@Override
	public Context save(Context context) {
		return contextRepository.save(context);
	}
}
