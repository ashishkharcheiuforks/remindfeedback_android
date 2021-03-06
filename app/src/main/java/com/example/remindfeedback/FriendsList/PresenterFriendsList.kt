package com.example.remindfeedback.FriendsList

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.remindfeedback.Network.RetrofitFactory
import com.example.remindfeedback.ServerModel.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PresenterFriendsList : ContractFriendsList.Presenter {


    lateinit override var view: ContractFriendsList.View
    lateinit override var context: Context

    override fun loadItems(list: ArrayList<ModelFriendsList>, adapterFriendsList: AdapterFriendsList) {
        list.clear()
        view.refresh()
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        val register_request: Call<GetFriends> = apiService.GetFriends()
        register_request.enqueue(object : Callback<GetFriends> {
            override fun onResponse(call: Call<GetFriends>, response: Response<GetFriends>) {
                if (response.isSuccessful) {
                    val fData: GetFriends = response.body()!!
                    val fList = fData.data
                    if (fList != null) {
                        for (i in 0 until fList.size) {
                            var myList: getFriendsInfo = getFriendsInfo()
                            myList = fList[i]
                            var addData: ModelFriendsList = ModelFriendsList(
                                myList.user_uid,
                                myList.id,
                                myList.nickname!!,
                                myList.introduction!!,
                                myList.portrait!!,
                                myList.type,
                                0
                            )
                            adapterFriendsList.addItem(addData)
                            view.refresh()
                        }
                    } else {
                    }
                } else {
                    Log.e("asdasdasd", "뭔가 실패함")
                }

            }

            override fun onFailure(call: Call<GetFriends>, t: Throwable) {
            }
        })
    }


    override fun receivedFriendRequests(list: ArrayList<ModelFriendsList>, adapterFriendsList: AdapterFriendsList) {
        list.clear()
        view.refresh()
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        val register_request: Call<GetFriends> = apiService.GetReceivedFriendRequest()
        register_request.enqueue(object : Callback<GetFriends> {
            override fun onResponse(call: Call<GetFriends>, response: Response<GetFriends>) {
                if (response.isSuccessful) {
                    val fData: GetFriends = response.body()!!
                    val fList = fData.data
                    if (fList != null) {
                        for (i in 0 until fList.size) {
                            var myList: getFriendsInfo = getFriendsInfo()
                            myList = fList[i]
                            var addData: ModelFriendsList = ModelFriendsList(
                                myList.user_uid,
                                myList.id,
                                myList.nickname!!,
                                myList.introduction!!,
                                myList.portrait!!,
                                myList.type,
                                1
                            )
                            adapterFriendsList.addItem(addData)
                            view.refresh()
                        }
                    } else {
                    }
                } else {
                }

            }

            override fun onFailure(call: Call<GetFriends>, t: Throwable) {
            }
        })
    }

    override fun requestedFriendsRequests(list: ArrayList<ModelFriendsList>, adapterFriendsList: AdapterFriendsList) {
        list.clear()
        view.refresh()
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        val register_request: Call<GetFriends> = apiService.GetRequestedFriendRequest()
        register_request.enqueue(object : Callback<GetFriends> {
            override fun onResponse(call: Call<GetFriends>, response: Response<GetFriends>) {
                if (response.isSuccessful) {
                    val fData: GetFriends = response.body()!!
                    val fList = fData.data
                    if (fList != null) {
                        for (i in 0 until fList.size) {
                            var myList: getFriendsInfo = getFriendsInfo()
                            myList = fList[i]
                            var addData: ModelFriendsList = ModelFriendsList(
                                myList.user_uid,
                                myList.id,
                                myList.nickname!!,
                                myList.introduction!!,
                                myList.portrait!!,
                                myList.type,
                                2
                            )
                            adapterFriendsList.addItem(addData)
                            view.refresh()
                        }
                    } else {
                    }
                } else {
                }

            }

            override fun onFailure(call: Call<GetFriends>, t: Throwable) {
            }
        })
    }


    override fun acceptRequest(list: ArrayList<ModelFriendsList>, user_uid: String, adapterFriendsList: AdapterFriendsList) {
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        var createFriend = CreateFriend(user_uid)
        val register_request: Call<SearchFriend> = apiService.CreateFriend(createFriend)
        register_request.enqueue(object : Callback<SearchFriend> {
            override fun onResponse(call: Call<SearchFriend>, response: Response<SearchFriend>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "친구요청이 수락되었습니다.", Toast.LENGTH_LONG).show()
                    list.clear()
                    receivedFriendRequests(list, adapterFriendsList)
                    view.refresh()
                } else {
                    val StatusCode = response.code()
                }
            }

            override fun onFailure(call: Call<SearchFriend>, t: Throwable) {
            }
        })
    }

    override fun rejectRequest(list: ArrayList<ModelFriendsList>, user_uid: String, friend_id: Int, friend_uid: String, adapterFriendsList: AdapterFriendsList) {
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        var rejectFreidn = RejectFriend(user_uid, friend_uid)
        val register_request: Call<SearchFriend> = apiService.RejectFriend(friend_id, rejectFreidn)
        register_request.enqueue(object : Callback<SearchFriend> {
            override fun onResponse(call: Call<SearchFriend>, response: Response<SearchFriend>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "친구요청이 거절되었습니다.", Toast.LENGTH_LONG).show()
                    list.clear()
                    receivedFriendRequests(list, adapterFriendsList)
                    view.refresh()
                } else {
                    val StatusCode = response.code()
                }
            }

            override fun onFailure(call: Call<SearchFriend>, t: Throwable) {
            }
        })
    }


    override fun blockRequest(list: ArrayList<ModelFriendsList>, user_uid: String, friend_id: Int, friend_uid: String, adapterFriendsList: AdapterFriendsList) {
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        var rejectFriend = RejectFriend(user_uid, friend_uid)
        val register_request: Call<SearchFriend> = apiService.BlockFriend(friend_id, rejectFriend)
        register_request.enqueue(object : Callback<SearchFriend> {
            override fun onResponse(call: Call<SearchFriend>, response: Response<SearchFriend>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "친구를 차단 했습니다.", Toast.LENGTH_LONG).show()
                    list.clear()
                    loadItems(list, adapterFriendsList)
                    view.refresh()
                } else {
                    val StatusCode = response.code()
                }
            }

            override fun onFailure(call: Call<SearchFriend>, t: Throwable) {
            }
        })
    }

    override fun unBlockRequest(list: ArrayList<ModelFriendsList>, user_uid: String, friend_id: Int, friend_uid: String, adapterFriendsList: AdapterFriendsList) {
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        var rejectFriend = RejectFriend(user_uid, friend_uid)
        val register_request: Call<SearchFriend> = apiService.UnBlockFriend(friend_id, rejectFriend)
        register_request.enqueue(object : Callback<SearchFriend> {
            override fun onResponse(call: Call<SearchFriend>, response: Response<SearchFriend>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "친구차단을 해제 했습니다.", Toast.LENGTH_LONG).show()
                    list.clear()
                    loadBlockedFriends(list, adapterFriendsList)
                    view.refresh()
                } else {
                    val StatusCode = response.code()
                }
            }

            override fun onFailure(call: Call<SearchFriend>, t: Throwable) {
            }
        })
    }

    override fun loadBlockedFriends(list: ArrayList<ModelFriendsList>, adapterFriendsList: AdapterFriendsList) {
        list.clear()
        view.refresh()
        val client: OkHttpClient = RetrofitFactory.getClient(context, "addCookie")
        val apiService = RetrofitFactory.serviceAPI(client)
        val register_request: Call<GetFriends> = apiService.GetBlockedFriends()
        register_request.enqueue(object : Callback<GetFriends> {
            override fun onResponse(call: Call<GetFriends>, response: Response<GetFriends>) {
                if (response.isSuccessful) {
                    val fData: GetFriends = response.body()!!
                    val fList = fData.data
                    if (fList != null) {
                        for (i in 0 until fList.size) {
                            var myList: getFriendsInfo = getFriendsInfo()
                            myList = fList[i]
                            var addData: ModelFriendsList = ModelFriendsList(
                                myList.user_uid,
                                myList.id,
                                myList.nickname!!,
                                myList.introduction!!,
                                myList.portrait!!,
                                myList.type,
                                3
                            )
                            adapterFriendsList.addItem(addData)
                            view.refresh()
                        }
                    } else {
                    }
                } else {
                    Log.e("asdasdasd", "뭔가 실패함")
                }

            }

            override fun onFailure(call: Call<GetFriends>, t: Throwable) {
            }
        })
    }

    override fun addItems(email: String, adapterFriendsList: AdapterFriendsList) {

    }

    override fun removeItems(position: Int, id: Int, context: Context) {
    }

    override fun updateItems(position: Int) {
    }


}