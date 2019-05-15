package a00267320.ait.com.androidacc;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

class PostTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... json) {
        try {
            sendPost(json[0]);
        } catch (Exception e) {
            this.exception = e;
            return "";
        }
        return "Success";
    }

    private void sendPost(String jsonData) throws IOException {
        HttpURLConnection conn = null;
        try {
            String url = "http://35.234.150.106:8080/sensorData/addData";
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            // wr.writeBytes(URLEncoder.encode(jsonData, "UTF-8"));
            wr.writeBytes(jsonData);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int status = conn.getResponseCode();
    }
}