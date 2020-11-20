package com.usoftware.marea.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usoftware.marea.api.models.ExecucaoModel;

@Repository
public interface ExecucaoRepository extends JpaRepository<ExecucaoModel, Long>{

}