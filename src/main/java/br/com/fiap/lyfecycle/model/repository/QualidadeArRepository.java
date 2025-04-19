package br.com.fiap.lyfecycle.model.repository;

import br.com.fiap.lyfecycle.model.QualidadeAr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualidadeArRepository extends JpaRepository<QualidadeAr, Long> {
}
