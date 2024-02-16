package android.kotlin.practice.network

import com.google.gson.annotations.SerializedName


data class VideoBean(
    /**
     * 视频图片地址
     */
    @SerializedName("cover")
    var cover: String? = null,

    /**
     * 视频的标题
     */
    @SerializedName("title")
    var title: String? = null,
    /**
     * 视频播放次数
     */
    @SerializedName("playCount")
    var playCount: String? = null,
    /**
     * m3u8 标清地址
     */
    @SerializedName("m3u8_url")
    var m3u8_url: String? = null,
    /**
     * m3u8高清地址
     */
    @SerializedName("m3u8Hd_url")
    var m3u8Hd_url: String? = null,
    /**
     * mp4地址
     */
    @SerializedName("mp4_url")
    var mp4_url: String? = null,
    /**
     * 视频时长
     */
    @SerializedName("length")
    var length: String? = null,


    /**
     * 谁发布的
     */
    @SerializedName("topicName")
    var topicName: String? = null,


    @SerializedName("sectiontitle")
    var sectiontitle: String? = null,

)