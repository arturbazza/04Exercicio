    package br.org.fundatec.atividade04.controller.response;

    import lombok.Data;

    import java.util.List;
    import java.util.Optional;

    @Data
    public class GoogleMapsResponse {
        private List<Result> results;
        private String status;

        public Optional<GeographicLocation> getFirstLocation() {
            if (results != null && !results.isEmpty()) {
                Geometry geometry = results.get(0).getGeometry();
                if (geometry != null && geometry.getLocation() != null) {
                    return Optional.of(new GeographicLocation());
                }
            }
            return Optional.empty();
        }
    }
