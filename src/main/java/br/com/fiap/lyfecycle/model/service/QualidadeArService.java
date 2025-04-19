package br.com.fiap.lyfecycle.model.service;

import br.com.fiap.lyfecycle.model.QualidadeAr;
import br.com.fiap.lyfecycle.model.exceptions.ResourceNotFoundException;
import br.com.fiap.lyfecycle.model.repository.QualidadeArRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualidadeArService {

    private final QualidadeArRepository qualidadeArRepository;

    @Autowired
    public QualidadeArService(QualidadeArRepository qualidadeArRepository) {
        this.qualidadeArRepository = qualidadeArRepository;
    }

    public List<QualidadeAr> findAll() {
        return qualidadeArRepository.findAll();
    }

    public Optional<QualidadeAr> findById(Long id) {
        return qualidadeArRepository.findById(id);
    }

    public QualidadeAr save(QualidadeAr qualidadeAr) {
        return qualidadeArRepository.save(qualidadeAr);
    }

    public QualidadeAr update(Long id, QualidadeAr qualidadeArDetails) {
        QualidadeAr existingQualidadeAr = qualidadeArRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QualidadeAr não encontrada com o ID: " + id));

        existingQualidadeAr.setDataHora(qualidadeArDetails.getDataHora());
        existingQualidadeAr.setIdEstacao(qualidadeArDetails.getIdEstacao());
        existingQualidadeAr.setNivelPm25(qualidadeArDetails.getNivelPm25());
        existingQualidadeAr.setNivelPm10(qualidadeArDetails.getNivelPm10());

        return qualidadeArRepository.save(existingQualidadeAr);
    }

    public void deleteById(Long id) {
        qualidadeArRepository.deleteById(id);
    }
}
