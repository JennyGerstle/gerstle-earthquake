package gerstle.geojson;

import io.reactivex.rxjava3.core.Single;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class GeoJSonServiceTest{

    @Test
    public void getsignificantEarthquakes(){
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        GeoJSonService service = retrofit.create(GeoJSonService.class);
        //when
        Single<GeoJSonFeed> single = service.getSignificantEarthquakes();
        //DO NOT USE BLOCKING GET
        GeoJSonFeed feed = single.blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.features);
        assertFalse(feed.features.isEmpty());
        assertNotNull(feed.features.get(0).properties);
        assertNotNull(feed.features.get(0).properties.place);
        assertTrue(feed.features.get(0).properties.mag > 0);
        assertTrue(feed.features.get(0).properties.time > 0);
        assertNotNull(feed.features.get(0).geometry);
        assertNotNull(feed.features.get(0).geometry.coordinates);
        assertFalse(feed.features.get(0).geometry.coordinates.isEmpty());
    }
}
