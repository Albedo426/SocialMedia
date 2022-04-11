package com.example.gamingsmlogin.interfaces;

import com.example.gamingsmlogin.classes.BubblesReturn;
import com.example.gamingsmlogin.classes.CommentReturn;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.FilesReturn;
import com.example.gamingsmlogin.classes.GamesReturn;
import com.example.gamingsmlogin.classes.MessageReturn;
import com.example.gamingsmlogin.classes.OptionsReturn;
import com.example.gamingsmlogin.classes.PlayerFeaturesForProfileReturn;
import com.example.gamingsmlogin.classes.PostReturn;
import com.example.gamingsmlogin.classes.SourceReturn;
import com.example.gamingsmlogin.classes.UsersReturn;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IServices {

    final static String BASE_SERVICES = "services.php";

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<PostReturn> getpost(@Field("Method") String Method, @Field("userId") String userId, @Field("limit") String limit);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> getUser(@Field("Method") String Method, @Field("userId") String userId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> getUrl(@Field("Method") String Method, @Field("msgId") String msgId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> getUserPhoto(@Field("Method") String Method, @Field("userId") String userId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> userUpdate(@Field("Method") String Method,
                                  @Field("userId") String userId,
                                  @Field("name") String name,
                                  @Field("lastname") String lastname,
                                  @Field("userName") String userName,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("phone") String phone,
                                  @Field("profilePhoto") String profilePhoto
                                );


    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> addMessage(@Field("Method") String Method,
                         @Field("senderID") String senderID,
                         @Field("receiverID") String receiverID,
                         @Field("value") String value,
                         @Field("type") String type,
                         @Field("vis") String vis);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<BubblesReturn> getBubbles(@Field("Method") String Method, @Field("senderId") String senderId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<PostReturn> getPost(@Field("Method") String Method, @Field("userId") String userId, @Field("limit") String limit);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> removeFile(@Field("Method") String Method,
                         @Field("fileId") String fileId,
                         @Field("fileName") String fileName);

    @POST(BASE_SERVICES)
    @Multipart
    Call<ControlReturn> sendMessageFile(@Part("Method") String Method,
                              @Part MultipartBody.Part file,
                              @Part("file") RequestBody name,
                              @Part("senderID") String senderID,
                              @Part("receiverID") String receiverID,
                              @Part("value") String value,
                              @Part("type") String type,
                              @Part("vis") String vis);

    @POST(BASE_SERVICES)
    @Multipart
    Call<FilesReturn> addImages(@Part("Method") String Method,
                                @Part MultipartBody.Part file,
                                @Part("file") RequestBody name,
                                @Part("type") String type);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> getFriendUser(@Field("Method") String Method, @Field("userId") String userId, @Field("searchName") String searchName);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> postSend(@Field("Method") String Method,
                       @Field("userId") String userId,
                       @Field("text") String text,
                       @Field("dataIsNull") String dataIsNull,
                       @Field("filesId[]") ArrayList<String> filesId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> lastMessageControl(@Field("Method") String Method, @Field("userId") String userId, @Field("targetUserId") String targetUserId, @Field("messagesId") String messagesId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<FilesReturn> postDataGet(@Field("Method") String Method,
                                  @Field("postId") String postId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> postlikeControlSet(@Field("Method") String Method,
                                 @Field("userId") String userId,
                                 @Field("postId") String postId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<MessageReturn> getChat(@Field("Method") String Method, @Field("userId") String userId, @Field("targetUserId") String targetUserId, @Field("limit") String limit);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> addComment(@Field("Method") String Method,
                         @Field("connectId") String connectId,
                         @Field("userId") String userId,
                         @Field("isSubComment") String isSubComment,
                         @Field("value") String value
    );

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<CommentReturn> getComment(@Field("Method") String Method, @Field("connectId") String connectId, @Field("limit") String limit);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> getForLogin(@Field("Method") String method,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("token") String token);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> onCreateUser(@Field("Method") String method,
                                     @Field("name") String name,
                                     @Field("lastName") String lastName,
                                     @Field("birthDate") String birthDate,
                                     @Field("email") String email,
                                     @Field("userName") String userName,
                                     @Field("password") String password,
                                     @Field("phone") String phone);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> isDuplicate(@Field("Method") String method,
                                     @Field("key") String key,
                                     @Field("value") String value);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<OptionsReturn> options(@Field("Method") String method);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<GamesReturn> getAllGames(@Field("Method") String method);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<SourceReturn> getStats(@Field("Method") String method,
                                @Field("required") String required);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> playerFeaturesControl(@Field("Method") String method,
                                                 @Field("gameID") int gameID,
                                                 @Field("gUserName") String required);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> insertPlayerFeatures(@Field("Method") String method,
                                             @Field("option1") String option1,
                                             @Field("option2") String option2,
                                             @Field("about") String about,
                                             @Field("gameID") int gameID,
                                             @Field("gUserName") String required,
                                             @Field("source1") String source1,
                                             @Field("source2") String source2);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<PlayerFeaturesForProfileReturn> getPlayerFeaturesWithUserID(@Field("Method") String method,
                                                                     @Field("userId") String userId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> pendingFriendRequest(@Field("Method") String method,
                                             @Field("senderId") String senderId,
                                             @Field("receiverId") String receiverId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> addFriend(@Field("Method") String method,
                                             @Field("senderId") String senderId,
                                             @Field("receiverId") String receiverId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> deleteFriend(@Field("Method") String method,
                                             @Field("senderId") String senderId,
                                             @Field("receiverId") String receiverId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<ControlReturn> isFriend(@Field("Method") String method,
                                 @Field("senderId") String senderId,
                                 @Field("receiverId") String receiverId);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> detailedSearch(@Field("Method") String method,
                                            @Field("value") String value,
                                            @Field("whichMethod") String whichMethod);

    @POST(BASE_SERVICES)
    @FormUrlEncoded
    Call<UsersReturn> getFilteredUser(@Field("Method") String method,
                                      @Field("option1") String option1,
                                      @Field("option2") String option2,
                                      @Field("source1") String source1,
                                      @Field("source2") String source2);
}
