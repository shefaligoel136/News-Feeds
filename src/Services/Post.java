package Services;

import Model.PostModel;
import Utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Post implements Interface.Post {

    static HashMap<String, PostModel> postMap = new HashMap<>();

    String uuid;

    @Override
    public PostModel create(String postData) throws Exception {

        String userId = User.getUserIdFromSession();

        do {
            this.uuid = Utils.generateUUID();
        }
        while (postMap.containsKey(this.uuid));

        PostModel newPost = new PostModel(this.uuid, postData, userId);

        postMap.put(this.uuid, newPost);

        System.out.println("POST CREATED" + " " + this.uuid + " " + postData);

        return newPost;
    }




    public List<HashMap<String, Object>> getPosts(String sortOption) throws Exception {
        String userId = User.getUserIdFromSession();

        List<String> userFollowings = User.getUserFollowings();

        userFollowings.add(userId);

//        Set<String> checkArraySet = new HashSet<>(userFollowings);

        List<HashMap<String, Object>> postFeed = postMap.values().stream().filter(data -> userFollowings.contains(data.getUserId())).map(PostModel::getPostInfo).collect(Collectors.toList());
        switch (sortOption) {
            case "following":
                break;
            case "score":
                break;
            case "comments":
                break;
            case "timestamp":
                break;
            default:
                for (HashMap<String, Object>entry : postFeed) {
                    for (String key : entry.keySet()) {
                        Object value = entry.get(key);
                        System.out.println(key + ":" + value);
                    }
                }
        }

        return postFeed;
    }


}
