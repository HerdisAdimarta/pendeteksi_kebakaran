package android.skripsi.pendeteksikebakaran.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;

@Generated("org.jsonschema2pojo")
public
@Data
class ApiBasic {

    @SerializedName("err_msg")
    @Expose
    String message;
    @SerializedName("err_code")
    @Expose
    int error_code;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("value")
    @Expose
    String value;
}
