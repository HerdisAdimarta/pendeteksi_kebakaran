package com.skripsi.pendeteksikebakaran.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;

@Generated("org.jsonschema2pojo")
public
@Data
class ApiBasic {

    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("severity")
    @Expose
    String severity;

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
