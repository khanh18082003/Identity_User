package com.ntk.identityuser.dao;

import com.ntk.identityuser.entity.Permittion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermittionRepository extends JpaRepository<Permittion, String> {

}
