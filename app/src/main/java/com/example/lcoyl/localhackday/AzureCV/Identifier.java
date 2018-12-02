package com.example.lcoyl.localhackday.AzureCV;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;

public abstract class Identifier {
    private final String api_key = "699c3c58d8aa4194b3aa198dcb6c5ba7";
    protected final String endpoint = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/";
    protected abstract String getEndPoint();
    protected abstract URIBuilder addParamaters(URIBuilder builder);

    public JSONObject requestData(File image){
        HttpClient httpclient = HttpClients.createDefault();
        JSONObject jsonRes = null;
        try {
            URIBuilder builder = new URIBuilder(getEndPoint());

            builder = addParamaters(builder);

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            //header for image
            request.setHeader("Content-Type", "application/octet-stream");
            //header for url
            request.setHeader("Ocp-Apim-Subscription-Key", api_key);

            // Request body For File
            request.setEntity(new FileEntity(image));

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();



            jsonRes = new JSONObject(EntityUtils.toString(entity));

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonRes;
    }

    public boolean isImage(File image){
        return true;
    }

}