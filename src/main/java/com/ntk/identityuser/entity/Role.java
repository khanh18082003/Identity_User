package com.ntk.identityuser.entity;

import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class Role {

  @Id
  String name;
  String description;

  @ManyToMany(fetch = FetchType.EAGER)
  Set<Permittion> permittions;
}
