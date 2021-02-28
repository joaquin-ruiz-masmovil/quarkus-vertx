package com.masmovil.phoneapp.infrastructure.repository;


import com.masmovil.phoneapp.domain.model.CatalogPhone;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.smallrye.mutiny.converters.uni.UniRxConverters;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PostgresCatalogPhoneRepository implements CatalogPhoneRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresCatalogPhoneRepository.class);

  private final Pool jdbcPool;
  private final Function<Row, CatalogPhone> mapper;

  public PostgresCatalogPhoneRepository(Pool jdbcPool, Function<Row, CatalogPhone> mapper) {
    this.jdbcPool = jdbcPool;
    this.mapper = mapper;
  }

  @Override
  public Single<List<CatalogPhone>> getPhoneCatalog() {

    LOGGER.info("Postgres DB get All Phone Catalog");

    return jdbcPool.preparedQuery("SELECT * FROM PHONECATALOG.PHONES_CATALOGUE")
        .execute()
        .convert().with(UniRxConverters.toSingle().failOnNull())
        .flatMap(rows -> Observable.fromIterable(rows)
            .map(mapper)
            .toList()
        );

  }

}
