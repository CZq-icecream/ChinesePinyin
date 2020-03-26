package com.czq.chinesepinyin.entity;

/**
 * 代表详细的一条记录
 * @date 2020.3.20
 * @author czq
 */
public class Detail {

    /**
     * 课程编号
     */
    private Integer lessonId;
    /**
     * 课程进度
     */
    private Integer index;
    /**
     * 中文图片路径（url）
     */
    private String chinesePath;
    /**
     * 音频路径（url）
     */
    private String audioPath;
    /**
     * 描述图片路径（url）
     */
    private String illustrationPath;
    /**
     * 中文意义路径（url）
     */
    private String meaningPath;
    /**
     * 视频路径（url）
     */
    private String vedioPath;

    public Detail(Integer lessonId, Integer index, String chinesePath, String audioPath, String illustrationPath, String meaningPath, String vedioPath) {
        this.lessonId = lessonId;
        this.index = index;
        this.chinesePath = chinesePath;
        this.audioPath = audioPath;
        this.illustrationPath = illustrationPath;
        this.meaningPath = meaningPath;
        this.vedioPath = vedioPath;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "lessonId=" + lessonId +
                ", index=" + index +
                ", chinesePath='" + chinesePath + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", illustrationPath='" + illustrationPath + '\'' +
                ", meaningPath='" + meaningPath + '\'' +
                ", vedioPath='" + vedioPath + '\'' +
                '}';
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getChinesePath() {
        return chinesePath;
    }

    public void setChinesePath(String chinesePath) {
        this.chinesePath = chinesePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getIllustrationPath() {
        return illustrationPath;
    }

    public void setIllustrationPath(String illustrationPath) {
        this.illustrationPath = illustrationPath;
    }

    public String getMeaningPath() {
        return meaningPath;
    }

    public void setMeaningPath(String meaningPath) {
        this.meaningPath = meaningPath;
    }

    public String getVedioPath() {
        return vedioPath;
    }

    public void setVedioPath(String vedioPath) {
        this.vedioPath = vedioPath;
    }
}
