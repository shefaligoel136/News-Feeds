import Model.ActivityModel;
import Model.CommentModel;
import Services.Comment;
import Services.Post;
import Services.User;
import Services.Vote;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        User userInitialize = new User();
        Post postInitialize = new Post();
        Comment commentInitialize = new Comment();
        Vote voteInitialize = new Vote();

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            String command = tokens[0];

            if (command.equals("signup")) {
                String firstName = tokens[1];
                String lastName = tokens[2];
                String email = tokens[3];
                String password = tokens[4];
                userInitialize.SignUp(firstName, lastName, email, password);
            } else if (command.equals("login")) {
                String email = tokens[1];
                String password = tokens[2];
                userInitialize.LogIn(email, password);
            } else if (command.equals("post")) {
                String postData = tokens[1];
                postInitialize.create(postData);
            } else if (command.equals("follow")) {
                String followUserId = tokens[1];
                userInitialize.FollowUser(followUserId);
            } else if (command.equals("reply")) {
                String commentText = tokens[1];
                String subjectId = tokens[2];
                CommentModel.SubjectType subjectType = CommentModel.SubjectType.valueOf(tokens[3]);
                commentInitialize.create(commentText, subjectId, subjectType);
            } else if (command.equals("upvote")) {
                ActivityModel.SubjectType subjectType = ActivityModel.SubjectType.valueOf(tokens[1]);
                String subjectId = tokens[2];
                voteInitialize.updateVote(subjectType,subjectId, true);
            } else if (command.equals("downvote")) {
                ActivityModel.SubjectType subjectType = ActivityModel.SubjectType.valueOf(tokens[1]);
                String subjectId = tokens[2];
                voteInitialize.updateVote(subjectType,subjectId, false);
            } else if (command.equals("showFeeds")) {
                String sortOption = tokens[1];
                postInitialize.getPosts(sortOption);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
        scanner.close();
    }
}