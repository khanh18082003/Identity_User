package com.ntk.identityuser.mapper;

import com.ntk.identityuser.dto.request.PermittionRequest;
import com.ntk.identityuser.dto.response.PermittionResponse;
import com.ntk.identityuser.entity.Permittion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermittionMapper {

  Permittion toPermittion(PermittionRequest permittion);

  PermittionResponse toPermittionResponse(Permittion permittion);
}
