package Model;

import Utils.Utils;

public class ActivityModel {
    String id;

    String subjectId;
    String userId;

    String remark;
    public enum SubjectType {
        POST, USER, COMMENT;
    }

    public enum ActivityType {
        FOLLOW,UPVOTE,DOWNVOTE;

    }

    SubjectType subjectType;
    ActivityType activityType;

    public ActivityModel(String userId, SubjectType subjectType, String subjectId, ActivityType activityType, String remark){
        this.id = Utils.generateUUID();
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.activityType = activityType;
        this.userId = userId;
        this.remark = remark;
    }

    public SubjectType getSubjectType(){
        return this.subjectType;
    }

    public ActivityType getActivityType(){
        return this.activityType;
    }

    public String getSubjectId(){
        return this.subjectId;
    }

    public String getUserId(){
        return this.userId;
    }
}
