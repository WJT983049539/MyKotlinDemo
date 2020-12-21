package com.rcdz.mykotlindemo.designtest

import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.bean.NewsBean
import com.rcdz.mykotlindemo.persenter.GetNewsInfo
import com.rcdz.mykotlindemo.persenter.NetPersenter
import com.wjt.mylibrary.base.BaseActivity
import com.rcdz.mykotlindemo.view.activity.RomTestActivity
import kotlinx.android.synthetic.main.drawerlayout.*


/**
 * 侧滑栏
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
class DrawerLayoutActivity : com.wjt.mylibrary.base.BaseActivity() , GetNewsInfo {
    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mNavigation: NavigationView
    internal var adapter: MyAdapter? = null
    val items = arrayListOf<NewsBean.StoriesBean>()
    override fun initData() {
        val net= NetPersenter(this@DrawerLayoutActivity);
        net.getNewsInfo(this)

        srl_activity_main.setColorSchemeResources(R.color.colorPrimary)
        //设置下拉属性监听器
        //设置下拉属性监听器
        srl_activity_main.setOnRefreshListener(OnRefreshListener {
            //调用initData方法获取数据
            net.getNewsInfo(this)
        })


    }

    override fun initView() {
//        mNavigation=findViewById(R.id.nav_activity_main)
        //设置默认选中
        nav_activity_main.setCheckedItem(R.id.friends)
        nav_activity_main.setNavigationItemSelectedListener { item: MenuItem ->
            //关闭弹出菜单
            when (item.itemId) {
                R.id.friends -> drawerLayout.closeDrawers()

                R.id.example2 ->   this@DrawerLayoutActivity.startActivity(Intent(this@DrawerLayoutActivity, RomTestActivity::class.java))
            }
            true
        }

        fab_activity_main.setOnClickListener{
            //setAction来指定操作  第一个参数是操作名称  第二个参数是点击事件的处理逻辑
            Snackbar.make(it, "删除数据", Snackbar.LENGTH_SHORT).setAction("确定",
                object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        Toast.makeText(this@DrawerLayoutActivity, "删除成功", Toast.LENGTH_SHORT).show()
                    }
                }).show()
        }


        mDrawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
//        toolbar.inflateMenu(R.menu.actionbar)//添加menu
        //将mtoolbar设置为actionbar
        setSupportActionBar(toolbar)
        //显示应用的Logo
        val bar: ActionBar? = getSupportActionBar()
        bar?.let { bb ->   //内联函数
            bar?.setDisplayShowTitleEnabled(true);
            // 显示导航按钮
            toolbar.setNavigationIcon(R.drawable.menu);
            toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        //创建适配器  并传入模拟的数据
        //创建适配器  并传入模拟的数据
         adapter = MyAdapter(items)
        //设置显示格式 2列
        //设置显示格式 2列
        val layoutParams = LinearLayoutManager(this)
//        val layoutParams = GridLayoutManager(this,2)
        //将显示格式传给mRecyclerView
        //将显示格式传给mRecyclerView
        datalist.setLayoutManager(layoutParams)
        //设置适配器
        //设置适配器
        datalist.setAdapter(adapter)
    }

    override fun setNowActivityName(): String {
        return "DrawerLayoutActivity"
    }

    override fun setLayout(): Int {
        return R.layout.drawerlayout
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                //弹出DrawerLayout菜单，参数为弹出的方式
                drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup ->

                Toast.makeText(this@DrawerLayoutActivity, "这是菜单按钮", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //获取menu的注入器(Inflater)并将我们配置的toolbar文件加载到menu中即可
        menuInflater.inflate(R.menu.actionbar, menu);
        //改变menu的颜色
        if (menu != null) {
            for (index in 0 until menu.size()) {
                val spannableString = SpannableString(menu.getItem(index).title)
                spannableString.setSpan(
                    ForegroundColorSpan(Color.parseColor("#2196F3")),
                    0,
                    spannableString.length,
                    0
                )
                menu.getItem(index).title = spannableString
            }
        }

        return true
    }
    //得到数据
    override fun getNewsInfo(newsBean: NewsBean?) {
        Log.i("test", "")
        items.clear()
        newsBean?.stories?.let { items.addAll(it) } //添加数据
        adapter?.notifyDataSetChanged()
        //关闭刷新提示
        srl_activity_main.setRefreshing(false);
    }
}

internal class MyAdapter(private val mLists: ArrayList<NewsBean.StoriesBean>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_item,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView.text =mLists.get(position).title
        val into: Any = Glide.with(holder.itemView.context)
            .load(mLists.get(position).images!!.get(0))
            .into(holder.mImageView)
    }

    override fun getItemCount(): Int {
        return mLists.size
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextView: TextView
        val mImageView:ImageView
        init {
            mTextView = itemView.findViewById(R.id.tv_layout_item)
            mImageView=itemView.findViewById(R.id.item_img)
        }
    }
}

