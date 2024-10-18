package test.api.gps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.api.gps.controller.dto.CreatePointOfIterest;
import test.api.gps.entity.PointOfInterest;
import test.api.gps.repository.PointOfInterestRepository;
import test.api.gps.services.CepResponse;
import test.api.gps.services.CepService;
import test.api.gps.services.GeocodeService;

import java.util.List;
import java.util.Map;

@RestController
@Controller
public class PointOfInterestController {

    @Autowired
    private CepService cepService;

    private final PointOfInterestRepository repository;

    public PointOfInterestController(PointOfInterestRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private GeocodeService geocodeService;

    //Point - Converter CEP em latitude e longitude
    @GetMapping("/points-of-intere/{cep}")
    public Map<String, Double> getCoordinates(@PathVariable String cep) {
        return geocodeService.getCoordinates(cep);
    }

    //Point - Salva a nome, latitude e longitude
    @PostMapping("/points-of-intere")
    public ResponseEntity<Void> createPoi(@RequestBody CreatePointOfIterest body){

        repository.save(new PointOfInterest(body.name(), body.x(), body.y(), body.cep()));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/points-of-intere")
    public ResponseEntity<Page<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        var body = repository.findAll(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam("x") Long x,
                                                        @RequestParam("y") Long y,
                                                        @RequestParam("dmax") Long dmax){

        var xMin = x - dmax;
        var xMax = x + dmax;
        var yMin = y - dmax;
        var yMax = y + dmax;


        var body = repository.findNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> distanceBetweenPoints(x, y, p.getX(), p.getY()) <= dmax)
                .toList();

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/points-of-intere/{id}")
    public ResponseEntity<Void> deletePoi(@PathVariable Long id) {
        // Verifica se o POI existe
        var poi = repository.findById(id);
        if (poi.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();  // Retorna 204 No Content se deletado com sucesso
        } else {
            return ResponseEntity.notFound().build();   // Retorna 404 se o POI não for encontrado
        }
    }

    private Double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2) {
        return Math.sqrt(Math.pow(x2- x1, 2) + Math.pow(y2- y1, 2));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<CepResponse> buscarCep(@PathVariable String cep) {
        CepResponse response = cepService.buscarCep(cep);
        return ResponseEntity.ok(response);
    }

}
