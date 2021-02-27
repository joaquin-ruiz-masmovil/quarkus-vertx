package com.masmovil.phoneapp.infrastructure.repository.mapper;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.vertx.mutiny.sqlclient.Row;

public class PostgresCatalogPhoneMapper implements Function<Row, CatalogPhone> {
  @Override
  public CatalogPhone apply(@NonNull Row row) throws Exception {
    return CatalogPhone.of(row.getUUID("pc_id"),
        row.getString("pc_name"),
        row.getString("pc_description"),
        row.getString("pc_image"),
        row.getInteger("pc_price"));
  }

}
