package br.com.fiap.lyfecycle.model.controller;


import br.com.fiap.lyfecycle.model.QualidadeAr;
import br.com.fiap.lyfecycle.model.exceptions.ResourceNotFoundException;
import br.com.fiap.lyfecycle.model.service.QualidadeArService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualidade-ar")
public class QualidadeArController {

    @Autowired
    public QualidadeArService qualidadeArService;


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<QualidadeAr>> getAllQualidadeAr() {
        List<QualidadeAr> lista = qualidadeArService.findAll();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum dado de Qualidade do Ar encontrado.");
        }

        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<QualidadeAr> getQualidadeArById(@PathVariable("id") Long id) {
        QualidadeAr qualidadeAr = qualidadeArService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QualidadeAr não encontrada com o ID: " + id));

        return ResponseEntity.ok(qualidadeAr);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createQualidadeAr(@RequestBody QualidadeAr qualidadeAr) {
        QualidadeAr savedQualidadeAr = qualidadeArService.save(qualidadeAr);
        String message = String.format("Qualidade do ar criada com sucesso: ID da estação = %d, Data/Hora = %s, Nível PM2.5 = %.2f, Nível PM10 = %.2f",
                savedQualidadeAr.getIdEstacao(),
                savedQualidadeAr.getDataHora(),
                savedQualidadeAr.getNivelPm25(),
                savedQualidadeAr.getNivelPm10());

        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateQualidadeAr(
            @PathVariable("id") Long id,
            @RequestBody QualidadeAr qualidadeArDetails) {

        QualidadeAr updatedQualidadeAr = qualidadeArService.update(id, qualidadeArDetails);

        String message = String.format(
                "Qualidade do ar atualizada com sucesso: ID = %d, Data/Hora = %s, Nível PM2.5 = %.2f, Nível PM10 = %.2f",
                updatedQualidadeAr.getIdEstacao(),
                updatedQualidadeAr.getDataHora(),
                updatedQualidadeAr.getNivelPm25(),
                updatedQualidadeAr.getNivelPm10()
        );

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteQualidadeAr(@PathVariable("id") Long id) {
        QualidadeAr qualidadeAr = qualidadeArService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QualidadeAr não encontrada com o ID: " + id));

        qualidadeArService.deleteById(id);
        return ResponseEntity.ok("Qualidade do ar com ID " + id + " foi deletada com sucesso.");
    }


}
