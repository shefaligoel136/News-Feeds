package Services;

import Model.ActivityModel;
import Model.UserModel;
import Model.UserSession;
import Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class User implements Interface.User {

    public static String sessionId;
    HashMap<String, UserModel> userMap = new HashMap<>();
    static HashMap<String, UserSession> userSessionHashMap = new HashMap<>();

    static ArrayList<ActivityModel> following = new ArrayList<>();

    String uuid;

    public static String getUserIdFromSession() throws Exception {
        UserSession userSessionInfo = userSessionHashMap.get(sessionId);
        return userSessionInfo.getUserIdFromSession();
    }

    public String generateUserSession(String userId) {

        do {
            sessionId = Utils.generateUUID();
        }
        while (userSessionHashMap.containsKey(sessionId));

        UserSession newUserSession = new UserSession(userId);

        userSessionHashMap.put(sessionId, newUserSession);

        System.out.println("SESSION CREATED WITH ID" + " " + sessionId);

        return sessionId;
    }

    @Override
    public String SignUp(String firstName, String lastName, String email, String password) {
        do {
            this.uuid = Utils.generateUUID();
        }
        while (userMap.containsKey(this.uuid));

        //        TODO: Password hashing
        UserModel newUser = new UserModel(this.uuid, firstName, lastName, email.toLowerCase(), password);

        userMap.put(this.uuid, newUser);
        generateUserSession(this.uuid);
        System.out.println("Success SignUp" + " " + this.uuid);
        return sessionId;
    }

    @Override
    public String LogIn(String email, String password) throws Exception {
        UserModel user = Utils.getUserWithEmail(email.toLowerCase(), userMap);

        if (user.getUserPassword().equals(password)) {
            generateUserSession(user.getUserId());
            System.out.println("Success LogIn" + " " + this.uuid);
            return sessionId;
        }

        throw new Exception("Invalid User Password");
    }

    @Override
    public void FollowUser(String followUserId) throws Exception {
        String userId = User.getUserIdFromSession();

        UserModel followingUserInstance = userMap.get(followUserId);

        if (followingUserInstance == null) {
            throw new Exception("User with the given id does not exists");
        }

        ActivityModel newFollow = new ActivityModel(userId, ActivityModel.SubjectType.USER, followUserId, ActivityModel.ActivityType.FOLLOW,null);

        following.add(newFollow);

        System.out.println("Started following" + followingUserInstance.getUserEmail());

    }

    public static List<String> getUserFollowings() throws Exception {
        String userId = User.getUserIdFromSession();
        List<String> userFollowings = following.stream().filter(data -> data.getActivityType() == ActivityModel.ActivityType.FOLLOW && data.getSubjectType() == ActivityModel.SubjectType.USER && data.getUserId() == userId).map(ActivityModel::getSubjectId).collect(Collectors.toList());
        return userFollowings;
    }



}
