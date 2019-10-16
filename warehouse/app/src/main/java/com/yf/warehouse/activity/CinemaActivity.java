package com.yf.warehouse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yf.warehouse.R;
import com.yf.warehouse.adapter.CinemaAdapter;
import com.yf.warehouse.data.Cinema;
import com.yf.warehouse.interfaces.ItemClickCallback;
import com.yf.warehouse.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjq on 2019/10/14
 * <个人影院>
 * 掌握通用RecyclerView对应adapter用法，RecyclerView列表用法，List对象数据与adapter数据绑定
 * RecyclerView列表点击，接口使用，界面跳转时数据传递 ，布局排版，播放控件使用等知识。
 */
public class CinemaActivity extends Activity implements ItemClickCallback {
    private List<Cinema> cinemaList;
    private RecyclerView recyclerView;
    private CinemaAdapter cinemaAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initDatas();
        cinemaAdapter = new CinemaAdapter(this,this,cinemaList);
        recyclerView.setAdapter(cinemaAdapter);
    }

    private void initDatas(){
        cinemaList = new ArrayList<>();
        Cinema cinema0 = new Cinema();
        cinema0.image = R.mipmap.saodu;
        cinema0.name = "扫毒2";
        cinema0.decrip = "影片以“毒品”为线索，讲述了由刘德华饰演的慈善家兼金融巨子余顺天与古天乐饰演的“香港最大毒贩”地藏之间由“禁毒”引发的一场天地对决。";
        cinema0.url = "https://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4";
        cinemaList.add(cinema0);

        Cinema cinema1 = new Cinema();
        cinema1.image = R.mipmap.yewen;
        cinema1.name = "叶问4";
        cinema1.decrip = "该片讲述了远赴美国开馆的李小龙因授武予洋人得罪当地人马，叶问现身美国唐人街，引导小龙以武震慑整个唐人街，以德令华洋各路折服的故事。";
        cinema1.url = "https://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4";
        cinemaList.add(cinema1);

        Cinema cinema2 = new Cinema();
        cinema2.name = "玩具总动员";
        cinema2.image = R.mipmap.toy;
        cinema2.decrip = "《玩具总动员》是皮克斯的动画系列电影，截止2019年共制作了四部，由华特·迪士尼影片公司和皮克斯动画工作室合作推出。讲述了主角两个玩具牛仔警长胡迪和太空骑警巴斯光年的故事。";
        cinema2.url = "https://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
        cinemaList.add(cinema2);

        Cinema cinema3 = new Cinema();
        cinema3.name = "紧急救援";
        cinema3.image = R.mipmap.jinjijiuyuan;
        cinema3.decrip = "《紧急救援》是由林超贤执导的动作剧情电影，由彭于晏、王彦霖、辛芷蕾、蓝盈莹领衔主演，王雨甜、徐洋、李岷城、刘亦淳等联合主演，定档于2020年1月25日（大年初一）上映";
        cinema3.url = "https://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4";
        cinemaList.add(cinema3);

        Cinema cinema4= new Cinema();
        cinema4.name = "驯龙高手";
        cinema4.image = R.mipmap.xunlonggaoshou;
        cinema4.decrip = "《驯龙高手》由梦工厂动画制作，于2010年3月26日上映。其故事内容改编自葛蕾熙达·柯维尔于2003年所作的同名童书《如何驯服你的龙》。负责担任电影的配音演员有杰伊·巴鲁切尔、亚美莉卡·费雷拉、乔纳·希尔、克里斯托夫·梅兹-普莱瑟、克雷格·费格森以及杰拉德·巴特勒等人。 ";
        cinema4.url = "https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";
        cinemaList.add(cinema4);
    }

    @Override
    public void itemClick(int pos) {
        Cinema cinema = cinemaList.get(pos);
        Intent intent = new Intent(CinemaActivity.this,ShowVideoActivity.class);
        intent.putExtra(Constant.IMAGE_INTENT_EXTRA_VALUE,cinema.url);
        startActivity(intent);
    }
}
