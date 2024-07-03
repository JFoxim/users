package ru.rinat.users.subscription;

import org.springframework.http.ResponseEntity;
import ru.rinat.users.common.CommonResponseDto;

import java.util.List;

public interface SubscriptionSpecification {

    ResponseEntity<CommonResponseDto<?>> create(SubscriptionRequest subscriptionRequest);

    CommonResponseDto<?> update(SubscriptionRequest subscriptionRequest, Long id);

    CommonResponseDto<?> delete(Long id);

    CommonResponseDto<SubscriptionResponse> findById(Long id);

    CommonResponseDto<List<SubscriptionResponse>> findByUserCreatorId(Long id);


    CommonResponseDto<List<SubscriptionResponse>> findAll(int page, int size, String sortDir, String sort);
}
