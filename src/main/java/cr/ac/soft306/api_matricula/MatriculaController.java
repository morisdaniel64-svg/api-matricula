package cr.ac.soft306.api_matricula;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    public record SolicitudMatricula(
            int edad,
            int creditos,
            double promedio,
            boolean tieneMora
    ) {}

    public record RespuestaMatricula(
            String decision,
            String motivo
    ) {}

    @PostMapping
    public ResponseEntity<RespuestaMatricula> matricular(@RequestBody SolicitudMatricula solicitud) {
        if (solicitud.edad() < 17) {
            return ResponseEntity.ok(new RespuestaMatricula(
                    "RECHAZADA",
                    "La edad mínima es 17 años."
            ));
        }
        if (solicitud.creditos() < 1 || solicitud.creditos() > 18) {
            return ResponseEntity.ok(new RespuestaMatricula(
                    "RECHAZADA",
                    "Los créditos deben estar entre 1 y 18."
            ));
        }
        if (solicitud.promedio() < 70) {
            return ResponseEntity.ok(new RespuestaMatricula(
                    "RECHAZADA",
                    "El promedio mínimo es 70."
            ));
        }
        if (solicitud.tieneMora()) {
            return ResponseEntity.ok(new RespuestaMatricula(
                    "RECHAZADA",
                    "Existe una mora pendiente."
            ));
        }
        return ResponseEntity.ok(new RespuestaMatricula(
                "PERMITIDA",
                "Cumple todas las condiciones."
        ));
    }
}
