package Model;

import Services.Comment;
import Services.Vote;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommentModel {
    static String id;
    String commentText;
    String userId;
    String subjectId;
    long createdAt;

    public enum SubjectType {
        POST, COMMENT;
    }

    SubjectType subjectType;

    public CommentModel(String id, String commentText, String userId, String subjectId, SubjectType subjectType) {
        this.id = id;
        this.commentText = commentText;
        this.userId = userId;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.createdAt = System.currentTimeMillis();
    }

    public String getUserId(){
        return this.userId;
    }

    public String getSubjectId(){
        return this.subjectId;
    }

    public List<ActivityModel> getCommentUpVotes(){
        List<ActivityModel> upVotes = Vote.voteMap.stream().filter(data -> data.getSubjectId() == CommentModel.id && data.getActivityType() == ActivityModel.ActivityType.UPVOTE).collect(Collectors.toList());
        return upVotes;
    }

    public List<ActivityModel> getCommentDownVotes(){
        List<ActivityModel> downVotes = Vote.voteMap.stream().filter(data -> data.getSubjectId() == CommentModel.id && data.getActivityType() == ActivityModel.ActivityType.DOWNVOTE).collect(Collectors.toList());
        return downVotes;
    }

    public List<CommentModel> getNestedComment(){
        return Comment.commentMap.values().stream().filter(data-> data.getSubjectId() == CommentModel.id).collect(Collectors.toList());
    }

}
