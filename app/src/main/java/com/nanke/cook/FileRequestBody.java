package com.nanke.cook;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;

/**
 * Created by admin on 16/11/5.
 */
public class FileRequestBody extends RequestBody{

    RequestBody requestBody;



    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        new ForwardingSink(sink){
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
            }
        };
    }
}
