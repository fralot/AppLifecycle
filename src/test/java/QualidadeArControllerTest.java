

import br.com.fiap.lyfecycle.model.QualidadeAr;
import br.com.fiap.lyfecycle.model.controller.QualidadeArController;
import br.com.fiap.lyfecycle.model.exceptions.ResourceNotFoundException;
import br.com.fiap.lyfecycle.model.service.QualidadeArService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class QualidadeArControllerTest {

    private final QualidadeArService qualidadeArService = mock(QualidadeArService.class);
    private final QualidadeArController qualidadeArController = new QualidadeArController();

    public QualidadeArControllerTest() {
        qualidadeArController.qualidadeArService = qualidadeArService;
    }

    @Test
    void testGetAllQualidadeAr() {
        QualidadeAr qualidadeAr = new QualidadeAr();
        List<QualidadeAr> mockList = Arrays.asList(qualidadeAr);

        when(qualidadeArService.findAll()).thenReturn(mockList);

        ResponseEntity<List<QualidadeAr>> response = qualidadeArController.getAllQualidadeAr();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockList, response.getBody());
    }

    @Test
    void testGetQualidadeArById() {
        QualidadeAr qualidadeAr = new QualidadeAr();
        when(qualidadeArService.findById(1L)).thenReturn(Optional.of(qualidadeAr));

        ResponseEntity<QualidadeAr> response = qualidadeArController.getQualidadeArById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(qualidadeAr, response.getBody());
    }

    @Test
    void testGetQualidadeArByIdNotFound() {
        when(qualidadeArService.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> qualidadeArController.getQualidadeArById(1L));
    }

    @Test
    void testCreateQualidadeAr() {
        QualidadeAr qualidadeAr = new QualidadeAr();
        when(qualidadeArService.save(qualidadeAr)).thenReturn(qualidadeAr);

        ResponseEntity<String> response = qualidadeArController.createQualidadeAr(qualidadeAr);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testUpdateQualidadeAr() {
        QualidadeAr qualidadeAr = new QualidadeAr();
        when(qualidadeArService.update(1L, qualidadeAr)).thenReturn(qualidadeAr);

        ResponseEntity<String> response = qualidadeArController.updateQualidadeAr(1L, qualidadeAr);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteQualidadeAr() {
        QualidadeAr qualidadeAr = new QualidadeAr();
        when(qualidadeArService.findById(1L)).thenReturn(Optional.of(qualidadeAr));
        doNothing().when(qualidadeArService).deleteById(1L);

        ResponseEntity<String> response = qualidadeArController.deleteQualidadeAr(1L);

        assertEquals(200, response.getStatusCodeValue());
    }
}