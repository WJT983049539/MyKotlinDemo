package com.rcdz.mykotlindemo.model.bean

/**
 * 下拉刷新的bean数据
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
class UserDataLoadMore {
    //lateinit 不需要初始值的话需要使用这个关键字
    lateinit var userName:String
    var age:Int=0
    constructor(userName:String,age:Int){
        this.age=age
        this.userName=userName
    }

}