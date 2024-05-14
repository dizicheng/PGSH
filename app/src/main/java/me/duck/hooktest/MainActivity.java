import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("token", "c97d8a2891db26e0b82b0280b1154deb")
                        .build();

                Request request = new Request.Builder()
                        .url("https://userapi.qiekj.com/user/info")
                        .post(formBody)
                        .header("Authorization", "c97d8a2891db26e0b82b0280b1154deb")
                        .header("Version", "1.56.0")
                        .header("channel", "android_app")
                        .header("phoneBrand", "Redmi")
                        .header("timestamp", "1715665301680")
                        .header("sign", "cedc76b7f4dc0408fa44d3e9d2ffabbef206401a0012e97221ff883c3fb8871e")
                        .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .header("Content-Length", "38")
                        .header("Host", "userapi.qiekj.com")
                        .header("Connection", "Keep-Alive")
                        .header("Accept-Encoding", "gzip")
                        .header("Cookie", "acw_tc=2f624a3e17156638081366429e3f7b9e58df92334368d99e190e86b20b092b")
                        .header("User-Agent", "okhttp/3.14.9")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    final String result = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(result);
                        }
                    });
                } catch (IOException e) {
                    Log.e("MainActivity", "Error ", e);
                }
            }
        }).start();
    }
}
