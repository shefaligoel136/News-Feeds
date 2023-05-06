package Services;

import Model.ActivityModel;
import Model.PostModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Vote implements Interface.Vote {
    public static ArrayList<ActivityModel> voteMap = new ArrayList<>();

    @Override
    public void updateVote(ActivityModel.SubjectType subjectType, String subjectId, boolean isVoteCreated) throws Exception {

        String userId = User.getUserIdFromSession();

        boolean actionPerformed;

        if (isVoteCreated) {
            actionPerformed = voteMap.removeIf(data -> data.getSubjectType().equals(subjectType) && data.getSubjectId().equals(subjectId) && data.getUserId().equals(userId) && data.getActivityType().equals(ActivityModel.ActivityType.DOWNVOTE));
        } else {
            actionPerformed = voteMap.removeIf(data -> data.getSubjectType().equals(subjectType) && data.getSubjectId().equals(subjectId) && data.getUserId().equals(userId) && data.getActivityType().equals(ActivityModel.ActivityType.UPVOTE));
        }


        if (actionPerformed) {
            System.out.println("VOTE UPDATED");
            return;
        }

        List<ActivityModel> vote = Vote.voteMap.stream().filter(data -> data.getSubjectType().equals(subjectType) && data.getSubjectId().equals(subjectId) && data.getUserId().equals(userId) && data.getActivityType().equals(isVoteCreated ? ActivityModel.ActivityType.UPVOTE : ActivityModel.ActivityType.DOWNVOTE)).collect(Collectors.toList());

        if(vote.size()>=1){
            System.out.println("YOU HAVE ALREADY PERFORMED THIS ACTION");
            return;
        }

        ActivityModel newVote = new ActivityModel(userId, subjectType, subjectId, isVoteCreated ? ActivityModel.ActivityType.UPVOTE : ActivityModel.ActivityType.DOWNVOTE, null);

        voteMap.add(newVote);
        System.out.println("NEW VOTE CREATED");
    }

}
