package Model;

import Services.Comment;
import Services.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PostModel {

    static String id;
    String postData;
    String userId;

    long createdAt;

    ArrayList<ActivityModel> postUpVote = new ArrayList<>();
    ArrayList<ActivityModel> postDownVote = new ArrayList<>();

    public PostModel(String id, String postData, String userId){
        this.id = id;
        this.postData = postData;
        this.userId = userId;
        this.createdAt = System.currentTimeMillis();
    }

    public String getUserId(){
        return this.userId;
    }


    public HashMap<String, Object> getPostInfo(){
        List<CommentModel> comments = Comment.commentMap.values().stream().filter(data -> data.getSubjectId().equals(PostModel.id)).collect(Collectors.toList());
        List<ActivityModel> upVotes = Vote.voteMap.stream().filter(data -> data.getSubjectId().equals(PostModel.id) && data.getActivityType().equals(ActivityModel.ActivityType.UPVOTE)).collect(Collectors.toList());
        List<ActivityModel> downVotes = Vote.voteMap.stream().filter(data -> data.getSubjectId().equals(PostModel.id) && data.getActivityType().equals(ActivityModel.ActivityType.DOWNVOTE)).collect(Collectors.toList());

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("comments" , comments);
        dataMap.put("upVotes", upVotes.size());
        dataMap.put("downVotes", downVotes.size());
        dataMap.put("post",this.postData);

        return dataMap;
    }

    public long getTimestamp() {
        return createdAt;
    }



}
