package ru.rinat.users.contact;

import org.springframework.http.ResponseEntity;
import ru.rinat.users.common.CommonResponseDto;

import java.util.List;

public interface ContactSpecification {
    ResponseEntity<CommonResponseDto<?>> create(ContactRequest contactRequest);

    CommonResponseDto<?> update(ContactRequest contactRequest, Long id);

    CommonResponseDto<?> delete(Long id);

    CommonResponseDto<ContactResponse> findById(Long id);

    CommonResponseDto<List<ContactResponse>> findByUserCreatorId(Long id);
}
