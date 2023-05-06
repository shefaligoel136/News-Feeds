package Interface;

import Model.PostModel;

public interface Post {
    PostModel create(String postData) throws Exception;
}
