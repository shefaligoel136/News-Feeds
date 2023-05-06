package Interface;

import Model.CommentModel;
import Model.PostModel;

public interface Comment {
    CommentModel create(String commentText, String subjectId, CommentModel.SubjectType subjectType) throws Exception;
}
