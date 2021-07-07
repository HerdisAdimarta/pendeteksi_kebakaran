package com.skripsi.pendeteksikebakaran.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;

@Generated("org.jsonschema2pojo")
public
@Data
class ApiControl {
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
}
