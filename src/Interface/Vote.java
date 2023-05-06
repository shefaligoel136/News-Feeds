package Interface;

import Model.ActivityModel;

public interface Vote {
    void updateVote(ActivityModel.SubjectType subjectType, String subjectId, boolean isVoteCreated) throws Exception;
}
