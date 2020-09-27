package com.rcdz.mykotlindemo.model.bean

import java.io.Serializable

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
class NewsBean : Serializable {
    /**
     * date : 20200910
     * stories : [{"image_hue":"0x151f1a","title":"为什么存在那么多动物自己不捕猎，光捡现成的？","url":"https://daily.zhihu.com/story/9727713","hint":"动物志 · 9 分钟阅读","ga_prefix":"091007","images":["https://pic1.zhimg.com/v2-29053087dd9d39a0f946ca1a5e6a5fe2.jpg?source=8673f162"],"type":0,"id":9727713},{"image_hue":"0x5774b3","title":"为什么国家对非洲提供大量的贷款，而不用这些钱去建设西部？","url":"https://daily.zhihu.com/story/9727737","hint":"枫冷慕诗 · 4 分钟阅读","ga_prefix":"091007","images":["https://pic3.zhimg.com/v2-eea1f6668625e70b00acfdf5fb3429d6.jpg?source=8673f162"],"type":0,"id":9727737},{"image_hue":"0x42352e","title":"87 版《红楼梦》的妆容水平如何？","url":"https://daily.zhihu.com/story/9727728","hint":"女王蒹葭大无敌 · 2 分钟阅读","ga_prefix":"091007","images":["https://pic3.zhimg.com/v2-c690529cfd67f2c871d2514a768d3d2f.jpg?source=8673f162"],"type":0,"id":9727728},{"image_hue":"0x3d3952","title":"为什么东北话传染性这么大？ ","url":"https://daily.zhihu.com/story/9727742","hint":"小云哥哥 · 1 分钟阅读","ga_prefix":"091007","images":["https://pic3.zhimg.com/v2-9b1749d390768e310f55c9c070f2ff19.jpg?source=8673f162"],"type":0,"id":9727742},{"image_hue":"0x30361d","title":"为什么靶向药不能治愈只能延续？","url":"https://daily.zhihu.com/story/9727719","hint":"程灵熙 · 2 分钟阅读","ga_prefix":"091007","images":["https://pic2.zhimg.com/v2-841137e3108a6322f57d0ddcf0e46c9a.jpg?source=8673f162"],"type":0,"id":9727719},{"image_hue":"0x967db3","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9727712","hint":"VOL.2481","ga_prefix":"091006","images":["https://pic3.zhimg.com/v2-1bfd72fe7d684bedbb77912befaa9018.jpg?source=8673f162"],"type":0,"id":9727712}]
     * top_stories : [{"image_hue":"0x807459","hint":"作者 / 和尚洗头用飘柔","url":"https://daily.zhihu.com/story/9727680","image":"https://pic2.zhimg.com/v2-aa8d171aaf41d53f096c75afdc99c3c2.jpg?source=8673f162","title":"第一次去空间站的话，怎么样装作经常去的样子？","ga_prefix":"090907","type":0,"id":9727680},{"image_hue":"0x3f4a34","hint":"作者 / linyi812","url":"https://daily.zhihu.com/story/9727658","image":"https://pic2.zhimg.com/v2-765261bfab6165328a721c8e539a3e10.jpg?source=8673f162","title":"世界考古发现中有没有什么重大发现让人觉得不真实？","ga_prefix":"090807","type":0,"id":9727658},{"image_hue":"0x222222","hint":"作者 / Dr.X","url":"https://daily.zhihu.com/story/9727630","image":"https://pic4.zhimg.com/v2-0cfdbb81bccea7cc0f2e883a30a30cf4.jpg?source=8673f162","title":"人类身体存在哪些 bug？","ga_prefix":"090707","type":0,"id":9727630},{"image_hue":"0xb3987d","hint":"作者 / 五莲花开","url":"https://daily.zhihu.com/story/9727536","image":"https://pic3.zhimg.com/v2-c006180442ad968adedb7fba22a227e5.jpg?source=8673f162","title":"为什么人们通常只吃大型鲨鱼的鳍，而不吃其他部分？","ga_prefix":"090407","type":0,"id":9727536},{"image_hue":"0x8b602a","hint":"作者 / 王介南","url":"https://daily.zhihu.com/story/9727473","image":"https://pic2.zhimg.com/v2-4aa73c5e71c1422592c9cf8a6de495d2.jpg?source=8673f162","title":"彩蛋可以有多精美？（这是真彩蛋）","ga_prefix":"090207","type":0,"id":9727473}]
     */
    var date: String? = null
    var stories: List<StoriesBean>? = null
    var top_stories: List<TopStoriesBean>? = null

    class StoriesBean : Serializable {
        /**
         * image_hue : 0x151f1a
         * title : 为什么存在那么多动物自己不捕猎，光捡现成的？
         * url : https://daily.zhihu.com/story/9727713
         * hint : 动物志 · 9 分钟阅读
         * ga_prefix : 091007
         * images : ["https://pic1.zhimg.com/v2-29053087dd9d39a0f946ca1a5e6a5fe2.jpg?source=8673f162"]
         * type : 0
         * id : 9727713
         */
        var image_hue: String? = null
        var title: String? = null
        var url: String? = null
        var hint: String? = null
        var ga_prefix: String? = null
        var type = 0
        var id = 0
        var images: List<String>? = null
    }

    class TopStoriesBean : Serializable {
        /**
         * image_hue : 0x807459
         * hint : 作者 / 和尚洗头用飘柔
         * url : https://daily.zhihu.com/story/9727680
         * image : https://pic2.zhimg.com/v2-aa8d171aaf41d53f096c75afdc99c3c2.jpg?source=8673f162
         * title : 第一次去空间站的话，怎么样装作经常去的样子？
         * ga_prefix : 090907
         * type : 0
         * id : 9727680
         */
        var image_hue: String? = null
        var hint: String? = null
        var url: String? = null
        var image: String? = null
        var title: String? = null
        var ga_prefix: String? = null
        var type = 0
        var id = 0
    }
}