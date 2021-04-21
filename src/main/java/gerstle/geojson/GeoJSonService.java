package gerstle.geojson;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface GeoJSonService
{
    @GET("/earthquakes/feed/v1.0/summary/significant_month.geojson")
    Single<GeoJSonFeed> getSignificantEarthquakes();

}
