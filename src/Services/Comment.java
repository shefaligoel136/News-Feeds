package Services;

import Model.ActivityModel;
import Model.CommentModel;
import Model.PostModel;
import Utils.Utils;

import java.util.HashMap;

public class Comment implements Interface.Comment {
    public static HashMap<String, CommentModel> commentMap = new HashMap<>();

    String uuid;

    @Override
    public CommentModel create(String commentText, String subjectId, CommentModel.SubjectType subjectType ) throws Exception {

        String userId = User.getUserIdFromSession();

        do {
            this.uuid = Utils.generateUUID();
        }
        while (commentMap.containsKey(this.uuid));

        CommentModel newComment = new CommentModel(this.uuid, commentText, userId, subjectId,subjectType);

        commentMap.put(this.uuid, newComment);

        System.out.println("COMMENT CREATED" + " " + this.uuid + " " + commentText);

        return newComment;
    }


}
