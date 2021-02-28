package com.masmovil.phoneapp.application.createcostumerorder;


import com.masmovil.phoneapp.domain.model.CostumerOrder;
import com.masmovil.phoneapp.domain.repository.CatalogPhoneRepository;
import io.reactivex.Single;

public class CreateCostumerOrderService {

  private CatalogPhoneRepository catalogPhoneRepository;

  public CreateCostumerOrderService(CatalogPhoneRepository catalogPhoneRepository) {
    this.catalogPhoneRepository = catalogPhoneRepository;
  }

  public Single<CostumerOrderResponse> execute(CostumerOrderRequest requestOrder) {

    return Single.just(requestOrder)
        .zipWith(catalogPhoneRepository.getPhoneCatalog(), (costumerOrderRequest, catalogPhoneList) -> {
          CostumerOrder costumerOrder = CostumerOrder.create()
              .withName(costumerOrderRequest.getName())
              .withSurname(costumerOrderRequest.getSurname())
              .withEmail(costumerOrderRequest.getEmail())
              .withPhonesNames(costumerOrderRequest.getPhonesNames())
              .withPhoneCatalog(catalogPhoneList)
              .build();

          return new CostumerOrderResponse(
              costumerOrder.getName(),
              costumerOrder.getSurname(),
              costumerOrder.getEmail(),
              costumerOrder.getPhoneNames(),
              costumerOrder.getTotalPrice());
        });

  }

}
