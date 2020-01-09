package com.example.remindfeedback.Network

import com.example.remindfeedback.ServerModel.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST


interface ServiceAPI {

    //회원가입
    @POST("auth/signup")
    fun SignUp(@Body json_account: SignUp): Call<SignUp>

    //로그인
    @POST("auth/login")
    fun LogIn(@Body login: LogIn): Call<ResponseBody>

    //내정보 가져오기
    @GET("auth/me/")
    fun GET_User(): Call<ResponseBody>

    //피드백 생성
    @POST("feedback/create")
    fun CreateFeedback(@Body createFeedback: CreateFeedback): Call<CreateFeedback>

    //내가 요청한 피드백정보 가져오기
    @GET("feedback/my/{start}")
    fun GetFeedback(@Path("start") start: Int): Call<GetFeedback>

    //피드백정보 모두 가져오기
    @GET("feedback/all/{start}")
    fun GetAllFeedback(@Path("start") start: Int): Call<GetAllFeedback>

    // 피드백 수정
    @PUT("feedback/update/{feedback_id}")
    fun ModifyFeedback(@Path("feedback_id") feedback_id: Int, @Body createFeedback: CreateFeedback): Call<CreateFeedback>

    //피드백 삭제
    @DELETE("feedback/{feedback_id}")
    fun DeleteFeedback(@Path("feedback_id") feedback_id: Int): Call<ResponseBody>

    //내 주제 정보 가져오기
    @GET("category/selectall")
    fun GetCategory(): Call<GetCategory>

    //주제 생성
    @POST("category/insert")
    fun CreateCategory(@Body createCategory: CreateCategory): Call<GetCategory>

    // 주제 수정
    @POST("category/update/{category_id}")
    fun ModifyCategory(@Path("category_id") category_id: Int, @Body createCategory: CreateCategory): Call<GetCategory>

    //주제 삭제
    @DELETE("category/deleteone/{category_id}")
    fun DeleteCategory(@Path("category_id") category_id: Int): Call<ResponseBody>


    //내 정보 가져오기
    @GET("mypage")
    fun GetMyPage(): Call<GetMyPage>

    //내 닉네임 수정하기
    @FormUrlEncoded
    @PATCH("mypage/update/nickname")
    fun PatchNickname(@Field("nickname") nickname: String): Call<GetMyPage>

    //내 상태메세지 수정하기
    @FormUrlEncoded
    @PATCH("mypage/update/introduction")
    fun PatchIntoduction(@Field("introduction") introduction: String?): Call<GetMyPage>

    //내 프로필 이미지 수정하기
    @Multipart
    @PATCH("mypage/update/portrait")
    fun PatchPortrait(@Part portrait: MultipartBody.Part?): Call<GetMyPage>

    //하나의 피드백 내부 포스트들 가져오기
    @GET("board/{feedbackid}/0")
    fun GetAllBoard(@Path("feedbackid") feedback_id: Int): Call<GetAllBoard>

    //글타입의 보드 생성
    @POST("board/text/create")
    fun CreateBoardText(@Body createCategory: CreateBoardText): Call<GetAllBoard>

    //사진타입의 보드 생성
    @Multipart
    @POST("board/picture/create")
    fun CreateBoardPictue(
        @Part("feedback_id") feedback_id: RequestBody,
        @Part("board_title") board_title: RequestBody,
        @Part("board_content") board_content: RequestBody,
        @Part file1: MultipartBody.Part?,
        @Part file2: MultipartBody.Part?,
        @Part file3: MultipartBody.Part?
    ): Call<GetAllBoard>

    //댓글 생성
    @POST("comment/create")
    fun CreateComment(@Body createComment: CreateComment): Call<CreateComment>

    //각 게시글의 댓글 가져오기
    @GET("comment/selectall/{board_id}")
    fun GetAllComment(@Path("board_id") board_id: Int): Call<GetAllComments>

}