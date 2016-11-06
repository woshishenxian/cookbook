package com.nanke.cook.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by admin on 16/11/5.
 */
public interface FileService {

    @Multipart
    @POST("/fileabout.php")
    Call<String> upload(@Part("fileName") String des,@Part("file\"; filename=\"1.txt") RequestBody file);
}

