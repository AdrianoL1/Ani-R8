package com.adrianoL.domain.service;

import com.adrianoL.domain.exception.RoleNotFoundException;
import com.adrianoL.domain.model.auth.Role;
import com.adrianoL.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findRoleByName(Role.Value.USER).orElseThrow(() -> new RoleNotFoundException("USER"));
    }
}
