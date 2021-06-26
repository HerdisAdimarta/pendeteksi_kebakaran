package com.skripsi.pendeteksikebakaran.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Data;

@Generated("org.jsonschema2pojo")
public
@Data
class GetRecent {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("api")
    @Expose
    String api;
    @SerializedName("asap")
    @Expose
    Integer asap;
    @SerializedName("datetime")
    @Expose
    String datetime;

}
