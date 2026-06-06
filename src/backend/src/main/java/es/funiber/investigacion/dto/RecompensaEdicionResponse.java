package es.funiber.investigacion.dto;

import java.util.List;

public record RecompensaEdicionResponse(
        RecompensaResponse recompensa,
        List<BeneficiarioRecompensaResponse> beneficiarios) {
}
