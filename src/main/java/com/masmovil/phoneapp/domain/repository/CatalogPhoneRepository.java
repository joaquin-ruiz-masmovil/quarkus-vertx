package com.masmovil.phoneapp.domain.repository;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface CatalogPhoneRepository {

  Uni<List<CatalogPhone>> getPhoneCatalog();

}
