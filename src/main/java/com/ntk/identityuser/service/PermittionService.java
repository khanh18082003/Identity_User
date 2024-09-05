package com.ntk.identityuser.service;

import com.ntk.identityuser.dao.PermittionRepository;
import com.ntk.identityuser.dto.request.PermittionRequest;
import com.ntk.identityuser.dto.response.PermittionResponse;
import com.ntk.identityuser.entity.Permittion;
import com.ntk.identityuser.mapper.PermittionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PermittionService {

  PermittionRepository permittionRepository;
  PermittionMapper permittionMapper;

  public PermittionResponse create(PermittionRequest request) {
    Permittion permittion = permittionMapper.toPermittion(request);

    return permittionMapper.toPermittionResponse(permittionRepository.save(permittion));
  }

  public List<PermittionResponse> getAll() {
    return permittionRepository.findAll().stream()
        .map(permittionMapper::toPermittionResponse)
        .toList();
  }

  public void delete(String name) {
    permittionRepository.deleteById(name);
  }
}
