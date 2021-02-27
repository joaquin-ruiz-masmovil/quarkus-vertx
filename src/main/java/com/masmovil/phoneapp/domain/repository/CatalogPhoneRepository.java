package com.masmovil.phoneapp.domain.repository;

import com.masmovil.phoneapp.domain.model.CatalogPhone;
import io.reactivex.Single;

import java.util.List;

public interface CatalogPhoneRepository {

  Single<List<CatalogPhone>> getPhoneCatalog();

}
