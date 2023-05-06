package Services;

import Model.ActivityModel;

import java.util.ArrayList;

public class Vote implements Interface.Vote {
    public static ArrayList<ActivityModel> voteMap = new ArrayList<>();

    @Override
    public void updateVote(ActivityModel.SubjectType subjectType, String subjectId, boolean isVoteCreated) throws Exception {

        String userId = User.getUserIdFromSession();

        boolean actionPerformed;

        if (isVoteCreated) {
            actionPerformed = voteMap.removeIf(data -> data.getSubjectType() == subjectType && data.getSubjectId() == subjectId && data.getUserId() == userId && data.getActivityType() == ActivityModel.ActivityType.DOWNVOTE);
        } else {
            actionPerformed = voteMap.removeIf(data -> data.getSubjectType() == subjectType && data.getSubjectId() == subjectId && data.getUserId() == userId && data.getActivityType() == ActivityModel.ActivityType.UPVOTE);
        }

        if (actionPerformed) {
            System.out.println("VOTE ");
            return;
        }

        ActivityModel newVote = new ActivityModel(userId, subjectType, subjectId, isVoteCreated ? ActivityModel.ActivityType.UPVOTE : ActivityModel.ActivityType.DOWNVOTE, null);

        voteMap.add(newVote);
        System.out.println(" UPDATED" + subjectType + newVote.getActivityType());
    }

}
