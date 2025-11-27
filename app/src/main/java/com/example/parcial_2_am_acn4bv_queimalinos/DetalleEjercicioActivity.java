package com.example.parcial_2_am_acn4bv_queimalinos;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class DetalleEjercicioActivity extends AppCompatActivity {

    private static final String API_URL = "https://exercisedb.p.rapidapi.com/image";
    private static final String API_KEY = "ed00eae7f7mshca4bc4bb46631f3p140c1djsn93547d7fa4ec";
    private static final String API_HOST = "exercisedb.p.rapidapi.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicio);

        TextView titulo = findViewById(R.id.txtTituloEjercicio);
        TextView descripcion = findViewById(R.id.txtDescripcionEjercicio);
        ImageView imagen = findViewById(R.id.imgEjercicio);

        String nombre = getIntent().getStringExtra("nombre");
        String desc = getIntent().getStringExtra("descripcion");
        String imageId = getIntent().getStringExtra("imageId");

        titulo.setText(nombre);
        descripcion.setText(desc);

        // Hacer la petición GET a la API
        OkHttpClient client = new OkHttpClient();
        String url = API_URL + "?exerciseId=" + imageId + "&resolution=high";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-rapidapi-host", API_HOST)
                .addHeader("x-rapidapi-key", API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = response.body().string().replace("\"", ""); // La API devuelve un string con la URL
                    runOnUiThread(() -> {
                        Glide.with(DetalleEjercicioActivity.this)
                                .load(imageUrl)
                                .timeout(6000)
                                .into(imagen);
                    });
                }
            }
        });
    }
}
