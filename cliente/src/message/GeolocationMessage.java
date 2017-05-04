package message;

/**
 * Representa un mensaje relacionado a la Geolocalizacion.
 *
 */
public class GeolocationMessage extends Message {
    
    private double latitude;  // latitud
    private double longitude;  // longitud

    public GeolocationMessage() {
        
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
}
